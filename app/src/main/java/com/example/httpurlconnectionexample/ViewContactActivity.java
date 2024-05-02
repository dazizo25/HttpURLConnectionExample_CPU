import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.httpurlconnectionexample.R;
import com.example.httpurlconnectionexample.data.Contact;
import com.example.httpurlconnectionexample.data.ContactRepository;

import java.util.List;

public class ViewContactActivity extends AppCompatActivity {

    private RecyclerView recyclerViewContacts;
    private ContactAdapter contactAdapter;
    private ContactRepository contactRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        // Initialize the ContactRepository
        contactRepository = new ContactRepository(this);

        // Find the RecyclerView
        recyclerViewContacts = findViewById(R.id.recyclerView_contacts);

        // Set up the RecyclerView
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter();
        recyclerViewContacts.setAdapter(contactAdapter);

        // Load the contacts
        loadContacts();
    }

    private void loadContacts() {
        List<Contact> contacts = contactRepository.getAllContacts();
        contactAdapter.setContacts(contacts);
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

        private List<Contact> contacts;

        void setContacts(List<Contact> contacts) {
            this.contacts = contacts;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_contact, parent, false);
            return new ContactViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            Contact contact = contacts.get(position);
            holder.bind(contact);
        }

        @Override
        public int getItemCount() {
            return contacts != null ? contacts.size() : 0;
        }

        class ContactViewHolder extends RecyclerView.ViewHolder {

            private TextView textViewName;
            private TextView textViewCompany;

            ContactViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewName = itemView.findViewById(R.id.textView_name);
                textViewCompany = itemView.findViewById(R.id.textView_company);
            }

            void bind(Contact contact) {
                textViewName.setText(contact.getContactName());
                textViewCompany.setText(contact.getCompanyName());
            }
        }
    }
}
