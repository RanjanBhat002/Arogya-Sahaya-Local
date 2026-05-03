package com.arogyasahaya.app.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medications")
public class Medication {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "dosage")
    public String dosage;

    @ColumnInfo(name = "morning")
    public boolean morning;

    @ColumnInfo(name = "afternoon")
    public boolean afternoon;

    @ColumnInfo(name = "night")
    public boolean night;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "is_active")
    public boolean isActive = true;

    @ColumnInfo(name = "created_at")
    public long createdAt;

    public Medication() {
        this.createdAt = System.currentTimeMillis();
    }

    public Medication(String name, String dosage, boolean morning, boolean afternoon, boolean night) {
        this.name = name;
        this.dosage = dosage;
        this.morning = morning;
        this.afternoon = afternoon;
        this.night = night;
        this.isActive = true;
        this.createdAt = System.currentTimeMillis();
    }

    public String getScheduleText() {
        StringBuilder sb = new StringBuilder();
        if (morning) sb.append("Morning");
        if (afternoon) {
            if (sb.length() > 0) sb.append(" · ");
            sb.append("Afternoon");
        }
        if (night) {
            if (sb.length() > 0) sb.append(" · ");
            sb.append("Night");
        }
        return sb.toString();
    }
}
