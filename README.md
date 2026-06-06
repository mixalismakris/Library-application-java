# Library-application-java
Console-based library management system built in Java 17, demonstrating OOP principles (inheritance, polymorphism, interfaces) with object serialization for persistent storage.

# Library Management System

A console-based university library management application written in Java, demonstrating core object-oriented programming principles and file-based data persistence through Java serialization.

This project was developed as part of a 2nd-semester Object-Oriented Programming course and consists of two stages: the initial OOP implementation and a follow-up extension that adds persistent storage.

## Features

- Add and manage library items (books, magazines, digital books)
- Register library members (students and professors)
- Search for items by code, title, or author
- Borrow and return items, with availability tracking
- View active loans
- Display library statistics
- Automatic data loading on startup and saving on exit
- Manual save option from the menu

## Tech Stack

- **Language:** Java 17
- **Persistence:** Java Object Serialization (`ObjectOutputStream` / `ObjectInputStream`)
- **No external dependencies** — runs with a standard JDK

## Project Structure

```
src/gr/university/library/
├── Main.java                       # Application entry point and menu
├── contracts/
│   ├── Borrowable.java             # Interface for borrowable items
│   └── Searchable.java             # Interface for searchable entities
├── model/
│   ├── LibraryItem.java            # Abstract base for library materials
│   ├── Book.java
│   ├── Magazine.java
│   ├── DigitalBook.java            # Extends Book
│   ├── Member.java                 # Abstract base for members
│   ├── StudentMember.java
│   ├── ProfessorMember.java
│   └── Loan.java
├── service/
│   └── Library.java                # Core domain logic
├── persistence/
│   └── LibraryStorage.java         # Save / load utility (final class)
└── util/
    └── ReportGenerator.java        # Statistics and reports (final class)
```

## OOP Concepts Demonstrated

- **Inheritance** with two chains (`DigitalBook → Book → LibraryItem`, `StudentMember/ProfessorMember → Member`)
- **Polymorphism** through interfaces and abstract methods
- **Abstract classes** (`LibraryItem`, `Member`)
- **Interfaces** (`Borrowable`, `Searchable`)
- **Encapsulation** via private fields with validating setters
- **Access modifiers** including `protected`, `private`, `public`, and package-private
- **Static members** for counters and constants (`DEFAULT_LOAN_DAYS`)
- **`final` utility classes** with private constructors (`ReportGenerator`, `LibraryStorage`)
- **Exception handling** with `IllegalArgumentException`, `IOException`, `ClassNotFoundException`, and `NumberFormatException`

## How to Run

### Requirements

- JDK 17 or newer

### From the command line

```bash
cd "Library Java"
javac -encoding UTF-8 -d out -cp src $(find src -name "*.java")
java -cp out gr.university.library.Main
```

### From VS Code

Open the project, then open `Main.java` and click **Run** above the `main` method (requires the Extension Pack for Java).

## Data Persistence

On startup, the application attempts to load existing data from `library.ser`. If the file does not exist (first run), an empty library is created. When the user exits via option `0`, or manually saves via option `8`, the entire library state (members, items, loans) is written to disk through Java serialization.

The application gracefully handles:
- Missing data file on first run
- Corrupted or incompatible save files
- Invalid input during data entry

## Menu Options

```
1. Add library item
2. Add member
3. Search library item
4. Borrow library item
5. Return library item
6. Show active loans
7. Library statistics
8. Save data
0. Exit and save
```

## Notes

- Data is stored in plain serialized form; no encryption is applied.
- The save file is environment-specific and is not included in the repository.
- This is a coursework project intended for educational purposes.

## Author

Mixalis Makrygiannakis — University of Piraeus
