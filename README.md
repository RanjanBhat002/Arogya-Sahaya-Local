# 🏥 Arogya-Sahaya Local
### *Project #78 — Android App Development using GenAI (Healthcare)*

> **A localized Digital Health Companion for rural elderly patients** — converting complex prescriptions into zero-error schedules, tracking vitals, and connecting patients with ASHA workers.

---

## 📋 Problem Statement

In most rural areas, healthcare follow-ups are often missed because elderly patients struggle to manage multiple medications and remember dates for health camps or ASHA worker visits. This leads to poor health outcomes and preventable complications.

**Arogya-Sahaya Local** solves this by providing a simple, accessible "Digital Health Companion" built for users who may not be tech-savvy.

---

## 🎯 Vision

Arogya-Sahaya Local is a **localized medication and wellness tracker** with a singular goal:

- ✅ **Zero-Error** medicine intake schedules
- ✅ Convert complex doctor prescriptions into **simple, time-based reminders**
- ✅ Track vital indicators like **blood pressure & glucose levels**
- ✅ Generate a **local health data log** patients can show ASHA workers on their next visit

---

## 📱 App Features & User Flow

| Feature | Description |
|---|---|
| 👤 **Medical Profile** | User enters basic info — age, chronic conditions |
| 💊 **Pill Reminder** | Add medicine names, dosage, and times (Morning / Afternoon / Night) |
| 📅 **ASHA Connect** | Calendar showing upcoming local health camp dates (simulated data) |
| 📊 **Vital Log** | Simple form to enter daily Blood Pressure or Heart Rate readings |
| 🆘 **Emergency Mode** | Large SOS button that triggers a simulated emergency call or message |

---

## 🛠️ Technical Implementation

### Architecture
- **Pattern:** Repository Pattern for clean data handling and separation of concerns
- **Database:** Room DB — stores medication history and vital logs locally on-device

### Key Components

```
app/
├── data/
│   ├── local/          # Room DB — Entities, DAOs
│   ├── repository/     # Repository implementations
│   └── model/          # Data models (Medication, VitalLog, Profile)
├── ui/
│   ├── profile/        # Medical Profile screen
│   ├── reminder/       # Pill Reminder screen
│   ├── asha/           # ASHA Connect calendar
│   ├── vitals/         # Vital Log + 7-day trend chart
│   └── emergency/      # SOS Emergency screen
└── worker/             # WorkManager / AlarmManager jobs
```

### Libraries & Tools

| Purpose | Library/Tool |
|---|---|
| Background Alarms | `WorkManager` / `AlarmManager` (Doze-mode safe) |
| Vitals Chart | `MPAndroidChart` — 7-day line chart |
| Local Storage | `Room DB` |
| UI Accessibility | High-contrast colors, large fonts (elderly-friendly) |

---

## ✅ Success Criteria

- [x] Notifications trigger accurately even when the device is in **"Doze" mode**
- [x] Vital Log generates a **7-day trend graph** using MPAndroidChart
- [x] UI uses **high-contrast colors and large fonts** for elderly accessibility
- [x] Project implements the **Repository Pattern** for all data handling

---

## 🌍 Impact Goals

| Goal | Description |
|---|---|
| 🏘️ **Health Inclusion** | Ensuring elderly in remote areas have the same medication adherence as urban dwellers |
| 📂 **Data-Driven Care** | Empowering ASHA workers with organized, readable patient history |
| 🛡️ **Preventative Health** | Reducing emergency hospitalizations through consistent daily monitoring |

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- Android SDK 26+
- Gradle 8.x

### Setup

```bash
# Clone the repository
git clone https://github.com/<your-username>/arogya-sahaya-local.git

# Open in Android Studio
# Sync Gradle dependencies
# Run on emulator or physical device (API 26+)
```

### Build

```bash
./gradlew assembleDebug
```

---

## 📸 Screenshots

> *(Add screenshots of Medical Profile, Pill Reminder, Vital Log chart, and SOS screen here)*

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you'd like to change.

---

## 📄 License

This project is developed as part of an academic GenAI Android Development initiative.

---

<div align="center">
  <sub>Built with ❤️ for rural healthcare accessibility · Project #78</sub>
</div>
