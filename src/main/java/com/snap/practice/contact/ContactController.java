package com.snap.practice.contact;

import com.snap.practice.contact.models.ContactDTO;
import com.snap.practice.contact.models.ContactSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

  private final ContactService contactService;

  @Autowired
  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping
  public List<ContactDTO> searchContact(@RequestBody ContactSearchDTO contactSearchDTO) {
    return contactService.searchContact(contactSearchDTO);
  }

  @PutMapping
  public void addNewContact(@Valid @RequestBody ContactDTO contactDTO) {
    contactService.createContact(contactDTO);
  }
}
