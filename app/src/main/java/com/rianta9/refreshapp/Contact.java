package com.rianta9.refreshapp;

public class Contact {
    long id;
    String phoneNumber;
    String contactName;
    int contactAvatar;

    public Contact(long id, String phoneNumber, String contactName, int contactAvatar) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.contactName = contactName;
        this.contactAvatar = contactAvatar;
    }

    public Contact(String phoneNumber, String contactName, int contactAvatar) {
        this.phoneNumber = phoneNumber;
        this.contactName = contactName;
        this.contactAvatar = contactAvatar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public int getContactAvatar() {
        return contactAvatar;
    }

    public void setContactAvatar(int contactAvatar) {
        this.contactAvatar = contactAvatar;
    }
}
