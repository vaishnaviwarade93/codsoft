import java.io.*;
import java.util.*;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Getters and setters for the contact attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress + "\n";
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
    }

    public List<Contact> searchContact(String query) {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(contact);
            }
        }
        return result;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(contacts);
            System.out.println("Contacts saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving contacts: " + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            contacts = (List<Contact>) ois.readObject();
            System.out.println("Contacts loaded from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading contacts: " + e.getMessage());
        }
    }
}

public class AddressBookSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        while (true) {
            System.out.println("Address Book Menu:");
            System.out.println("1. Add a Contact");
            System.out.println("2. Remove a Contact");
            System.out.println("3. Search for a Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Save Contacts to File");
            System.out.println("6. Load Contacts from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter Email Address: ");
                    String emailAddress = scanner.nextLine();
                    Contact contact = new Contact(name, phoneNumber, emailAddress);
                    addressBook.addContact(contact);
                    System.out.println("Contact added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Name to Remove: ");
                    String nameToRemove = scanner.nextLine();
                    addressBook.removeContact(nameToRemove);
                    System.out.println("Contact removed successfully.");
                    break;
                case 3:
                    System.out.print("Enter Search Query: ");
                    String searchQuery = scanner.nextLine();
                    List<Contact> searchResults = addressBook.searchContact(searchQuery);
                    if (searchResults.isEmpty()) {
                        System.out.println("No matching contacts found.");
                    } else {
                        System.out.println("Matching Contacts:");
                        for (Contact result : searchResults) {
                            System.out.println(result);
                        }
                    }
                    break;
                case 4:
                    List<Contact> allContacts = addressBook.getAllContacts();
                    if (allContacts.isEmpty()) {
                        System.out.println("Address book is empty.");
                    } else {
                        System.out.println("All Contacts:");
                        for (Contact contact1 : allContacts) {
                            System.out.println(contact1);
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter File Name to Save Contacts: ");
                    String saveFileName = scanner.nextLine();
                    addressBook.saveToFile(saveFileName);
                    break;
                case 6:
                    System.out.print("Enter File Name to Load Contacts: ");
                    String loadFileName = scanner.nextLine();
                    addressBook.loadFromFile(loadFileName);
                    break;
                case 7:
                    System.out.println("Exiting Address Book.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}


/***************OUTPUT******************


Address Book Menu:
1. Add a Contact
2. Remove a Contact
3. Search for a Contact
4. Display All Contacts
5. Save Contacts to File
6. Load Contacts from File
7. Exit
Enter your choice: 5
Enter File Name to Save Contacts: vaishnavi
Contacts saved to vaishnavi
Address Book Menu:
1. Add a Contact
2. Remove a Contact
3. Search for a Contact
4. Display All Contacts
5. Save Contacts to File
6. Load Contacts from File
7. Exit
Enter your choice: 6
Enter File Name to Load Contacts: vaisnavi
Error loading contacts: vaisnavi (The system cannot find the file specified)
Address Book Menu:
1. Add a Contact
2. Remove a Contact
3. Search for a Contact
4. Display All Contacts
5. Save Contacts to File
6. Load Contacts from File
7. Exit
Enter your choice: 6
Enter File Name to Load Contacts: vaishnavi
Contacts loaded from vaishnavi
Address Book Menu:
1. Add a Contact
2. Remove a Contact
3. Search for a Contact
4. Display All Contacts
5. Save Contacts to File
6. Load Contacts from File
7. Exit
Enter your choice: 7
Exiting Address Book.
Press any key to continue . . . .*******************/