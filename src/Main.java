import model.Contact;
import model.Group;
import service.ContactService;
import service.FavoriteService;
import service.FileService;
import service.GroupService;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ContactService contactService = new ContactService();
    private static final GroupService groupService = new GroupService();
    private static final FileService fileService = new FileService();

    public static void main(String[] args) throws IOException {

        List<Contact> contacts = fileService.loadContacts();
        contacts.forEach(contactService::addContact);
        System.out.println("Đã tải: " + contacts.size() + " liên hệ.\n");

        boolean run = true;
        while (run){
            System.out.println("===== MENU QUẢN LÝ DANH BẠ =====");
            System.out.println("1. Thêm liên hệ mới");
            System.out.println("2. cập nhật liên hệ");
            System.out.println("3. Xóa liên hệ");
            System.out.println("4. Tìm kiếm theo số điện thoại hoặc tên");
            System.out.println("5. Xem / sắp xếp tất cả liên hệ");
            System.out.println("6. Đánh dấu yêu thích");
            System.out.println("7. bỏ đánh dấu yêu thích");
            System.out.println("8. Xem danh bạ yêu thích");
            System.out.println("9. Thêm nhóm");
            System.out.println("10. Thêm liên hệ vào nhóm");
            System.out.println("11. Xóa liên hệ khỏi nhóm");
            System.out.println("12. Xem liên hệ trong nhóm");
            System.out.println("13. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println();

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhập ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Nhập tên: ");
                    String name = scanner.nextLine();

                    System.out.print("Nhập số điện thoại: ");
                    String phone = scanner.nextLine();

                    System.out.print("Nhập email: ");
                    String email = scanner.nextLine();

                    System.out.print("Nhập địa chỉ: ");
                    String address = scanner.nextLine();

                    Contact c = new Contact(id, name, phone, email, address, true);
                    contactService.addContact(c);
                    fileService.saveContacts(contactService.getAll());
                    System.out.println("Đã thêm liên hệ thành công!\n");
                }

                case 2 -> {
                    System.out.print("Nhập ID liên hệ cần cập nhật: ");
                    String id = scanner.nextLine();
                    Contact existing = null;
                    for (Contact c : contactService.getAll()) {
                        if (c.getId().equals(id)) {
                            existing = c;
                            break;
                        }
                    }

                    if(existing == null){
                        System.out.println("Không tìm thấy Id!\n");
                    }else {
                        System.out.println("Nhập thông tin mới (bỏ trống nếu muốn giữ nguyên): ");

                        System.out.print("Tên (" + existing.getName() + "): ");
                        String name = scanner.nextLine();
                        if(name.isEmpty())
                            name = existing.getName();

                        System.out.print("Số điện thoại (" + existing.getPhone() + "): ");
                        String phone = scanner.nextLine();
                        if (phone.isEmpty()) phone = existing.getPhone();

                        System.out.print("Email (" + existing.getEmail() + "): ");
                        String email = scanner.nextLine();
                        if (email.isEmpty()) email = existing.getEmail();

                        System.out.print("Địa chỉ (" + existing.getAddress() + "): ");
                        String address = scanner.nextLine();
                        if (address.isEmpty()) address = existing.getAddress();

                        Contact updatedInfo = new Contact(id, name, phone, email, address, existing.isFavorite());
                        contactService.updateContact(id, updatedInfo);
                        fileService.saveContacts(contactService.getAll());
                        System.out.println("Đã cập nhật liên hệ thành công!\n");
                    }
                }

                case 3 -> {
                    System.out.print("Nhập ID liên hệ cần xóa: ");
                    String id = scanner.nextLine();


                    if (contactService.deleteContact(id)) {
                        fileService.saveContacts(contactService.getAll());
                        System.out.println("Đã xóa liên hệ thành công!\n");
                    } else {
                        System.out.println("Không tìm thấy liên hệ có ID này.\n");
                    }
                }

                case 4 -> {
                    System.out.print("Nhập số điện thoại cần tìm: ");
                    String phone = scanner.nextLine();
                    List<Contact> found = contactService.searchByPhone(phone);
                    if (found.isEmpty()) {
                        System.out.println("Không tìm thấy liên hệ nào!");
                    }else {
                        found.forEach(c -> System.out.println(c.getName() + " - " + c.getPhone()));
                        System.out.println();
                    }
                }

                case 5 -> {
                    List<Contact> all = contactService.getAllSorted();
                    if (all.isEmpty()) System.out.println("Danh bạ trống.\n");
                    else all.forEach(c -> System.out.println(c.getName() + " - " + c.getPhone()));
                    System.out.println();
                }

                case 6 -> {
                    System.out.print("Nhập ID liên hệ cần đánh dấu yêu thích: ");
                    String id = scanner.nextLine();
                    contactService.markAsFavorite(id, true);
                    fileService.saveContacts(contactService.getAll());
                    System.out.println("Đã thêm vào danh sách yêu thích!\n");
                }

                case 7 -> {
                    System.out.print("Nhập ID liên hệ cần bỏ đánh dấu yêu thích: ");
                    String id = scanner.nextLine();
                    contactService.unmarkAsFavorite(id,false);
                    fileService.saveContacts(contactService.getAll());
                    System.out.println("Đã xóa khỏi danh sách yêu thích!\n");
                }

                case 8 -> {
                    List<Contact> favs = contactService.getFavorites();
                    if (favs.isEmpty()) System.out.println("Không có liên hệ yêu thích nào.\n");
                    else favs.forEach(System.out::println);
                    System.out.println();
                }

                case 9 -> {
                    System.out.println("Nhập Tên Nhóm mới: ");
                    String groupName = scanner.nextLine();

                    Group group = new Group(groupName);
                    groupService.addGroup(group);
                    fileService.saveGroups(groupService.getAllGroups());
                    System.out.println("Đã tạo nhóm thành công!");
                }

                case 10 ->{
                    System.out.println("Nhập tên nhóm: ");
                    String groupName = scanner.nextLine();
                    System.out.println("Nhập Id liên hệ cần thêm: ");
                    String contactId = scanner.nextLine();

                    groupService.addContactToGroup(groupName, contactId);
                    fileService.saveGroups(groupService.getAllGroups());
                    System.out.println("Đã thêm liên hệ vào nhóm!\n");
                }

                case 11 -> {
                    System.out.println("Nhập tên nhóm: ");
                    String groupName = scanner.nextLine();
                    System.out.println("Nhập Id liên hệ cần xóa: ");
                    String contactId = scanner.nextLine();

                    groupService.removeContactFromGroup(groupName, contactId);
                    fileService.saveGroups(groupService.getAllGroups());
                    System.out.println("Đã xóa liên hệ vào nhóm!\n");
                }

                case 12 -> {
                    System.out.print("Nhập tên nhóm: ");
                    String groupName = scanner.nextLine();
                    Group g = groupService.findGroup(groupName);

                    if (g == null) {
                        System.out.println("Không tìm thấy nhóm này!\n");
                    } else {
                        System.out.println("Liên hệ trong nhóm " + groupName + ":");
                        g.getContactId().forEach(System.out::println);
                        System.out.println();
                    }
                }

                case 13 -> {
                    System.out.println("Thoát chương trình. Dữ liệu đã được lưu!");
                    fileService.saveContacts(contactService.getAll());
                    run = false;
                }

                default -> System.out.println(" Lựa chọn không hợp lệ, vui lòng thử lại.\n");
            }
        }
    }
}