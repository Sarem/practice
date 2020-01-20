package com.snap.practice.contact;

import com.snap.practice.contact.models.ContactDTO;
import com.snap.practice.contact.models.ContactResponseDTO;
import com.snap.practice.contact.models.ContactSearchDTO;
import com.snap.practice.github.GithubComponent;
import com.snap.practice.github.model.RepositoryModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContractServiceTest {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactMapper contactMapper;

    @MockBean
    private GithubComponent githubComponent;

    private ContactDTO samanContactDTO;
    private ContactDTO armanContactDTO;


    @Before
    public void init() {

        Mockito.when(githubComponent.getUserRepositories("Sarem")).thenReturn(getSamanRepositories());
        samanContactDTO = new ContactDTO("Saman", "+989352490969", "saman.sarem@hotmail.com", "snap", "Sarem");
        armanContactDTO = new ContactDTO("Arman", null, "arman.ajabkhani@gmail.com", "snap", null);
        contactService.createContact(samanContactDTO);
        contactService.createContact(armanContactDTO);
    }

    private Set<RepositoryModel> getSamanRepositories(){
        RepositoryModel repositoryModel=new RepositoryModel();
        repositoryModel.setId(12343L);
        repositoryModel.setDescription("sad");
        repositoryModel.setFull_name("dsadsad");
        repositoryModel.setName("dsads");
        repositoryModel.setNode_id("dsadd");
        return Arrays.asList(repositoryModel).stream().collect(Collectors.toSet());
    }

    @Test
    public void searchForSamanContactByName() {
        ContactSearchDTO samanSearchDTO = new ContactSearchDTO();
        samanSearchDTO.setName(samanContactDTO.getName());
        List<ContactResponseDTO> result = contactService.searchContact(samanSearchDTO);
        Assertions.assertTrue(result.stream().map(contactMapper::toDTO).collect(Collectors.toList()).contains(samanContactDTO));
        Assertions.assertFalse(result.stream().map(contactMapper::toDTO).collect(Collectors.toList()).contains(armanContactDTO));
    }

    @Test
    public void searchForArmanContactByEmail() {
        ContactSearchDTO armanSearchDTO = new ContactSearchDTO();
        armanSearchDTO.setEmail(armanContactDTO.getEmail());
        List<ContactResponseDTO> result = contactService.searchContact(armanSearchDTO);
        Assertions.assertTrue(result.stream().map(contactMapper::toDTO).collect(Collectors.toList()).contains(armanContactDTO));
        Assertions.assertFalse(result.stream().map(contactMapper::toDTO).collect(Collectors.toList()).contains(samanContactDTO));
    }

    @Test
    public void searchForSamanContactByAllArguments() {
        ContactSearchDTO samanSearchDTO = contactMapper.toSearchDTO(samanContactDTO);
        List<ContactResponseDTO> result = contactService.searchContact(samanSearchDTO);
        Assertions.assertTrue(result.stream().map(contactMapper::toDTO).collect(Collectors.toList()).contains(samanContactDTO));
        Assertions.assertFalse(result.stream().map(contactMapper::toDTO).collect(Collectors.toList()).contains(armanContactDTO));
    }

    @Test
    public void searchAllContactByEmptyArguments() {
        ContactSearchDTO searchDTO = new ContactSearchDTO();
        List<ContactResponseDTO> result = contactService.searchContact(searchDTO);
        Assertions.assertTrue(result.stream().map(contactMapper::toDTO).collect(Collectors.toList()).contains(samanContactDTO));
        Assertions.assertTrue(result.stream().map(contactMapper::toDTO).collect(Collectors.toList()).contains(armanContactDTO));
    }
    @Test
    public void contactWithRepositoryShouldHaveRepositoryInDb() {
        ContactSearchDTO samanSearchDTO = new ContactSearchDTO();
        samanSearchDTO.setName(samanContactDTO.getName());
        List<ContactResponseDTO> result = contactService.searchContact(samanSearchDTO);
        result.stream().findFirst().ifPresent(contactResponseDTO -> Assertions.assertFalse(contactResponseDTO.getRepositories().isEmpty()));
    }
    @Test
    public void contactWithoutRepositoryShouldNotHaveRepositoryInDb() {
        ContactSearchDTO armanSearchDTO = new ContactSearchDTO();
        armanSearchDTO.setName(armanContactDTO.getName());
        List<ContactResponseDTO> result = contactService.searchContact(armanSearchDTO);
        result.stream().findFirst().ifPresent(contactResponseDTO -> Assertions.assertTrue(contactResponseDTO.getRepositories().isEmpty()));
    }


    @Test
    public void addAndDeleteTest() {
        contactService.createContact(new ContactDTO("Rman", "+989359994444", "test@tst.com", "somewhere", null));
        ContactSearchDTO searchDTO = new ContactSearchDTO();
        searchDTO.setName("Rman");
        contactService.searchContact(searchDTO)
                .stream().
                findFirst().ifPresent(contactDTO -> {
            contactService.removeContact(contactDTO.getId());
            Assertions.assertThrows(ContactNotFoundException.class,() ->contactService.getById(contactDTO.getId()));
        });
    }
}
