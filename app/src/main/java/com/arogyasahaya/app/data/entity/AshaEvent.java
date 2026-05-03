package com.arogyasahaya.app.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "asha_events")
public class AshaEvent {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "location")
    public String location;

    @ColumnInfo(name = "event_date")
    public long eventDate;  // timestamp

    @ColumnInfo(name = "event_type")
    public String eventType;  // "health_camp", "asha_visit", "vaccination"

    @ColumnInfo(name = "is_registered")
    public boolean isRegistered;

    public AshaEvent() {}

    public AshaEvent(String title, String description, String location, long eventDate, String eventType) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.isRegistered = false;
    }
}
