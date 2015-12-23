package termine24.secondinterviewtest.app;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import termine24.secondinterviewtest.app.model.Contact;

/**
 * Created by shanemurphy on 23/12/2015.
 * Copyright (c) 2015 Shore. All rights reserved.
 */
public class DataModel {

    private static DataModel instance;
    private static Context ctx;

    private final String CONTACT_FILE_NAME = "contact";

    private DataModel(Context context) {
        ctx = context;
        initContactInFileSystem();
    }

    public static synchronized DataModel getInstance(Context context) {
        if (instance == null) {
            instance = new DataModel(context);
        }
        return instance;
    }


    private void initContactInFileSystem() {
        File file = new File(CONTACT_FILE_NAME);
        if(!file.exists()) {
            Contact contact = new Contact("Mr New Guy", "newguy@shore.com", "0123456789");
            storeContactInFileSystem(contact);
        }
    }


    public Contact readContactFromFileSystem() {
        try {
            FileInputStream fis = ctx.openFileInput(CONTACT_FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (Contact) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void storeContactInFileSystem(Contact contact) {
        if(contact == null) {
            ctx.deleteFile(CONTACT_FILE_NAME);
            return;
        }
        FileOutputStream fos = null;
        try {
            fos = ctx.openFileOutput(CONTACT_FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contact);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (fos != null) {
                try {
                    fos.close();
                }
                catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
