package com.snap.practice.contact;

import com.snap.practice.contact.models.ContactDTO;
import com.snap.practice.contact.models.ContactEntity;
import com.snap.practice.contact.models.ContactSearchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    @Mapping(target = "id", ignore = true)
    ContactEntity toEntity(ContactDTO contactDTO);
    ContactSearchDTO toSearchDTO(ContactDTO contactDTO);
    ContactEntity toEntity(ContactSearchDTO contactSearchDTO);
    ContactDTO toDTO(ContactEntity contactEntity);
    List<ContactDTO> toDTO(List<ContactEntity> contactEntities);
}
