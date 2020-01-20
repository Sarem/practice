package com.snap.practice.contact;

import com.snap.practice.contact.models.*;
import com.snap.practice.github.RepositoryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {RepositoryMapper.class})
public interface ContactMapper {
    @Mapping(target = "id", ignore = true)
    ContactEntity toEntity(ContactDTO contactDTO);

    @Mapping(target = "id", ignore = true)
    ContactEntity toEntity(ContactDTO contactDTO, Set<RepositoryEntity> githubRepositories);

    ContactSearchDTO toSearchDTO(ContactDTO contactDTO);

    ContactEntity toEntity(ContactSearchDTO contactSearchDTO);

    //    ContactDTO toDTO(ContactEntity contactEntity);
//    List<ContactDTO> toDTO(List<ContactEntity> contactEntities);
    @Mapping(target = "repositories", source = "githubRepositories")
    ContactResponseDTO toDTO(ContactEntity contactEntity);

    List<ContactResponseDTO> toDTO(List<ContactEntity> contactEntities);

    ContactDTO toDTO(ContactResponseDTO contactResponseDTO);

}
