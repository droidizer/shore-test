package termine24.secondinterviewtest.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by shanemurphy on 14/01/2016.
 * Copyright (c) 2016 Shore. All rights reserved.
 */
public class Contacts extends ArrayList<Contact> implements Parcelable {

    private static final long serialVersionUID = 1L;

    public Contacts(){
        super();
    }

    public Contacts(Parcel in) {
        in.readTypedList(this, Contact.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this);
    }

    public static final Parcelable.Creator<Contacts> CREATOR
            = new Parcelable.Creator<Contacts>() {
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };
}
