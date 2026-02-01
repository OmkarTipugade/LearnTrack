package src.com.airtribe.learntrack.services;

import src.com.airtribe.learntrack.entity.Course;
import src.com.airtribe.learntrack.exceptions.EntityNotFoundException;
import src.com.airtribe.learntrack.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CourseService implements ICourseService {
    
    private static final CourseService INSTANCE = new CourseService();
    
    private final List<Course> courses;
    
    private CourseService() {
        this.courses = Collections.synchronizedList(new ArrayList<>());
    }
    
    public static CourseService getInstance() {
        return INSTANCE;
    }
    
    @Override
    public synchronized void addCourse(Course course) throws InvalidInputException {
        if (course == null) {
            throw new InvalidInputException("Course cannot be null");
        }
        
        if (isCourseIdExists(course.getCourseId())) {
            throw new InvalidInputException(
                "Course with ID " + course.getCourseId() + " already exists"
            );
        }
        
        courses.add(course);
    }
    
    @Override
    public synchronized List<Course> getAllCourses() {
        return Collections.unmodifiableList(new ArrayList<>(courses));
    }
    
    @Override
    public synchronized Course getCourseById(String courseId) 
            throws EntityNotFoundException, InvalidInputException {
        validateCourseId(courseId);
        
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        
        throw new EntityNotFoundException("Course with ID " + courseId + " not found");
    }
    
    @Override
    public synchronized void removeCourse(String courseId) 
            throws EntityNotFoundException, InvalidInputException {
        validateCourseId(courseId);
        
        if (!isCourseIdExists(courseId)) {
            throw new EntityNotFoundException("Course with ID " + courseId + " not found");
        }
        
        courses.removeIf(course -> course.getCourseId().equals(courseId));
    }
    
    @Override
    public synchronized void updateCourseDetails(String courseId, String newName, 
                                                String newDescription, int newDuration) 
            throws EntityNotFoundException, InvalidInputException {
        validateCourseId(courseId);
        validateNonEmpty(newName, "Course name");
        validateNonEmpty(newDescription, "Description");
        
        Course course = getCourseById(courseId);
        
        // Update all fields - entity validation will occur in setters
        course.setCourseName(newName);
        course.setDescription(newDescription);
        course.setDurationInWeeks(newDuration);
    }
    
    @Override
    public synchronized void deactivateCourse(String courseId) 
            throws EntityNotFoundException, InvalidInputException {
        validateCourseId(courseId);
        
        Course course = getCourseById(courseId);
        course.deactivateCourse();
    }
    
    @Override
    public synchronized void activateCourse(String courseId) 
            throws EntityNotFoundException, InvalidInputException {
        validateCourseId(courseId);
        
        Course course = getCourseById(courseId);
        course.activateCourse();
    }
    
  
    private void validateCourseId(String courseId) throws InvalidInputException {
        if (courseId == null) {
            throw new InvalidInputException("Course ID cannot be null");
        }
        if (courseId.trim().isEmpty()) {
            throw new InvalidInputException("Course ID cannot be empty");
        }
    }
    
  
    private void validateNonEmpty(String value, String fieldName) throws InvalidInputException {
        if (value == null) {
            throw new InvalidInputException(fieldName + " cannot be null");
        }
        if (value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty");
        }
    }
    

    private boolean isCourseIdExists(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }
}