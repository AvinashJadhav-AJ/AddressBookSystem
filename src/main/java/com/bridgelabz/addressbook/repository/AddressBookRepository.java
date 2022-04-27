package com.bridgelabz.addressbook.repository;

import com.bridgelabz.addressbook.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressBookRepository extends JpaRepository<Contact, Long> {

    /**
     * Custom Query to get addressBook data by city
     * @param
     * @return
     */
//    @Query(value = "select * from addressbook where city = :city",nativeQuery = true)
//    List<Contact> findAddressBookDataByCity(String city);
   // List<Contact> findContactsByCity(String city);

 //  boolean existsByEmailAndPassword(String email, String Password);

   // Contact findByEmailIdAndPassword(String email, String Password);

    //Optional<Contact> findPasswordByEmailId(String email);
    @Query(value = "select * from contact where email=:email",nativeQuery = true)
     Contact findPersonByEmail(String email);
   // Optional<Contact> findPasswordById(String email);
    Contact findContactByEmail(String email);
}
