package com.example.hw3_wi16b011;

import java.util.ArrayList;

public class Contact {
    private String mName;
    private String mPhone;

    public Contact(String name, String phone) {
        mName = name;
        mPhone = phone;
    }

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    private static int lastContactId = 0;

    public static ArrayList<Contact> createContactsList(int numContacts) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Contact("Person " + ++lastContactId, "Phone"));
        }

        return contacts;
    }
}
