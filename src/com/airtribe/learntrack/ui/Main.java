package src.com.airtribe.learntrack.ui;

import java.util.*;

import src.com.airtribe.learntrack.idGenerator.CourseIdCounter;
import src.com.airtribe.learntrack.idGenerator.StudentIdCounter;
import src.com.airtribe.learntrack.idGenerator.TrainerIdCounter;
import src.com.airtribe.learntrack.services.StudentService;
import src.com.airtribe.learntrack.entity.Student;
import src.com.airtribe.learntrack.entity.Trainer;
import src.com.airtribe.learntrack.services.TrainerService;
import src.com.airtribe.learntrack.services.CourseService;
import src.com.airtribe.learntrack.entity.Course;

public class Main {

    private static final StudentService studentService = StudentService.getInstance();
    private static final TrainerService trainerService = TrainerService.getInstance();
    private static final CourseService courseService = CourseService.getInstance();

    public static void showMenu() {
        System.out.println("== ** Welcome to LearnTrack Management System ** ==");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Trainers");
        System.out.println("3. Manage Courses");
        System.out.println("4. Exit");
    }

    public static void showTrainerMenu() {
        System.out.println("== ** Trainer Management ** ==");
        System.out.println("1. Add Trainer");
        System.out.println("2. View All Trainers");
        System.out.println("3. Get Trainer by ID");
        System.out.println("4. Update Trainer Expertise");
        System.out.println("5. Set Trainer Availability");
        System.out.println("6. Remove Trainer");
        System.out.println("7. Back to Main Menu");
    }

    public static void showCourseMenu() {
        System.out.println("== ** Course Management ** ==");
        System.out.println("1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Get Course by ID");
        System.out.println("4. Remove Course");
        System.out.println("5. Deactivate Course");
        System.out.println("6. Back to Main Menu");
    }

    public static void showStudentMenu() {
        System.out.println("== ** Student Management ** ==");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Get Student by ID");
        System.out.println("4. Update Student Details");
        System.out.println("5. Remove Student");
        System.out.println("6. Back to Main Menu");
    }

    // Student List
    public static void getAllStudentsList() {
        System.out.println();
        for (Student student : studentService.getAllStudents()) {
            System.out.println(student);
        }
        System.out.println();
    }

    // Trainer List
    public static void getAllTrainersList() {
        System.out.println();
        for (Trainer trainer : trainerService.getAllTrainers()) {
            System.out.println(trainer);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            showMenu();
            System.out.print("Select an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1: {
                    // Handle Student Management
                    showStudentMenu();
                    System.out.print("Select an option: ");
                    int stChoice = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    switch (stChoice) {
                        case 1:
                            System.out.println("== ** Enter the student information ** ==");
                            System.out.print("Enter first name: ");
                            String firstName = sc.next();
                            System.out.print("Enter last name: ");
                            String lastName = sc.next();
                            System.out.print("Enter age: ");
                            int age = sc.nextInt();
                            String studId = StudentIdCounter.generateStudentId();
                            System.out.print("Enter the course name: ");
                            String course = sc.next();
                            System.out.print("Enter the batch: ");
                            String batch = sc.next();

                            Student newStudent = new Student(firstName, lastName, age, studId, course, batch);
                            System.out.println("Adding student...");
                            try {
                                studentService.addStudent(newStudent);
                            } catch (Exception e) {
                                System.out.println("Error adding student: " + e.getMessage());
                            }
                            System.out.println("Student added successfully");
                            System.out.println("STUDENT ID: " + studId);
                            break;
                        case 2:
                            System.out.println("Listing all students...");
                            getAllStudentsList();
                            break;
                        case 3:
                            System.out.println("Getting student by ID...");
                            System.out.print("Enter student ID: ");
                            String studentId = sc.next();
                            try {
                                Student student = studentService.getStudentById(studentId);
                                System.out.println("Student found: " + student);
                            } catch (Exception e) {
                                System.out.println("Error retrieving student: " + e.getMessage());
                            }
                            break;
                        case 4:
                            System.out.println("Updating student details...");
                            System.out.print("Enter student ID: ");
                            String updateStudentId = sc.next();
                            try {
                                Student student = studentService.getStudentById(updateStudentId);
                                System.out.println("Current student details: " + student);
                                System.out.print("Enter new course name: ");
                                String newCourse = sc.next();
                                System.out.print("Enter new batch: ");
                                String newBatch = sc.next();
                                studentService.updateStudent(updateStudentId, newCourse, newBatch, true);
                                System.out.println("Student details updated successfully.");
                            } catch (Exception e) {
                                System.out.println("Error updating student: " + e.getMessage());
                            }
                            break;
                        case 5:
                            // Remove Student
                            System.out.println("Removing a student...");
                            System.out.print("Enter student ID to remove: ");
                            String removeStudentId = sc.next();
                            try {
                                studentService.removeStudent(removeStudentId);
                                System.out.println("Student removed successfully.");
                            } catch (Exception e) {
                                System.out.println("Error removing student: " + e.getMessage());
                            }
                            break;
                        case 6:
                            System.out.println("Returning to main menu...");
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }

                    break;
                }
                case 2: {
                    showTrainerMenu();
                    System.out.print("Select an option: ");
                    int trChoice = sc.nextInt();
                    switch (trChoice) {
                        case 1:
                            System.out.println("Adding a new trainer...");
                            System.out.print("Enter trainer first name: ");
                            String trfirstName = sc.next();
                            System.out.print("Enter trainer last name: ");
                            String trlastName = sc.next();
                            System.out.print("Enter trainer email: ");
                            String trEmail = sc.next();
                            System.out.print("Enter trainer age: ");
                            int trAge = sc.nextInt();
                            System.out.print("Enter trainer expertise: ");
                            String trExpertise = sc.next();
                            String trId = TrainerIdCounter.generateTrainerId();

                            Trainer trainer = new Trainer(trfirstName, trlastName, trAge, trId, trExpertise, trEmail);
                            System.out.println("Adding trainer...");
                            try {
                                trainerService.addTrainer(trainer);
                                System.out.println("Trainer added successfully");
                                System.out.println("TRAINER ID: " + trId);
                            } catch (Exception e) {
                                System.out.println("Error adding trainer: " + e.getMessage());
                            }
                            break;
                        case 2:
                            System.out.println("Listing all trainers...");
                            getAllTrainersList();
                            break;
                        case 3:
                            System.out.println("Getting trainer by ID...");
                            System.out.print("Enter trainer ID: ");
                            String trainerId = sc.next();
                            try {
                                Trainer tr = trainerService.getTrainerById(trainerId);
                                System.out.println("Trainer found: " + tr);
                            } catch (Exception e) {
                                System.out.println("Error retrieving trainer: " + e.getMessage());
                            }
                            break;
                        case 4:
                            System.out.println("Updating trainer expertise...");
                            System.out.print("Enter trainer ID: ");
                            String updateTrainerId = sc.next();
                            System.out.print("Enter new expertise: ");
                            String newExpertise = sc.next();
                            try {
                                trainerService.updateTrainerExpertise(updateTrainerId, newExpertise);
                                System.out.println("Trainer expertise updated successfully.");
                            } catch (Exception e) {
                                System.out.println("Error updating trainer: " + e.getMessage());
                            }
                            break;
                        case 5:
                            System.out.println("Setting trainer availability...");
                            System.out.print("Enter trainer ID: ");
                            String availTrainerId = sc.next();
                            System.out.print("Is the trainer available? (true/false): ");
                            boolean availability = sc.nextBoolean();
                            try {
                                trainerService.setTrainerAvailability(availTrainerId, availability);
                                System.out.println("Trainer availability updated successfully.");
                            } catch (Exception e) {
                                System.out.println("Error updating availability: " + e.getMessage());
                            }
                            break;
                        case 6:
                            System.out.println("Removing a trainer...");
                            System.out.println("Enter trainer ID to remove: ");
                            String removeTrainerId = sc.next();
                            try {
                                trainerService.removeTrainer(removeTrainerId);
                                System.out.println("Trainer removed successfully.");
                            } catch (Exception e) {
                                System.out.println("Error removing trainer: " + e.getMessage());
                            }
                            break;
                        case 7:
                            System.out.println("Returning to main menu...");
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                    break;
                }
                case 3: {
                    showCourseMenu();
                    System.out.print("Select an option: ");
                    int courseChoice = sc.nextInt();
                    switch (courseChoice) {
                        case 1:
                            System.out.println("Adding a new course...");
                            String courseId = CourseIdCounter.generateCourseId();
                            System.out.print("Enter course name: ");
                            String courseName = sc.next();
                            System.out.print("Enter course description: ");
                            String description = sc.next();
                            System.out.print("Enter course duration in weeks: ");
                            int duration = sc.nextInt();
                            try {
                                Course cou = new Course(courseId, courseName, description, duration);
                                courseService.addCourse(cou);
                                System.out.println("Course added successfully.");
                            } catch (Exception e) {
                                System.out.println("Error adding course: " + e.getMessage());
                            }
                            break;
                        case 2:
                            System.out.println("Viewing all courses...");
                            try {
                                List<Course> courses = courseService.getAllCourses();
                                for (Course c : courses) {
                                    System.out.println(c);
                                }
                            } catch (Exception e) {
                                System.out.println("Error retrieving courses: " + e.getMessage());
                            }
                            break;
                        case 3:
                            System.out.println("Getting a specific course...");
                            System.out.print("Enter course ID: ");
                            String getCourseId = sc.next();
                            try {
                                Course c = courseService.getCourseById(getCourseId);
                                if (c != null) {
                                    System.out.println(c);
                                } else {
                                    System.out.println("Course not found.");
                                }
                            } catch (Exception e) {
                                System.out.println("Error retrieving course: " + e.getMessage());
                            }
                            break;
                        case 4:
                            System.out.println("Removing a course...");
                            System.out.print("Enter course ID to remove: ");
                            String removeCourseId = sc.next();
                            try {
                                courseService.removeCourse(removeCourseId);
                                System.out.println("Course removed successfully.");
                            } catch (Exception e) {
                                System.out.println("Error removing course: " + e.getMessage());
                            }
                            break;
                        case 5:
                            System.out.println("Deactivating a course...");
                            System.out.print("Enter course ID to deactivate: ");
                            String deactivateCourseId = sc.next();
                            try {
                                courseService.deactivateCourse(deactivateCourseId);
                                System.out.println("Course deactivated successfully.");
                            } catch (Exception e) {
                                System.out.println("Error deactivating course: " + e.getMessage());
                            }
                            break;
                        case 6:
                            System.out.println("Returning to main menu...");
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                    break;
                }
                case 4:
                    exit = true;
                    System.out.println("Exiting LearnTrack Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        sc.close();
    }

}