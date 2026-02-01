# LearnTrack Design Notes

This document explains key design decisions made in the LearnTrack Management System.

## Table of Contents

- [Why ArrayList Instead of Array](#why-arraylist-instead-of-array)
- [Static Members Usage](#static-members-usage)
- [Inheritance Implementation](#inheritance-implementation)

---

## Why ArrayList Instead of Array

### Decision

Throughout the LearnTrack system, we chose to use `ArrayList<T>` for storing collections of entities (students, trainers, courses) instead of traditional arrays.

### Locations

- `StudentService.java`: `List<Student> students = Collections.synchronizedList(new ArrayList<>())`
- `TrainerService.java`: `List<Trainer> trainers = Collections.synchronizedList(new ArrayList<>())`
- `CourseService.java`: `List<Course> courses = Collections.synchronizedList(new ArrayList<>())`

### Rationale

#### 1. **Dynamic Sizing**

Arrays in Java have a fixed size that must be declared at creation time. In a learning management system, the number of students, trainers, and courses will vary over time as entities are added and removed.

```java
// With arrays (inflexible):
Student[] students = new Student[100]; // What if we need 101 students?

// With ArrayList (flexible):
List<Student> students = new ArrayList<>(); // Grows automatically
```

#### 2. **Ease of Manipulation**

ArrayList provides built-in methods for common operations that would require manual implementation with arrays:

- **Adding elements**: `list.add(student)` vs manual array index management
- **Removing elements**: `list.removeIf(s -> s.getId().equals(id))` vs array shifting
- **Searching**: `list.stream().filter(...)` vs manual iteration

#### 3. **Size Management**

ArrayList automatically manages its internal capacity and resizes when needed. With arrays, we would need to:

- Track the current number of elements separately
- Manually create larger arrays and copy elements when capacity is exceeded
- Waste memory by over-allocating to avoid frequent resizing

#### 4. **Type Safety**

Using generics with ArrayList provides compile-time type safety:

```java
List<Student> students = new ArrayList<>(); // Can only hold Student objects
students.add(new Student(...)); // Type-safe
// students.add(new Trainer(...)); // Compile error
```

#### 5. **Integration with Collections Framework**

ArrayList works seamlessly with Java's Collections Framework, enabling:

- Thread-safe wrappers: `Collections.synchronizedList(new ArrayList<>())`
- Unmodifiable views: `Collections.unmodifiableList(students)`
- Sorting, filtering, and other operations via Stream API

#### 6. **Memory Efficiency in Practice**

While arrays are slightly more memory-efficient in theory, ArrayList's overhead is minimal (typically just the list structure itself). In our application, the flexibility benefits far outweigh the negligible memory cost.


### Conclusion

For a management system where the number of entities is unknown and will change frequently, ArrayList is the clear choice. It provides flexibility, ease of use, and better maintainability without sacrificing performance for our use case.

---

## Static Members Usage

### Overview

Static members (fields and methods) are used strategically throughout the LearnTrack system in specific scenarios where they provide clear benefits.


**Why Static?**

- **Single Instance**: The `INSTANCE` field must be static to ensure only one instance exists across the entire application
- **Global Access Point**: The static `getInstance()` method provides a globally accessible way to retrieve the singleton instance
- **Thread Safety**: The static field is initialized when the class is loaded, ensuring thread-safe lazy initialization

**Benefits**:

- Guarantees only one service instance manages each entity collection
- Prevents data inconsistency from multiple service instances
- Reduces memory footprint by avoiding duplicate instances

---
## Inheritance Implementation

### Overview

Inheritance is used in the LearnTrack system to create a hierarchical relationship between the abstract `Person` class and its concrete subclasses `Student` and `Trainer`.

### Class Hierarchy

```
            Person 
              ▲
              |
      ┌───────┴───────┐
      |               |
   Student         Trainer
```
