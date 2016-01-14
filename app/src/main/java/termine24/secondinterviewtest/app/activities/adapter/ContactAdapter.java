package termine24.secondinterviewtest.app.activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import termine24.secondinterviewtest.R;
import termine24.secondinterviewtest.app.model.Contact;
import termine24.secondinterviewtest.app.model.Contacts;

/**
 * Created by shanemurphy on 14/01/2016.
 * Copyright (c) 2016 Shore. All rights reserved.
 */
public class ContactAdapter extends BaseAdapter {

    private Context context;
    private Contacts data;

    public ContactAdapter(Context context) {
        this.context = context;
        this.data = new Contacts();
    }

    public int getCount() {
       return this.data.size();
    }

    public Object getItem(int pos) {
        if (this.data.size() <= pos) {
            return this.data.get(pos);
        }
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        //TODO This is badly implemented. Please implement using a ViewHolder pattern.

        Contact contact = data.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contactView = inflater.inflate(R.layout.listitem_contact, parent, false);

        TextView nameTextView = (TextView) contactView.findViewById(R.id.listitem_contact_name);
        nameTextView.setText(contact.getName());
        TextView phoneTextView = (TextView) contactView.findViewById(R.id.listitem_contact_phone);
        phoneTextView.setText(contact.getMobile());
        TextView emailTextView = (TextView) contactView.findViewById(R.id.listitem_contact_email);
        emailTextView.setText(contact.getEmail());

        return contactView;
    }


    public void setContacts(List<Contact> contacts) {
        this.data.clear();
        if (contacts != null && !contacts.isEmpty()) {
            this.data.addAll(contacts);
        }
        notifyDataSetChanged();
    }
}
