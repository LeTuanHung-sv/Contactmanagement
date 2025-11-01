package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Contact;
import model.Group;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class FileService {
    private final Gson gson = new Gson();

    public void saveContacts(List<Contact> contacts) throws IOException{
        File file = new File("data/contacts.json");
        file.getParentFile().mkdirs();

        try (Writer writer = new FileWriter("data/contacts.json")){
            gson.toJson(contacts, writer);
        }
    }

    public List<Contact> loadContacts() throws IOException {
        File file = new File("data/contacts.json");
        if (!file.exists()) return new java.util.ArrayList<>();

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Contact>>(){}.getType();
            return gson.fromJson(reader, listType);
        }
    }

    public void saveGroups(List<Group> groups) throws IOException {
        try (Writer writer = new FileWriter("data/groups.json")) {
            gson.toJson(groups, writer);
        }
    }

    public List<Group> loadGroups() throws IOException {
        File file = new File("data/groups.json");
        if (!file.exists()) return new java.util.ArrayList<>();

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Group>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        }
    }
}
