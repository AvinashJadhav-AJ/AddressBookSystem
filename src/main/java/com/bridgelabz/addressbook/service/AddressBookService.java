package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dto.ContactDTO;
import com.bridgelabz.addressbook.entity.Contact;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import com.bridgelabz.addressbook.util.JMSUtil;
import com.bridgelabz.addressbook.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AddressBookService implements IAddressBookService {

    @Autowired
    AddressBookRepository addressBookRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    TokenUtil tokenUtil = new TokenUtil();
    @Autowired
    JMSUtil jmsUtil;
    /**
     * Service method for Getting All Contact Data
     *
     * @return
     */
    @Override
    public List<Contact> getContact() {

        return addressBookRepository.findAll();
    }

    /**
     * @param contactId
     * @return
     */
    @Override
    public Contact getContactById(long contactId) {

        return addressBookRepository.findById(contactId).orElseThrow(() -> new AddressBookException("Contact Not Found"));
    }

//    @Override
//    public Contact createContact(ContactDTO contactDTO) {
//        return null;
//    }
@Override
public Contact createContact(ContactDTO contactDTO) {
    Contact contact = new Contact(contactDTO);
    contact.setPassword(bCryptPasswordEncoder.encode(contactDTO.getPassword()));
    return addressBookRepository.save(contact);
}

    /**
     * @param contactDTO
     * @return
     */
    @Override
    public Contact addnewContact(ContactDTO contactDTO) {
        Contact contact = null;
        contact = new Contact(contactDTO);
        contact.setPassword(bCryptPasswordEncoder.encode(contactDTO.getPassword()));
        return addressBookRepository.save(contact);
    }

    /**
     * @param contactId
     * @param contactDTO
     * @return
     */
    @Override
    public Contact updateContact(long contactId, ContactDTO contactDTO) {
        Contact contact = this.getContactById(contactId);
        contactDTO.setPassword(bCryptPasswordEncoder.encode(contactDTO.getPassword()));
        contact.updateContact(contactDTO);
        return addressBookRepository.save(contact);
    }

    /**
     * @param contactId
     */
    @Override
    public void deleteContact(long contactId) {
        Contact contact = this.getContactById(contactId);
        addressBookRepository.delete(contact);
    }

    @Override
    public List<Contact> findAddressBookDataByCity(String city) {
        return null;
    }

    @Override
    public boolean loginUser(String email, String password) {
        Contact encodedPass = addressBookRepository.findPersonByEmail(email);
        boolean matches = bCryptPasswordEncoder.matches(password, encodedPass.getPassword());
        return matches;
    }



    /**
     * @param
     * @return
     */
//    @Override
//    public List<Contact> findAddressBookDataByCity(String city) {
//        return addressBookRepository.findContactsByCity(city);
//    }

//    @Override
//    public Status loginUser(String email, String password) {
//
//        if (addressBookRepository.existsByEmailAndPassword(email, password)) {
//            return Status.SUCCESS;
//        } else {
//            return Status.FAILURE;
//        }
//    }

    @Override
    public String loginWithToken(String email, String password) {
        Contact userPass = addressBookRepository.findContactByEmail(email);
        System.out.println(userPass);
        boolean matches = bCryptPasswordEncoder.matches(password, userPass.getPassword());
        String token = tokenUtil.createToken(userPass.getId());
        //JMSUtil jmsUtil = new JMSUtil();
        jmsUtil.sendEmail(email,"this is subject","this is body");
        return  token;

//        if (matches) {
//            return "login Succsesful" + token;
//        } else {
//            return "Invalid User Name Password";
//        }
    }
}