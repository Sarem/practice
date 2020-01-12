package com.snap.practice.contact;

import com.snap.practice.contact.models.ContactDTO;
import com.snap.practice.contact.models.ContactSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

  @GetMapping("/{id}")
  public ContactDTO getContact(@NotNull @PathVariable("id") Long id) {
    return contactService.getById(id);
  }

  @PutMapping
  public void addNewContact(@Valid @RequestBody ContactDTO contactDTO) {
    contactService.createContact(contactDTO);
  }

  @PutMapping("/{id}")
  public void update(@Valid @RequestBody ContactDTO contactDTO,@NotNull @PathVariable("id") Long id) {
    contactService.updateContact(contactDTO,id);
  }

  @DeleteMapping("/{id}")
  public void removeContact(@NotNull @PathVariable("id") Long id){
    contactService.removeContact(id);
  }



}
