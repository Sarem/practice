package com.snap.practice.contact;

import com.snap.practice.contact.models.ContactDTO;
import com.snap.practice.contact.models.ContactResponseDTO;
import com.snap.practice.contact.models.ContactSearchDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Find All Contacts or search by example", description = "search for contact", tags = {"contactDTO"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContactDTO.class))))})
    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> searchContact(@RequestBody(required = false) ContactSearchDTO contactSearchDTO) {
        return new ResponseEntity<>(contactService.searchContact(contactSearchDTO), HttpStatus.OK);
    }

    @Operation(summary = "Find contact by ID", description = "Returns a single contact", tags = {"contactDTO"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = ContactDTO.class))),
            @ApiResponse(responseCode = "404", description = "Contact not found")})
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<ContactResponseDTO> getContact(@NotNull @PathVariable("id") Long id) {
        return new ResponseEntity<>(contactService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Add a new contact", description = "", tags = {"contact"})
    @PutMapping
    public void addNewContact(@Valid @RequestBody ContactDTO contactDTO) {
        contactService.createContact(contactDTO);
    }

    @Operation(summary = "Update an existing contact", description = "", tags = {"contact"})
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody ContactDTO contactDTO, @NotNull @PathVariable("id") Long id) {
        contactService.updateContact(contactDTO, id);
    }

    @Operation(summary = "Deletes a contact", description = "", tags = {"contact"})
    @DeleteMapping("/{id}")
    public void removeContact(@NotNull @PathVariable("id") Long id) {
        contactService.removeContact(id);
    }


}
