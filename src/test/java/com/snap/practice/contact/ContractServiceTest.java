package com.snap.practice.contact;

import com.snap.practice.contact.models.ContactDTO;
import com.snap.practice.contact.models.ContactSearchDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContractServiceTest {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactMapper contactMapper;

    private ContactDTO samanContactDTO;
    private ContactDTO armanContactDTO;


    @Before
    public void init() {
        samanContactDTO = new ContactDTO("Saman", "+989352490969", "saman.sarem@hotmail.com", "snap", "https://github.com/cloudfoundry");
        armanContactDTO = new ContactDTO("Arman", null, "arman.ajabkhani@gmail.com", "snap", null);
        contactService.createContact(samanContactDTO);
        contactService.createContact(armanContactDTO);
    }

    @Test
    public void searchForSamanContactByName() {
        ContactSearchDTO samanSearchDTO = new ContactSearchDTO();
        samanSearchDTO.setName(samanContactDTO.getName());
        List<ContactDTO> result = contactService.searchContact(samanSearchDTO);
        Assertions.assertTrue(result.contains(samanContactDTO));
        Assertions.assertFalse(result.contains(armanContactDTO));
    }

    @Test
    public void searchForArmanContactByEmail() {
        ContactSearchDTO armanSearchDTO = new ContactSearchDTO();
        armanSearchDTO.setEmail(armanContactDTO.getEmail());
        List<ContactDTO> result = contactService.searchContact(armanSearchDTO);
        Assertions.assertTrue(result.contains(armanContactDTO));
        Assertions.assertFalse(result.contains(samanContactDTO));
    }

    @Test
    public void searchForSamanContactByAllArguments() {
        ContactSearchDTO samanSearchDTO = contactMapper.toSearchDTO(samanContactDTO);
        List<ContactDTO> result = contactService.searchContact(samanSearchDTO);
        Assertions.assertTrue(result.contains(samanContactDTO));
        Assertions.assertFalse(result.contains(armanContactDTO));
    }

    @Test
    public void searchAllContactByEmptyArguments() {
        ContactSearchDTO searchDTO = new ContactSearchDTO();
        List<ContactDTO> result = contactService.searchContact(searchDTO);
        Assertions.assertTrue(result.contains(samanContactDTO));
        Assertions.assertTrue(result.contains(armanContactDTO));
    }
}
