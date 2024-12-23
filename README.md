# Note Keeper App

A modern Android note-taking application built with Jetpack Compose and following Android architecture best practices.

## Features

- Create and delete notes with title and content
- Real-time search functionality
- Sort notes by:
  - Title (Ascending/Descending)
  - Date (Ascending/Descending)
- Search highlighting in both title and content
- Material 3 design implementation
- Persistent storage using Room database

## Architecture & Technical Stack

### Architecture Components
- MVVM (Model-View-ViewModel) architecture
- Repository pattern
- Single activity pattern
- Clean Architecture principles

### Libraries & Technologies
- **Jetpack Compose**: Modern Android UI toolkit
- **Room**: Local database storage
- **Kotlin Coroutines**: Asynchronous programming
- **Kotlin Flow**: Reactive programming
- **ViewModel**: UI state management
- **Material 3**: Modern design system

## Project Structure

```
app/src/main/java/com/example/notekeeper/
├── data/
│   ├── Note.kt           # Data entity
│   ├── NoteDao.kt        # Data Access Object
│   ├── NoteDatabase.kt   # Room database
│   ├── NoteRepository.kt # Repository layer
│   └── SortType.kt       # Sort type enum
├── ui/
│   ├── components/
│   │   └── NoteItem.kt   # Reusable note component
│   ├── screens/
│   │   └── NoteScreen.kt # Main screen
│   └── viewmodel/
│       └── NoteViewModel.kt
├── MainActivity.kt
└── NoteApplication.kt
```

## Setup Instructions

1. Clone the repository
2. Open the project in Android Studio
3. Add the following dependencies to your `build.gradle.kts`:

```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

dependencies {
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
}
```

4. Sync the project with Gradle files
5. Run the app

## Requirements

- Minimum SDK: 24
- Target SDK: 34
- Compile SDK: 35
- Kotlin version: 1.9.0
- Android Studio Hedgehog or newer

## Architecture Overview

### Data Flow
1. UI Layer (Compose) → Collects state from ViewModel
2. ViewModel → Manages UI state and business logic
3. Repository → Single source of truth for data
4. Room Database → Persistent storage

### Key Components

#### Note Entity
```kotlin
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)
```

#### Search Implementation
- Real-time search using Flow
- Case-insensitive matching
- Highlights matching text
- Maintains sort order during search

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Acknowledgments

- Material Design 3 Guidelines
- Android Jetpack Documentation
- Room Database Documentation
