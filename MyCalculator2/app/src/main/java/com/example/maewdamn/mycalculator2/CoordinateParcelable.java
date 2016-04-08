package com.example.maewdamn.mycalculator2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Maewdamn on 7/4/2559.
 */
public class CoordinateParcelable implements Parcelable {

    public int x, y, z;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
        dest.writeInt(z);
    }

    protected CoordinateParcelable(Parcel in) {
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
    }

    public static final Creator<CoordinateParcelable> CREATOR = new Creator<CoordinateParcelable>() {

        @Override
        public CoordinateParcelable createFromParcel(Parcel source) {
            return new CoordinateParcelable(source);
        }

        @Override
        public CoordinateParcelable[] newArray(int size) {
            return new CoordinateParcelable[size];
        }
    };

    public CoordinateParcelable() {

    }
}
