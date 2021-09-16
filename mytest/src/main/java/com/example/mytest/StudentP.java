package com.example.mytest;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentP implements Parcelable {
    String name;
    int age;
    char sex;

    public StudentP(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    protected StudentP(Parcel in) {
        name = in.readString();
        age = in.readInt();
        sex = (char) in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeInt((int) sex);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StudentP> CREATOR = new Creator<StudentP>() {
        @Override
        public StudentP createFromParcel(Parcel in) {
            return new StudentP(in);
        }

        @Override
        public StudentP[] newArray(int size) {
            return new StudentP[size];
        }
    };

    @Override
    public String toString() {
        return "StudentP{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
