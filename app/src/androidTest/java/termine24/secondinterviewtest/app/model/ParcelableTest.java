package termine24.secondinterviewtest.app.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.test.AndroidTestCase;

/**
 * Created by shanemurphy on 23/12/2015.
 * Copyright (c) 2015 Shore. All rights reserved.
 */
public class ParcelableTest extends AndroidTestCase {

    private Parcelable p = null;

    public ParcelableTest(Parcelable p) {
        this.p = p;
    }

    public ParcelableTest(){

    }

    public void testDescribeContents() throws Exception {
        if (p != null) {
            assertEquals("Describe Contents is not 0", 0, p.describeContents());
        }

    }

    public void testEmptyAttachment() throws Exception {
        if (p != null) {
            assertNotNull("Object is null", p);
        }
    }

    protected Parcel createParcel(Parcelable parcelable){
        Parcel p = Parcel.obtain();
        parcelable.writeToParcel(p, 0);
        p.setDataPosition(0);
        return p;
    }
}