package com.arogyasahaya.app.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_profile")
public class UserProfile {
    @PrimaryKey
    public int id = 1; // Single profile

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "gender")
    public String gender;

    @ColumnInfo(name = "blood_group")
    public String bloodGroup;

    @ColumnInfo(name = "chronic_conditions")
    public String chronicConditions;

    @ColumnInfo(name = "allergies")
    public String allergies;

    @ColumnInfo(name = "emergency_contact_name")
    public String emergencyContactName;

    @ColumnInfo(name = "emergency_contact_phone")
    public String emergencyContactPhone;

    @ColumnInfo(name = "asha_worker_name")
    public String ashaWorkerName;

    @ColumnInfo(name = "asha_worker_phone")
    public String ashaWorkerPhone;

    public UserProfile() {}
}
