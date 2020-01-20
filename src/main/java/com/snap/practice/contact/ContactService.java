package com.snap.practice.contact;

import com.snap.practice.contact.models.*;
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
    return contactMapper.toDTO(contactRepository.findAll(Example.of(contactMapper.toEntity(contactSearchDTO))));
  }

  @Transactional(readOnly = true)
  public ContactResponseDTO getById(Long id) {
    return contactMapper.toDTO(contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id)));
  }

  @Transactional
  public void createContact(ContactDTO contactDTO) {
    if (contactDTO.getGithub() == null || contactDTO.getGithub().isEmpty()) {
      contactRepository.save(contactMapper.toEntity(contactDTO));
    } else {
      final ContactEntity contactEntity = contactMapper.toEntity(
          contactDTO, repositoryMapper.toEntity(
              githubComponent.getUserRepositories(contactDTO.getGithub())));
      contactEntity.getGithubRepositories().stream().forEach(repositoryEntity -> repositoryEntity.setContact(contactEntity));
      contactRepository.save(contactEntity);
    }
  }

  @Transactional
  public void updateContact(ContactDTO contactDTO, Long id) {
    if (!contactRepository.existsById(id)) {
      throw new ContactNotFoundException(id);
    }
    ContactEntity contactEntity;
    if (contactDTO.getGithub() == null || contactDTO.getGithub().isEmpty())
      contactEntity = contactMapper.toEntity(contactDTO);
    else {
      contactEntity = contactMapper.toEntity(
          contactDTO, repositoryMapper.toEntity(
              githubComponent.getUserRepositories(contactDTO.getGithub())));
      contactEntity.getGithubRepositories().stream()
          .forEach(repositoryEntity -> repositoryEntity.setContact(contactEntity));
    }
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
