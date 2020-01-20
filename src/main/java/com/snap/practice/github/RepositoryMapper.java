package com.snap.practice.github;

import com.snap.practice.contact.models.RepositoryDTO;
import com.snap.practice.contact.models.RepositoryEntity;
import com.snap.practice.github.model.RepositoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RepositoryMapper {

    @Mapping(target = "githubId",source = "id")
    @Mapping(target = "id", ignore = true)
    RepositoryEntity toEntity(RepositoryModel repositoryModel);

    Set<RepositoryEntity> toEntity(Set<RepositoryModel> repositoryModels);


    RepositoryDTO toDTO(RepositoryEntity repositoryEntity);

    Set<RepositoryDTO> toDTO(Set<RepositoryEntity> repositoryEntities);



}
