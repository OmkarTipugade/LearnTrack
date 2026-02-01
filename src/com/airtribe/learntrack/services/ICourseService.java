package src.com.airtribe.learntrack.services;

import src.com.airtribe.learntrack.entity.Course;
import src.com.airtribe.learntrack.exceptions.EntityNotFoundException;
import src.com.airtribe.learntrack.exceptions.InvalidInputException;
import java.util.List;


public interface ICourseService {
    
 
    void addCourse(Course course) throws InvalidInputException;
    

    List<Course> getAllCourses();
    

    Course getCourseById(String courseId) throws EntityNotFoundException, InvalidInputException;
    

    void removeCourse(String courseId) throws EntityNotFoundException, InvalidInputException;
    

    void updateCourseDetails(String courseId, String newName, String newDescription, int newDuration)
            throws EntityNotFoundException, InvalidInputException;
    

    void deactivateCourse(String courseId) throws EntityNotFoundException, InvalidInputException;
    

    void activateCourse(String courseId) throws EntityNotFoundException, InvalidInputException;
}
