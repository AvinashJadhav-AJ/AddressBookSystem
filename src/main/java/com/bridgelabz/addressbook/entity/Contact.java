package com.bridgelabz.addressbook.entity;

import com.bridgelabz.addressbook.dto.ContactDTO;

public class Contact {
    private String id;
    private String name;

    /**
     *
     * @param i
     * @param contactDTO
     */
    public Contact(int i, ContactDTO contactDTO) {

    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
