package termine24.secondinterviewtest.app.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import termine24.secondinterviewtest.R;
import termine24.secondinterviewtest.app.model.Contact;
import termine24.secondinterviewtest.app.DataModel;

/**
 * Created by shanemurphy on 22/12/2015.
 * Copyright (c) 2015 Shore. All rights reserved.
 */
public class DetailsActivity extends BaseActivity {

    private DetailsFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        if (detailFragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            detailFragment = new DetailsFragment();
            ft.add(R.id.content, detailFragment);
            ft.commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                detailFragment.editButtonPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * The fragment that displays the Details Screen
     */
    public static class DetailsFragment extends Fragment {

        //views
        private TextView nameTextView;
        private TextView emailTextView;
        private TextView phoneTextView;

        //data
        private Contact contact;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //load contact from data model
            contact = DataModel.getInstance(getActivity()).readContactFromFileSystem();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.content_details, container, false);
            nameTextView = (TextView) rootView.findViewById(R.id.nameTV);
            emailTextView = (TextView) rootView.findViewById(R.id.emailTV);
            phoneTextView = (TextView) rootView.findViewById(R.id.phoneTV);

            updateUIValues();
            return rootView;
        }


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(requestCode == Contact.REQUEST_EDIT_CONTACT && data != null && resultCode == Activity.RESULT_OK) {
                contact = data.getParcelableExtra(Contact.EXTRA_CONTACT);
                updateUIValues();
            }
        }

        private void updateUIValues() {
            if (contact != null) {
                nameTextView.setText(contact.getName());
                emailTextView.setText(contact.getEmail());
                phoneTextView.setText(contact.getMobile());
            }
        }


        private void editButtonPressed() {
            //Start Edit Activity and pass it the contact object that should be edited
            Intent intent = new Intent(getActivity(), EditActivity.class);
            intent.putExtra(Contact.EXTRA_CONTACT, (Parcelable)contact);
            startActivityForResult(intent, Contact.REQUEST_EDIT_CONTACT); // Call startActivityForResult without getActivity()
        }

    }

}
