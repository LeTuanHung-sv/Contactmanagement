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
                .sorted(Comparator.comparing(Contact::getId))
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

    public boolean existsById(String id) {
        for (Contact c : getAll()) {
            if (c.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


//    public List<Contact> getFavorites() {
//        return contacts.stream()
//                .filter(Contact::isFavorite)
//                .collect(Collectors.toList());
//    }

    public void displayContacts(List<Contact> contacts){
        if (contacts == null || contacts.isEmpty()) {
            System.out.println("Danh bạ trống!");
            return;
        }

        System.out.println("====================================================================================================================");
        System.out.printf("| %-4s | %-20s | %-15s | %-25s | %-25s | %-10s |%n",
                "ID", "Họ và tên", "SĐT", "Email", "Address", "Yêu thích");
        System.out.println("====================================================================================================================");

        for (Contact c : contacts) {
            System.out.printf("| %-4s | %-20s | %-15s | %-25s | %-25s | %-10s |%n",
                    c.getId(),
                    c.getName(),
                    c.getPhone(),
                    c.getEmail(),
                    c.getAddress(),
                    c.isFavorite() ? "(*) Có" : "Không"); System.out.println("====================================================================================================================");
        }

        System.out.println("====================================================================================================================");
    }
}
