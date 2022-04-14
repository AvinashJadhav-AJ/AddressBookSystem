package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.entity.Contact;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    /**
     *Simple http request get, put ,update,delete,post
     * @return
     */
    @RequestMapping(value = {"", "/", "/get"})
    public String welcomeUser() {
        return "Welcome to address book home";
    }

    /**
     *
     * @param id
     * @return
     * annotation is used to extract the value from the URl.
     */
    @GetMapping("/get/{id}")
    public String welcomeSpecificUser(@PathVariable String id) {
        return "Welcome, User " + id;
    }

    /**
     *
     * @param contact
     * @return
     */
    @PostMapping("/post")
    public String createContact(@RequestBody Contact contact) {
        return "Added " + contact.getName() + " to list";
    }

    /**
     *
     * @param contact
     * @return
     *annotation maps the HttpRequest body to a transfer
     *    or domain object, enabling automatic deserialization
     *    of the inbound HttpRequest body onto a Java object
     */
    @PutMapping("/update")
    public String updateContact(@RequestBody Contact contact) {
        return "Updated " + contact.getName() + " in list";
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public String deleteContact(@PathVariable String id) {
        return "Deleted contact " + id;
    }
}
