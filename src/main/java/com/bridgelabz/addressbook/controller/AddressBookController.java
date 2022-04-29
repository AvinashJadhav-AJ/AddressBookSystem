package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.ContactDTO;
import com.bridgelabz.addressbook.dto.LoginDTO;
import com.bridgelabz.addressbook.dto.ResponseDTO;
import com.bridgelabz.addressbook.entity.Contact;
import com.bridgelabz.addressbook.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    @Autowired
    private IAddressBookService addressbookservice;

    /**
     *
     * @return
     */
    @RequestMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getContactData() {
        List<Contact> contactList = addressbookservice.getContact();
        ResponseDTO response = new ResponseDTO("Get call success", contactList);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }


    /**
     *
     * @param contactId
     * @return
     */
    @GetMapping("/get/{contactId}")
    public ResponseEntity<ResponseDTO> getContactData(@PathVariable("contactId") long contactId) {
        Contact contact = addressbookservice.getContactById(contactId);
        ResponseDTO response = new ResponseDTO("Get call success for id", contact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

    /**
     *
     * @param contactDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addContactData(@RequestBody ContactDTO contactDTO) {
        Contact contact = addressbookservice.createContact(contactDTO);
        ResponseDTO response = new ResponseDTO("Created contact data for", contact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

    /**
     *
     * @param contactDTO
     * @return
     */
    @PostMapping("/user/register")
    public ResponseEntity<ResponseDTO> registerContactData(@RequestBody ContactDTO contactDTO) {
//        Contact contact = addressbookservice.createContact(contactDTO);
//        ResponseDTO response = new ResponseDTO("Register contact data for", contact);
//        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

        Contact contact = addressbookservice.createContact(contactDTO);
        if(contact!=null) {
            ResponseDTO responseDTO = new ResponseDTO("Registered new user in address book", contact);
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        }else {
            return new ResponseEntity("Email id already present", HttpStatus.OK);
        }

    }

    /**
     *
     * @param loginDTO
     * @return
     */
//    @PostMapping("/login/user")
//    public  ResponseEntity<ResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO){
//        boolean status = addressbookservice.loginUser(loginDTO.getEmailID(), loginDTO.getPassword());
//        ResponseDTO responseDTO = new ResponseDTO("Login contact Data for",status);
//        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
//    }

    /**
     *
     * @param contactId
     * @param contactDTO
     * @return
     */
    @PutMapping("/update/{contactId}")
    public ResponseEntity<ResponseDTO> updateContactData(@PathVariable("contactId") int contactId,
                                                         @RequestBody ContactDTO contactDTO) {
        Contact contact = addressbookservice.updateContact(contactId, contactDTO);
        ResponseDTO response = new ResponseDTO("Updated contact data for", contact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

    /**
     *
     * @param contactId
     * @return
     */
    @DeleteMapping("/delete/{contactId}")
    public ResponseEntity<ResponseDTO> deleteContactData(@PathVariable("contactId") int contactId) {
        addressbookservice.deleteContact(contactId);
        ResponseDTO response = new ResponseDTO("Delete call success for id ", "deleted id:" + contactId);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }
   @PostMapping("/login")
   public  ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO loginDTO){
       boolean matches = addressbookservice.loginUser(loginDTO.getEmail(), loginDTO.getPassword());
       ResponseDTO responseDTO = null;
       if (matches){
           responseDTO = new ResponseDTO("user login succesfull", "welcome");
       }
       else {
           responseDTO = new ResponseDTO("invaild password","retry");
       }
       return new ResponseEntity<>(responseDTO,HttpStatus.OK);
   }
    @PostMapping("/tokenlogin")
    public ResponseEntity<ResponseDTO> loginWithToken(@RequestBody LoginDTO loginDTO){
//        ResponseDTO response = new ResponseDTO( addressbookservice.loginWithToken(loginDTO.getEmail(),loginDTO.getPassword()),"Done");
//        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
//    }
        ResponseDTO response = new ResponseDTO( addressbookservice.loginWithToken(loginDTO.getEmail(),loginDTO.getPassword()),"OTP Sent to your Email ID");
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
    }

}
