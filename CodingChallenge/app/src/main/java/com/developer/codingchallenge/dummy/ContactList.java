package com.developer.codingchallenge.dummy;

import com.developer.codingchallenge.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactList {

    private static final int ITEMS = 30;
    private static ContactList INSTANCE = new ContactList();
    private List<Contact> mContactListAdapter = new ArrayList<Contact>();

    public static ContactList getInstance() {
        return INSTANCE;
    }

    private ContactList() {
        loadInformation();
    }

    private void loadInformation() {
        Contact contact;
        for (int i = 0; i <= ITEMS; i++) {
            contact = Contact.newBuilder().
                    itemNumber(i + 1).
                    title(String.format("Contact number %s", i)).
                    content(String.format("Contact Address %s", i)).
                    build();
            mContactListAdapter.add(contact);
        }
    }

    public List<Contact> getItems() {
        return mContactListAdapter;
    }
}
