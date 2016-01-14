package termine24.secondinterviewtest.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by shanemurphy on 23/12/2015.
 * Copyright (c) 2015 Shore. All rights reserved.
 */
public class Contact implements Parcelable, Serializable {

    public static final int REQUEST_EDIT_CONTACT = 10001;
    public static final String EXTRA_CONTACT = "Extra_Contact";

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    private String name;
    private String email;
    private String mobile;

    public Contact() {

    }

    public Contact(String name, String emailAddress, String phoneNumber) {
        this.name = name;
        this.email = emailAddress;
        this.mobile = phoneNumber;
    }

    private Contact(Parcel in){
        this.name = in.readString();
        this.email = in.readString();
        this.mobile = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(mobile);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailAddress) {
        this.email = emailAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String phoneNumber) {
        this.mobile = phoneNumber;
    }

    public boolean canMakePhoneCall() {
        return this.mobile != null && !this.mobile.isEmpty();
    }

    public boolean canMessage() {
        return this.email != null && !this.email.isEmpty();
    }

}
