package model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupName;
    private final List<String> contactId;

    public Group(){
        contactId = new ArrayList<>();
    }

    public Group(String groupName) {
        this.groupName = groupName;
        this.contactId = new ArrayList<>();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getContactId() {
        return contactId;
    }

    public void addContact(String id) {
        if (!contactId.contains(id)) contactId.add(id);
    }

    public boolean removeContact(String id) {
        return contactId.remove(id);
    }

}
