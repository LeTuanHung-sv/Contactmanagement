package service;

import model.Contact;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ContactService {
    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact c){
        contacts.add(c);
    }

    public void updateContact(String id, Contact info){
        for(Contact c : contacts){
            if(c.getId().equals(id)){
                c.setName(info.getName());
                c.setPhone(info.getPhone());
                c.setEmail(info.getEmail());
                c.setAddress(info.getAddress());
                return;
            }
        }
    }

    public boolean deleteContact(String id){
        return contacts.removeIf(c -> c.getId().equals(id));
    }

    public List<Contact> searchByName(String name){
        return contacts.stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByPhone(String phone){
        return contacts.stream()
                .filter(c -> c.getPhone().contains(phone))
                .collect(Collectors.toList());
    }

    public List<Contact> getAllSorted() {
        return contacts.stream()
                .sorted(Comparator.comparing(Contact::getName))
                .collect(Collectors.toList());
    }

    public List<Contact> getAll() {
        return contacts;
    }

    public void markAsFavorite(String id, boolean isFav) {
        for (Contact c : contacts) {
            if (c.getId().equals(id)) {
                c.setFavorite(isFav);
                return;
            }
        }
    }

    public void unmarkAsFavorite(String id, boolean isFav) {
        for (Contact c : contacts) {
            if (c.getId().equals(id)) {
                c.setFavorite(isFav);
                return;
            }
        }
    }

    public List<Contact> getFavorites() {
        return contacts.stream()
                .filter(Contact::isFavorite)
                .collect(Collectors.toList());
    }
}
