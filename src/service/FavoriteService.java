package service;

import model.Contact;

import java.util.List;
import java.util.stream.Collectors;

public class FavoriteService {
    public void markFavorite(Contact contact){
        contact.setFavorite(true);
    }

    public void unmarkFavorite(Contact contact){
        contact.setFavorite(false);
    }

    public List<Contact> getFavorites(List<Contact> contact){
        return contact.stream()
                .filter(Contact::isFavorite) // lọc ra những người được yêu thích
                .collect(Collectors.toList());
    }

}
