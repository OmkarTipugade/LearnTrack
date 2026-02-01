package src.com.airtribe.learntrack.entity;

import java.util.Objects;

public class Student extends Person {

    private final String studentId;

    private String course;
    private String batch;

    // Active status of the student
    private boolean isActive;

    /**
     * Creates a new Student and initializes all required fields.
     */
    public Student(String firstName, String lastName, int age,
                   String studentId, String course, String batch) {

        // Initialize Person fields
        super(firstName, lastName, age);

        validateStudentId(studentId);
        validateCourse(course);
        validateBatch(batch);

        this.studentId = studentId.trim();
        this.course = course.trim();
        this.batch = batch.trim();
        this.isActive = true;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        validateCourse(course);
        this.course = course.trim();
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        validateBatch(batch);
        this.batch = batch.trim();
    }

    public boolean isActive() {
        return isActive;
    }

    public void activateStudent() {
        this.isActive = true;
    }

    public void deactivateStudent() {
        this.isActive = false;
    }

    /**
     * Returns student-specific information.
     */
    @Override
    public String getPersonInfo() {
        return "Student ID: " + studentId +
               ", Name: " + getName() +
               ", Age: " + getAge() +
               ", Course: " + course +
               ", Batch: " + batch +
               ", Active: " + isActive;
    }

    private void validateStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
    }

    private void validateCourse(String course) {
        if (course == null || course.trim().isEmpty()) {
            throw new IllegalArgumentException("Course cannot be null or empty");
        }
    }

    private void validateBatch(String batch) {
        if (batch == null || batch.trim().isEmpty()) {
            throw new IllegalArgumentException("Batch cannot be null or empty");
        }
    }

    /**
     * Equality is based on unique studentId.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return Objects.equals(studentId, other.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", course='" + course + '\'' +
                ", batch='" + batch + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
