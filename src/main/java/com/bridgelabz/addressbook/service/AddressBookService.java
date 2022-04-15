package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dto.ContactDTO;
import com.bridgelabz.addressbook.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService implements IAddressBookService{
    /**
     *
     * @return
     */
    @Override
    public List<Contact> getContact() {
        List<Contact> contactList = new ArrayList<>();
        contactList.add(new Contact(1,
                new ContactDTO("Shubham", "Singh", "varanasi",
                        "UP", "Varanasi", "221105",
                        "6380236945")));
        return contactList;
    }

    /**
     *
     * @param contactId
     * @return
     */
    @Override
    public Contact getContactById(int contactId) {
        Contact contact = new Contact(2,
                new ContactDTO("Avinash", "Jhadhav",
                        "Aurangabad", "Maharastara", "Aurangabad",
                        "45586", "7679157389"));
        return contact;
    }

    /**
     *
     * @param contactDTO
     * @return
     */
    @Override
    public Contact createContact(ContactDTO contactDTO) {
        Contact contact = new Contact(3, contactDTO);
        return contact;
    }

    /**
     *
     * @param contactId
     * @param contactDTO
     * @return
     */
    @Override
    public Contact updateContact(int contactId, ContactDTO contactDTO) {
        Contact contact = new Contact(1, contactDTO);
        return contact;
    }

    /**
     *
     * @param contactId
     */
    @Override
    public void deleteContact(int contactId) {

    }
}
