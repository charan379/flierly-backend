package com.ctytech.flierly.contact.repository;

import com.ctytech.flierly.contact.enitity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
}
