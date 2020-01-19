package com.snap.practice.contact;

import com.snap.practice.contact.models.ContactDTO;
import com.snap.practice.contact.models.ContactEntity;
import com.snap.practice.contact.models.ContactSearchDTO;
import com.snap.practice.contact.repository.ContactRepository;
import com.snap.practice.github.GithubComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private GithubComponent githubComponent;


    @Autowired
    public ContactService(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Autowired
    public void setGithubComponent(GithubComponent githubComponent) {
        this.githubComponent = githubComponent;
    }

    @Transactional(readOnly = true)
    public List<ContactDTO> searchContact(ContactSearchDTO contactSearchDTO) {
        return contactMapper.toDTO(contactRepository.findAll(Example.of(contactMapper.toEntity(contactSearchDTO))));
    }

    @Transactional(readOnly = true)
    public ContactDTO getById(Long id) {
        return contactMapper.toDTO(contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id)));
    }

    @Transactional
    public void createContact(ContactDTO contactDTO) {
        if (contactDTO.getGithub() == null || contactDTO.getGithub().isEmpty())
            contactRepository.save(contactMapper.toEntity(contactDTO));
        else
            contactRepository.save(contactMapper.toEntity(contactDTO, githubComponent.getUserRepositories(contactDTO.getGithub())));
    }

    @Transactional
    public void updateContact(ContactDTO contactDTO, Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(id);
        }
        ContactEntity contactEntity;
        if (contactDTO.getGithub() == null || contactDTO.getGithub().isEmpty())
            contactEntity = contactMapper.toEntity(contactDTO);
        else
        contactEntity = contactMapper.toEntity(contactDTO, githubComponent.getUserRepositories(contactDTO.getGithub()));
        contactEntity.setId(id);
        contactRepository.save(contactEntity);
    }

    @Transactional
    public void removeContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(id);
        }
        contactRepository.deleteById(id);
    }

}
