package com.refactory.mytestcode.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

public class Listusers implements Parcelable {
    private String name,email, phone, address;

    public Listusers(JSONObject jsonObject) {
        try{

            String addressString = jsonObject.getString("address");
            JSONObject adrObj = new JSONObject(addressString);

            String res_street = adrObj.getString("street");
            String res_city = adrObj.getString("city");
            String res_ZipCode = adrObj.getString("zipcode");


            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String phone = jsonObject.getString("phone");
            String address = "Street :"+ res_street + "\nCity :"+ res_city+"\nZip Code : "+ res_ZipCode;

            this.name = name;
            this.email = email;
            this.phone = phone;
            this.address = address;
        }catch (Exception e){
            e.printStackTrace();
        }
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.address);
    }

    protected Listusers(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
    }

    public static final Parcelable.Creator<Listusers> CREATOR = new Parcelable.Creator<Listusers>() {
        @Override
        public Listusers createFromParcel(Parcel source) {
            return new Listusers(source);
        }

        @Override
        public Listusers[] newArray(int size) {
            return new Listusers[size];
        }
    };
}
