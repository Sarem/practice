package com.snap.practice.contact;

import com.snap.practice.contact.models.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository  extends JpaRepository<ContactEntity, Long> {

}
