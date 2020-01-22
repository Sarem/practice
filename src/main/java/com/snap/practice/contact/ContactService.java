package com.snap.practice.contact;

import com.snap.practice.contact.models.ContactDTO;
import com.snap.practice.contact.models.ContactEntity;
import com.snap.practice.contact.models.ContactResponseDTO;
import com.snap.practice.contact.models.ContactSearchDTO;
import com.snap.practice.contact.repository.ContactRepository;
import com.snap.practice.github.GithubComponent;
import com.snap.practice.github.RepositoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final RepositoryMapper repositoryMapper;
    private GithubComponent githubComponent;


    @Autowired
    public ContactService(ContactRepository contactRepository, ContactMapper contactMapper, RepositoryMapper repositoryMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
        this.repositoryMapper = repositoryMapper;
    }

    @Autowired
    public void setGithubComponent(GithubComponent githubComponent) {
        this.githubComponent = githubComponent;
    }

    @Transactional(readOnly = true)
    public List<ContactResponseDTO> searchContact(ContactSearchDTO contactSearchDTO) {
        if (contactSearchDTO == null)
            return contactMapper.toDTO(contactRepository.findAll());
        return contactMapper.toDTO(contactRepository.findAll(Example.of(contactMapper.toEntity(contactSearchDTO))));
    }

    @Transactional(readOnly = true)
    public ContactResponseDTO getById(Long id) {
        return contactMapper.toDTO(contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id)));
    }

    @Transactional
    public void createContact(ContactDTO contactDTO) {
        if (isWithoutGitHub(contactDTO)) {
            contactRepository.save(contactMapper.toEntity(contactDTO));
        } else {
            final ContactEntity contactEntity = createContactEntityWithGitRepos(contactDTO);
            contactRepository.save(contactEntity);
        }
    }

    @Transactional
    public void updateContact(ContactDTO contactDTO, Long id) {
        validateIdExist(id);
        ContactEntity contactEntity;
        if (isWithoutGitHub(contactDTO))
            contactEntity = contactMapper.toEntity(contactDTO);
        else {
            contactEntity = createContactEntityWithGitRepos(contactDTO);
        }
        contactEntity.setId(id);
        contactRepository.save(contactEntity);
    }

    @Transactional
    public void removeContact(Long id) {
        validateIdExist(id);
        contactRepository.deleteById(id);
    }

    private ContactEntity createContactEntityWithGitRepos(ContactDTO contactDTO) {
        ContactEntity contactEntity = contactMapper.toEntity(
                contactDTO, repositoryMapper.toEntity(
                        githubComponent.getUserRepositories(contactDTO.getGithub())));
        contactEntity.getGithubRepositories().stream().forEach(repositoryEntity -> repositoryEntity.setContact(contactEntity));
        return contactEntity;
    }

    private boolean isWithoutGitHub(ContactDTO contactDTO) {
        return contactDTO.getGithub() == null || contactDTO.getGithub().isEmpty();
    }

    private void validateIdExist(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(id);
        }
    }

}
