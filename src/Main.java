import model.Contact;
import model.Group;
import service.ContactService;
import service.FileService;
import service.GroupService;
import ui.ConsoleUtils;
import validation.InputValidator;

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
            ConsoleUtils.clearScreen();
            System.out.println("===== MENU QUẢN LÝ DANH BẠ =====");
            System.out.println("1. Thêm liên hệ mới");
            System.out.println("2. cập nhật liên hệ");
            System.out.println("3. Xóa liên hệ");
            System.out.println("4. Tìm kiếm theo số điện thoại");
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
                    ConsoleUtils.clearScreen();

                    String id;
                    while (true) {
                        id = ConsoleUtils.input("Nhập ID 'exit để thoát': ");
                        if (id == null) {
                            System.out.println("Đã hủy thêm liên hệ.\n");
                            break;
                        }
                        if (contactService.existsById(id)) {
                            System.out.println("ID đã tồn tại! Vui lòng nhập ID khác.\n");
                        } else {
                            break;
                        }
                    }


                    String name;
                    while (true) {
                        name = ConsoleUtils.input("Nhập tên 'exit để thoát': ");
                        if (name == null) {
                            System.out.println("Đã hủy thêm liên hệ.\n");
                            break;
                        }
                        if (!InputValidator.isValidName(name)) {
                            System.out.println("Tên không được để trống!\n");
                        } else {
                            break;
                        }
                    }
                    if (name == null) break;

                    String phone;
                    while (true) {
                        phone = ConsoleUtils.input("Nhập số điện thoại 'exit để thoát': ");
                        if (phone == null) {
                            System.out.println("Đã hủy thêm liên hệ.\n");
                            break;
                        }
                        if (!InputValidator.isValidPhone(phone)) {
                            System.out.println("Số điện thoại phải là 9-11 chữ số!\n");
                        } else {
                            break;
                        }
                    }
                    if (phone == null) break;


                    String email;
                    while (true) {
                        email = ConsoleUtils.input("Nhập email 'exit để thoát': ");
                        if (email == null) {
                            System.out.println("Đã hủy thêm liên hệ.\n");
                            break;
                        }
                        if (!InputValidator.isValidEmail(email)) {
                            System.out.println("Email không hợp lệ! (Ví dụ: abc@gmail.com)\n");
                        } else {
                            break;
                        }
                    }
                    if (email == null) break;

                    String address;
                    while (true) {
                        address = ConsoleUtils.input("Nhập địa chỉ 'exit để thoát': ");
                        if (address == null) {
                            System.out.println("Đã hủy thêm liên hệ.\n");
                            break;
                        }
                        if (!InputValidator.isNotEmpty(address)) {
                            System.out.println("Địa chỉ không được để trống!\n");
                        } else {
                            break;
                        }
                    }
                    if (address == null) break;

                    Contact c = new Contact(id, name, phone, email, address, true);

                    contactService.addContact(c);
                    fileService.saveContacts(contactService.getAll());

                    System.out.println("Đã thêm liên hệ thành công!\n");
                    ConsoleUtils.pause();
                }

                case 2 -> {
                    ConsoleUtils.clearScreen();
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

                        String name = ConsoleUtils.input("Tên (" + existing.getName() + ") 'exit để thoát': ");
                        if (name == null) {
                            System.out.println("Đã hủy cập nhật.\n");
                            break;
                        }
                        if(name.isEmpty())
                            name = existing.getName();

                        String phone = ConsoleUtils.input("Số đện thoại (" + existing.getPhone() + ") 'exit để thoát': ");
                        if (phone == null) {
                            System.out.println("Đã hủy cập nhật.\n");
                            break;
                        }
                        if (phone.isEmpty()) phone = existing.getPhone();

                        String email = ConsoleUtils.input("Email (" + existing.getEmail() + ") 'exit để thoát': ");
                        if (email == null) {
                            System.out.println("Đã hủy cập nhật.\n");
                            break;
                        }
                        if (email.isEmpty()) email = existing.getEmail();

                        String address = ConsoleUtils.input("Địa chỉ (" + existing.getAddress() + ") 'exit để thoát': ");
                        if (address == null) {
                            System.out.println("Đã hủy cập nhật.\n");
                            break;
                        }
                        if (address.isEmpty()) address = existing.getAddress();

                        Contact updatedInfo = new Contact(id, name, phone, email, address, existing.isFavorite());
                        contactService.updateContact(id, updatedInfo);
                        fileService.saveContacts(contactService.getAll());
                        System.out.println("Đã cập nhật liên hệ thành công!\n");
                        ConsoleUtils.pause();
                    }
                }

                case 3 -> {
                    ConsoleUtils.clearScreen();
                    System.out.print("Nhập ID liên hệ cần xóa: ");
                    String id = scanner.nextLine();


                    if (contactService.deleteContact(id)) {
                        fileService.saveContacts(contactService.getAll());
                        System.out.println("Đã xóa liên hệ thành công!\n");
                    } else {
                        System.out.println("Không tìm thấy liên hệ có ID này.\n");
                    }
                    ConsoleUtils.pause();
                }

                case 4 -> {
                    ConsoleUtils.clearScreen();
                    System.out.print("Nhập số điện thoại cần tìm: ");
                    String phone = scanner.nextLine();
                    List<Contact> found = contactService.searchByPhone(phone);
                    if (found.isEmpty()) {
                        System.out.println("Không tìm thấy liên hệ nào!");
                    }else {
                        System.out.printf("%-10s %-20s %-15s %-25s %-30s%n",
                                "ID", "Tên", "Số ĐT", "Email", "Địa chỉ");
                        System.out.println("-----------------------------------------------------------------------------------------------");

                        found.forEach(c -> System.out.printf("%-10s %-20s %-15s %-25s %-30s%n",
                                c.getId(),
                                c.getName(),
                                c.getPhone(),
                                c.getEmail(),
                                c.getAddress()
                        ));
                        System.out.println();
                    }
                    ConsoleUtils.pause();
                }

                case 5 -> {
                    ConsoleUtils.clearScreen();
                    List<Contact> all = contactService.getAllSorted();
                    if (all.isEmpty()) System.out.println("Danh bạ trống.\n");
                    else contactService.displayContacts(contactService.getAllSorted());
                    System.out.println();
                    ConsoleUtils.pause();
                }

                case 6 -> {
                    ConsoleUtils.clearScreen();
                    System.out.print("Nhập ID liên hệ cần đánh dấu yêu thích: ");
                    String id = scanner.nextLine();
                    Contact ex = null;
                    for(Contact c : contactService.getAll()){
                        if(c.getId().equals(id)){
                            ex = c;
                            break;
                        }
                    }
                    if(ex == null){
                        System.out.print("Id không tồn tại!");
                    }else {
                        contactService.markAsFavorite(id, true);
                        fileService.saveContacts(contactService.getAll());
                        System.out.println("Đã thêm vào danh sách yêu thích!\n");
                    }
                    ConsoleUtils.pause();
                }

                case 7 -> {
                    ConsoleUtils.clearScreen();
                    System.out.print("Nhập ID liên hệ cần bỏ đánh dấu yêu thích: ");
                    String id = scanner.nextLine();
                    Contact ex = null;
                    for(Contact c : contactService.getAll()){
                        if(c.getId().equals(id)){
                            ex = c;
                            break;
                        }
                    }
                    if(ex == null){
                        System.out.print("Id không tồn tại!");
                    }else {
                        contactService.unmarkAsFavorite(id,false);
                        fileService.saveContacts(contactService.getAll());
                        System.out.println("Đã xóa khỏi danh sách yêu thích!\n");
                    }
                    ConsoleUtils.pause();
                }

                case 8 -> {
                    ConsoleUtils.clearScreen();
                    System.out.println("===== DANH BẠ YÊU THÍCH =====");
                    List<Contact> favorites = contacts.stream()
                            .filter(Contact::isFavorite)
                            .toList();

                    if (favorites.isEmpty()) {
                        System.out.println("Không có liên hệ yêu thích nào.");
                    } else {
                        contactService.displayContacts(favorites);
                    }
                    ConsoleUtils.pause();
                }

                case 9 -> {
                    ConsoleUtils.clearScreen();
                    String groupName = ConsoleUtils.input("Nhập tên nhóm mới 'exit để thoát': ");
                    if (groupName == null) {
                        System.out.println("Đã hủy tạo nhóm.\n");
                        break;
                    }

                    Group group = new Group(groupName);
                    groupService.addGroup(group);
                    fileService.saveGroups(groupService.getAllGroups());
                    System.out.println("Đã tạo nhóm thành công!");
                    ConsoleUtils.pause();
                }

                case 10 ->{
                    ConsoleUtils.clearScreen();
                    String groupName = ConsoleUtils.input("Nhập tên nhóm '0 để thoát': ");
                    if (groupName == null) break;

                    String contactId = ConsoleUtils.input("Nhập ID liên hệ cần thêm 'exit để thoát': ");
                    if (contactId == null) break;

                    groupService.addContactToGroup(groupName, contactId);
                    fileService.saveGroups(groupService.getAllGroups());
                    System.out.println("Đã thêm liên hệ vào nhóm!\n");
                    ConsoleUtils.pause();
                }

                case 11 -> {
                    ConsoleUtils.clearScreen();
                    System.out.println("Nhập tên nhóm: ");
                    String groupName = scanner.nextLine();
                    System.out.println("Nhập Id liên hệ cần xóa: ");
                    String contactId = scanner.nextLine();

                    boolean removed = groupService.removeContactFromGroup(groupName, contactId);

                    if (!removed) {
                        System.out.println("ID không tồn tại trong nhóm hoặc nhóm không tồn tại!\n");
                    } else {
                        fileService.saveGroups(groupService.getAllGroups());
                        System.out.println("Đã xóa liên hệ khỏi nhóm!\n");
                    }
                    ConsoleUtils.pause();
                }

                case 12 -> {
                    ConsoleUtils.clearScreen();
                    System.out.print("Nhập tên nhóm: ");
                    String groupName = scanner.nextLine().trim();

                    Group g = groupService.findGroup(groupName);

                    if (g == null) {
                        System.out.println("Không tìm thấy nhóm này!\n");
                    } else {
                        System.out.println("===== Liên hệ trong nhóm " + groupName + " =====");
                        System.out.printf("%-5s %-20s %-15s %-25s %-30s\n", "ID", "Tên", "SĐT", "Email", "Địa chỉ");
                        System.out.println("-------------------------------------------------------------------------------");
                        g.getContactId().forEach(contactId -> {
                            Contact c = contactService.findContactById(contactId);
                            if (c != null) {
                                System.out.printf("%-5s %-20s %-15s %-25s %-30s\n",
                                        c.getId(), c.getName(), c.getPhone(), c.getEmail(), c.getAddress());
                            }
                        });
                        System.out.println();
                        ConsoleUtils.pause();
                    }
                }

                case 13 -> {
                    System.out.println("Thoát chương trình. Dữ liệu đã được lưu!");
                    fileService.saveContacts(contactService.getAll());
                    run = false;
                }

                default -> {
                    System.out.println(" Lựa chọn không hợp lệ, vui lòng thử lại.\n");
                    ConsoleUtils.pause();
                }
            }
        }
    }
}