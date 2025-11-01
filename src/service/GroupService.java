package service;

import model.Contact;
import model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupService {
    private final List<Group> groups = new ArrayList<>();

    public void addGroup(Group g){
        groups.add(g);
    }

    public void addContactToGroup(String groupName, String contactId){
        for(Group g : groups){
            if(g.getGroupName().equalsIgnoreCase(groupName)){
                g.addContact(contactId);
                return;
            }
        }
    }

    public void removeContactFromGroup(String groupName,String contactId){
        for(Group g : groups){
            if(g.getGroupName().equalsIgnoreCase(groupName)){
                g.removeContact(contactId);
                return;
            }
        }
    }
    public Group findGroup(String groupName) {
        for (Group g : groups) {
            if (g.getGroupName().equalsIgnoreCase(groupName))
                return g;
        }
        return null;
    }


    public List<Group> getAllGroups(){
        return groups;
    }
}
