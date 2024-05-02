import android.content.Context;

import com.example.httpurlconnectionexample.data.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    private static List<Contact> contacts;

    public ContactRepository(Context context) {
        // Initialize the contacts list with some sample data
        contacts = new ArrayList<>();
        contacts.add(new Contact("1", "Company A", "John Doe", "Manager", "Sales", "123456789", "123", "987654321", "john.doe@example.com", "123 Main St", "City A", "County A", "12345", "Active", "1000000"));
        contacts.add(new Contact("2", "Company B", "Jane Smith", "Director", "Marketing", "234567890", "456", "098765432", "jane.smith@example.com", "456 Oak Ave", "City B", "County B", "67890", "Inactive", "2000000"));
        contacts.add(new Contact("3", "Company C", "Michael Johnson", "VP", "Operations", "345678901", "789", "109876543", "michael.johnson@example.com", "789 Elm St", "City C", "County C", "23456", "Active", "3000000"));
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public Contact getContactById(String id) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                return contact;
            }
        }
        return null;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void updateContact(Contact contact) {
        int index = contacts.indexOf(getContactById(contact.getId()));
        if (index != -1) {
            contacts.set(index, contact);
        }
    }

    public void deleteContact(String id) {
        Contact contact = getContactById(id);
        if (contact != null) {
            contacts.remove(contact);
        }
    }
}
