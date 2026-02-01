# LearnTrack Management System

A Java-based learning management system for managing students, trainers, and courses. Built with object-oriented programming principles, comprehensive error handling, and thread-safe services.

## Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Usage](#usage)
- [Architecture](#architecture)
- [Design Patterns](#design-patterns)
- [Exception Handling](#exception-handling)
- [Thread Safety](#thread-safety)

## Features

### Student Management

- Add new students with auto-generated unique IDs
- View all students or search by ID
- Update student course and batch information
- Remove students from the system
- Track student active status

### Trainer Management

- Register trainers with expertise areas
- Update trainer expertise and availability
- View all trainers or retrieve by ID
- Manage trainer availability status
- Email validation for trainers

### Course Management

- Create courses with auto-generated IDs
- View all courses or search by ID
- Deactivate/activate courses
- Update course details (name, description, duration)
- Validate course duration (1-104 weeks)

## 📁 Project Structure

```
Project/
├── README.md
└── src/
    └── com/airtribe/learntrack/
        ├── entity/              # Domain entities
        │   ├── Person.java      # Base class for Student & Trainer
        │   ├── Student.java     # Student entity
        │   ├── Trainer.java     # Trainer entity
        │   └── Course.java      # Course entity
        ├── services/            # Business logic layer
        │   ├── IStudentService.java
        │   ├── StudentService.java
        │   ├── ITrainerService.java
        │   ├── TrainerService.java
        │   ├── ICourseService.java
        │   └── CourseService.java
        ├── exceptions/          # Custom exception classes
        │   ├── Exceptions.java
        │   ├── EntityNotFoundException.java
        │   └── InvalidInputException.java
        ├── idGenerator/         # ID generation utilities
        │   ├── IIdGenerator.java
        │   ├── StudentIdCounter.java
        │   ├── TrainerIdCounter.java
        │   └── CourseIdCounter.java
        └── ui/                  # User interface
            └── Main.java        # Console-based UI
```

## Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **Terminal/Command Prompt**: For compilation and execution

## Installation & Setup

### 1. Clone or Download the Project

```bash
cd /path/to/Project
```

### 2. Compile the Project

From the project root directory:

```bash
# Compile all Java files
javac -d src/com/airtribe/learntrack/**/*.java
```

### 3. Run the Application

```bash
# Run from the bin directory
cd bin
java src.com.airtribe.learntrack.ui.Main
```

Or from the project root:

```bash
java -cp bin src.com.airtribe.learntrack.ui.Main
```

## Usage

### Main Menu

Upon launching the application, you'll see:

```
== ** Welcome to LearnTrack Management System ** ==
1. Manage Students
2. Manage Trainers
3. Manage Courses
4. Exit
```

### Student Management Workflow

1. **Add Student**
   - Provide: first name, last name, age, course name, batch
   - System auto-generates unique student ID (format: `STUD_XXX`)

2. **View All Students**
   - Displays complete list with all details

3. **Get Student by ID**
   - Search using student ID
   - Returns full student information

4. **Update Student Details**
   - Modify course and batch information
   - Update active status

5. **Remove Student**
   - Permanently delete student record

### Trainer Management Workflow

1. **Add Trainer**
   - Provide: first name, last name, email, age, expertise
   - System auto-generates unique trainer ID (format: `TRAINR_XXX`)
   - Email validation included

2. **View All Trainers**
   - Lists all registered trainers

3. **Update Trainer Expertise**
   - Modify trainer's area of expertise

4. **Set Availability**
   - Mark trainer as available/unavailable

5. **Remove Trainer**
   - Delete trainer from system

### Course Management Workflow

1. **Add Course**
   - Provide: course name, description, duration (in weeks)
   - System auto-generates unique course ID (format: `CRS_XXX`)
   - Duration must be between 1-104 weeks

2. **View All Courses**
   - Display all courses with status

3. **Deactivate/Activate Course**
   - Change course availability status

4. **Remove Course**
   - Permanently delete course

## Architecture

### Entity Layer

**Person (Abstract Base Class)**

- Common attributes: firstName, lastName, age
- Provides foundation for Student and Trainer entities
- Implements input validation

**Student**

- Extends `Person`
- Additional fields: studentId (immutable), course, batch, isActive
- Overrides: `equals()`, `hashCode()`, `toString()`

**Trainer**

- Extends `Person`
- Additional fields: trainerId (immutable), expertise, email, isAvailable
- Email format validation

**Course**

- Independent entity
- Fields: courseId (immutable), courseName, description, durationInWeeks, isActive
- Duration constraints: 1-104 weeks

### Service Layer

All services implement the **Singleton pattern** for single instance management:

- **StudentService**: CRUD operations for students
- **TrainerService**: CRUD operations for trainers
- **CourseService**: CRUD operations for courses

**Service Interfaces:**

- `IStudentService`
- `ITrainerService`
- `ICourseService`

### ID Generation

Each entity type has a dedicated ID generator:

- **StudentIdCounter**: Generates `STUD_001`, `STUD_002`, ...
- **TrainerIdCounter**: Generates `TRAINR_001`, `TRAINR_002`, ...
- **CourseIdCounter**: Generates `CRS_001`, `CRS_002`, ...

All implement `IIdGenerator` interface for consistency.

## Design Patterns

### 1. Singleton Pattern

- Applied to all service classes
- Ensures single instance per service type
- Thread-safe implementation

### 2. Interface Segregation

- Service interfaces define contracts
- Promotes loose coupling and testability

### 3. Inheritance Hierarchy

- `Person` as base class for `Student` and `Trainer`
- Code reuse for common attributes and behavior

### 4. Immutability

- Entity IDs are final (cannot be changed after creation)
- Prevents accidental ID modifications

## Exception Handling

### Custom Exceptions

**EntityNotFoundException**

- Thrown when requested entity doesn't exist
- Used in: `getStudentById()`, `getCourseById()`, `getTrainerById()`

**InvalidInputException**

- Thrown for invalid or malformed input
- Validates: null values, empty strings, invalid formats

**EnrollmentException**

- Thrown for enrollment-related errors
- Used when duplicate enrollments or capacity issues occur

### Validation

All entities perform validation in:

- Constructors (during object creation)
- Setters (during updates)

Common validations:

- Null checks
- Empty string checks
- Format validation (email, duration range)
- Trim whitespace automatically

##  Thread Safety

### Synchronized Collections

All services use `Collections.synchronizedList()` for thread-safe list operations.

### Synchronized Methods

Critical service methods are synchronized to prevent race conditions:

- `addStudent()`, `addTrainer()`, `addCourse()`
- `removeStudent()`, `removeTrainer()`, `removeCourse()`
- All update operations

### Unmodifiable Views

Read operations return unmodifiable collections to prevent external modification:

```java
return Collections.unmodifiableList(new ArrayList<>(students));
```

## Code Quality Features

### JavaDoc Documentation

- All classes, methods, and public APIs documented
- Clear parameter and return value descriptions

### Input Validation

- Comprehensive validation at entity and service layers
- Meaningful error messages for debugging

### Clean Code Principles

- Single Responsibility Principle
- DRY (Don't Repeat Yourself)
- Meaningful variable and method names
- Consistent code formatting

### Method Overrides

All entities implement:

- `equals()` - Based on unique ID
- `hashCode()` - Consistent with equals
- `toString()` - Human-readable representation

