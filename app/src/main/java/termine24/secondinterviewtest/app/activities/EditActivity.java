package termine24.secondinterviewtest.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import termine24.secondinterviewtest.R;
import termine24.secondinterviewtest.app.model.Contact;
import termine24.secondinterviewtest.app.DataModel;

/**
 * Created by shanemurphy on 22/12/2015.
 * Copyright (c) 2015 Shore. All rights reserved.
 */
public class EditActivity extends BaseActivity {

    private EditFragment editFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        if (editFragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            editFragment = new EditFragment();
            Intent intent = getIntent();
            if (intent != null) {
                Contact contact = intent.getParcelableExtra(Contact.EXTRA_CONTACT);
                if (contact != null) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Contact.EXTRA_CONTACT, contact);
                    editFragment.setArguments(bundle);
                }
            }
            ft.add(R.id.content, editFragment);
            ft.commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.hint_fix_bug, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                editFragment.saveButtonPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * The fragment that displays the Details Screen
     */
    public static class EditFragment extends Fragment {

        //views
        private EditText nameEditText;
        private EditText emailEditText;
        private EditText phoneEditText;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Bundle bundle = getArguments();
            Contact contact = null;
            if (bundle != null) {
                contact = bundle.getParcelable(Contact.EXTRA_CONTACT);
            }

            View rootView = inflater.inflate(R.layout.content_edit, container, false);
            nameEditText = (EditText) rootView.findViewById(R.id.nameET);
            emailEditText = (EditText) rootView.findViewById(R.id.emailET);
            phoneEditText = (EditText) rootView.findViewById(R.id.phoneET);

            updateUIValues(contact);
            return rootView;
        }


        private void updateUIValues(Contact contact) {
            if (contact != null) {
                int position = contact.getName().length();
                nameEditText.setText(contact.getName());
                nameEditText.setSelection(position);

                position = contact.getEmailAddress().length();
                emailEditText.setText(contact.getEmailAddress());
                emailEditText.setSelection(position);

                position = contact.getPhoneNumber().length();
                phoneEditText.setText(contact.getPhoneNumber());
                phoneEditText.setSelection(position);
            }
        }


        private void saveButtonPressed() {

            Contact editedContact = createContactFromComponents();
            if (editedContact != null) {
                //Save updated contact to the data model
                DataModel.getInstance(getActivity()).storeContactInFileSystem(editedContact);
            }
            //Send back result to detail so it can update it view
            Intent result = new Intent();
            result.putExtra(Contact.EXTRA_CONTACT, (Parcelable)editedContact);
            getActivity().setResult(Activity.RESULT_OK, result);
            getActivity().finish();
        }

        /**
         * Collect data from relevant UI components and creates a Contact object
         * If all data is available then return null
         * @return Contact or null
         */
        private Contact createContactFromComponents() {
            if (nameEditText == null || emailEditText == null ||
                    phoneEditText == null) {
                return null;
            }

            return new Contact(nameEditText.getText().toString(),
                    emailEditText.getText().toString(),
                    phoneEditText.getText().toString());
        }

    }
}
