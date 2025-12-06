package service;

import model.Contact;
import model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupService {
    private final List<Group> groups;

    public GroupService() {
        try {
            FileService fileService = new FileService();
            groups = fileService.loadGroups();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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

    public boolean removeContactFromGroup(String groupName,String contactId){
        for(Group g : groups){
            if(g.getGroupName().equalsIgnoreCase(groupName)){
                return g.removeContact(contactId);
            }
        }
        return false;
    }
    public Group findGroup(String groupName) {
        for (Group g : groups) {
            if (g.getGroupName().trim().equalsIgnoreCase(groupName.trim()))
                return g;
        }
        return null;
    }


    public List<Group> getAllGroups(){
        return groups;
    }
}
