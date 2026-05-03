package com.arogyasahaya.app.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vital_logs")
public class VitalLog {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "systolic")
    public int systolic;       // BP upper value

    @ColumnInfo(name = "diastolic")
    public int diastolic;      // BP lower value

    @ColumnInfo(name = "heart_rate")
    public int heartRate;

    @ColumnInfo(name = "glucose_level")
    public int glucoseLevel;   // mg/dL

    @ColumnInfo(name = "weight")
    public float weight;       // kg

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "recorded_at")
    public long recordedAt;

    public VitalLog() {
        this.recordedAt = System.currentTimeMillis();
    }

    public String getBPText() {
        if (systolic > 0 && diastolic > 0) {
            return systolic + "/" + diastolic + " mmHg";
        }
        return "Not recorded";
    }

    public String getBPStatus() {
        if (systolic == 0) return "Unknown";
        if (systolic < 120 && diastolic < 80) return "Normal";
        if (systolic < 130 && diastolic < 80) return "Elevated";
        if (systolic < 140 || diastolic < 90) return "High Stage 1";
        return "High Stage 2";
    }

    public String getHeartRateText() {
        if (heartRate > 0) return heartRate + " BPM";
        return "Not recorded";
    }

    public String getGlucoseText() {
        if (glucoseLevel > 0) return glucoseLevel + " mg/dL";
        return "Not recorded";
    }

    public String getGlucoseStatus() {
        if (glucoseLevel == 0) return "Unknown";
        if (glucoseLevel < 70) return "Low";
        if (glucoseLevel <= 140) return "Normal";
        if (glucoseLevel <= 200) return "Pre-diabetic";
        return "High";
    }
}
