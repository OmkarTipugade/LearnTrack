package src.com.airtribe.learntrack.services;

import src.com.airtribe.learntrack.entity.Student;
import src.com.airtribe.learntrack.exceptions.EntityNotFoundException;
import src.com.airtribe.learntrack.exceptions.InvalidInputException;
import java.util.List;


public interface IStudentService {
 
    void addStudent(Student student) throws InvalidInputException;
    
   
    List<Student> getAllStudents();
    
   
    Student getStudentById(String studentId) throws EntityNotFoundException, InvalidInputException;
    
   
    void removeStudent(String studentId) throws EntityNotFoundException, InvalidInputException;
    
    
    void updateStudentCourse(String studentId, String newCourse) 
            throws EntityNotFoundException, InvalidInputException;
    
  
    void deactivateStudent(String studentId) throws EntityNotFoundException, InvalidInputException;
    
  
    void activateStudent(String studentId) throws EntityNotFoundException, InvalidInputException;
    
    
    List<Student> getActiveStudents();
    
   
    void updateStudent(String studentId, String newCourse, String newBatch, boolean isActive)
            throws EntityNotFoundException, InvalidInputException;
}
