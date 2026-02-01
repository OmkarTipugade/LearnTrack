package src.com.airtribe.learntrack.entity;

import java.util.*;

public class Course {
    
    private static final int MIN_DURATION_WEEKS = 1;
    
    private static final int MAX_DURATION_WEEKS = 104;
    
    private final String courseId;
    
    private String courseName;
    
    private String description;
    
    private int durationInWeeks;
    
    private boolean isActive;

   
    public Course(String courseId, String courseName, String description, int durationInWeeks) {
        validateCourseId(courseId);
        validateCourseName(courseName);
        validateDescription(description);
        validateDuration(durationInWeeks);
        
        this.courseId = courseId.trim();
        this.courseName = courseName.trim();
        this.description = description.trim();
        this.durationInWeeks = durationInWeeks;
        
        // New courses are active by default
        this.isActive = true;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        validateCourseName(courseName);
        this.courseName = courseName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description.trim();
    }

  
    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    
    public void setDurationInWeeks(int durationInWeeks) {
        validateDuration(durationInWeeks);
        this.durationInWeeks = durationInWeeks;
    }

  
    public boolean isActive() {
        return isActive;
    }
    
   
    public void activateCourse() {
        this.isActive = true;
    }

  
    public void deactivateCourse() {
        this.isActive = false;
    }
    
   
    private void validateCourseId(String courseId) {
        if (courseId == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        if (courseId.trim().isEmpty()) {
            throw new IllegalArgumentException("Course ID cannot be empty");
        }
    }
    
   
    private void validateCourseName(String courseName) {
        if (courseName == null) {
            throw new IllegalArgumentException("Course name cannot be null");
        }
        if (courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }
    }
    
    private void validateDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
    }
    
    private void validateDuration(int durationInWeeks) {
        if (durationInWeeks < MIN_DURATION_WEEKS || durationInWeeks > MAX_DURATION_WEEKS) {
            throw new IllegalArgumentException(
                "Duration must be between " + MIN_DURATION_WEEKS + " and " + 
                MAX_DURATION_WEEKS + " weeks, got: " + durationInWeeks
            );
        }
    }
    
  
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // For courses, equality is based on course ID (unique identifier)
        Course other = (Course) obj;
        return Objects.equals(courseId, other.courseId);
    }
    
   
    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
    
    @Override
    public String toString() {
        return "Course{" +
               "courseId='" + courseId + '\'' +
               ", courseName='" + courseName + '\'' +
               ", description='" + description + '\'' +
               ", durationInWeeks=" + durationInWeeks +
               ", isActive=" + isActive +
               '}';
    }
}
