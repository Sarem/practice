package com.snap.practice.contact.repository;

import com.snap.practice.contact.models.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryRepository extends JpaRepository<RepositoryEntity, Long> {

}
