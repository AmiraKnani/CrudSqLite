package com.example.cruid_sqllite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.StringSearch;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    private Context context;
    private List<Contact> listContact;

    public ContactAdapter(Context context, List<Contact> listContact) {
        this.context = context;
        this.listContact = listContact;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitem,parent, false);
        ContactViewHolder ContactViewHolder = new ContactViewHolder(view);
        return ContactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Contact contact = listContact.get(position);

        holder.Nom.setText(contact.getNom());
        holder.Number.setText(contact.getNumber());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation supression");
                builder.setMessage("Voulez vous vraiment supprimer défintivement le contact de l'id' :" + listContact.get(position).getId()+listContact.get(position).getNumber()+ "de la liste des contacts");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (position >= 0 && position < listContact.size()) {
                            listContact.remove(position);
                            notifyItemRemoved(position);
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return listContact.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{

        TextView Nom;
        TextView Number;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            Nom = itemView.findViewById(R.id.Nom);
            Number= itemView.findViewById(R.id.Number);
        }
    }
}
