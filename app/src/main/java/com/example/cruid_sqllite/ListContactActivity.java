package com.example.cruid_sqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Collections;
import java.util.List;

public class ListContactActivity extends AppCompatActivity {
    RecyclerView recyclerViewContact;
    RecyclerView.LayoutManager layoutManager;
    ContactAdapter contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        recyclerViewContact = findViewById(R.id.rcvcontact);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewContact.setLayoutManager(layoutManager);
        ContactBDD contactBDD = new ContactBDD(this);
        List<Contact> listContact = contactBDD.getAllContact();
        contactAdapter = new ContactAdapter(this,listContact);
        recyclerViewContact.setAdapter(contactAdapter);

    }
}