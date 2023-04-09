package com.example.cruid_sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AjoutContact extends AppCompatActivity {
    EditText editNom,editNum;
    Button sauvegarder;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_contact);
        editNom=(EditText)findViewById(R.id.nom);
        editNum=(EditText)findViewById(R.id.num);
        sauvegarder =(Button)findViewById(R.id.sauvegarder);
        sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editNom.getText().toString().equals("")){
                    editNom.setError("Invalide!");
                    editNom.requestFocus();
                    return;
                }
                if (editNum.getText().toString().equals("")){
                    editNum.setError("Invalide!");
                    editNum.requestFocus();
                    return;
                }
                ContactBDD db = new ContactBDD(AjoutContact.this);
                Contact c = new Contact(editNom.getText().toString(), editNum.getText().toString());
                long r = db.addContact(c);
                if (r != -1)
                    Toast.makeText(AjoutContact.this, "Ajout avec succes", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AjoutContact.this, "Problème dans l'ajout", Toast.LENGTH_LONG).show();
            }
        });
    }
}