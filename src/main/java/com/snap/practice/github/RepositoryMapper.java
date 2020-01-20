package com.snap.practice.github;

import com.snap.practice.contact.models.RepositoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RepositoryMapper {

    @Mapping(target = "githubId",source = "id")
    @Mapping(target = "id", ignore = true)
    RepositoryEntity toEntity(RepositoryModel repositoryModel);


    List<RepositoryEntity> toEntity(List<RepositoryModel> repositoryModels);

}
