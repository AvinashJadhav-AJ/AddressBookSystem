package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dto.ContactDTO;
import com.bridgelabz.addressbook.entity.Contact;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Service
public class AddressBookService implements IAddressBookService{

    @Autowired
    AddressBookRepository addressBookRepository;
    /**
     * Service method for Getting All Contact Data
     * @return
     */
    @Override
    public List<Contact> getContact() {

        return addressBookRepository.findAll();
    }

    /**
     *
     * @param contactId
     * @return
     */
    @Override
    public Contact getContactById(long contactId) {

        return addressBookRepository.findById(contactId).orElseThrow(() -> new AddressBookException("Contact Not Found"));
    }

    /**
     *
     * @param contactDTO
     * @return
     */
    @Override
    public Contact createContact(ContactDTO contactDTO) {
    Contact contact = new Contact(contactDTO);
        return addressBookRepository.save(contact);
    }

    /**
     *
     * @param contactId
     * @param contactDTO
     * @return
     */
    @Override
    public Contact updateContact(long contactId, ContactDTO contactDTO) {
        Contact contact = this.getContactById(contactId);
        contact.Contact(contactDTO);
        return addressBookRepository.save(contact);
    }

    /**
     *
     * @param contactId
     */
    @Override
    public void deleteContact(long contactId) {
        Contact contact = this.getContactById(contactId);
        addressBookRepository.delete(contact);
    }

    /**
     *
     * @param city
     * @return
     */
    @Override
    public List<Contact> findAddressBookDataByCity(String city) {
        return addressBookRepository.findAddressBookDataByCity(city);
    }
}
