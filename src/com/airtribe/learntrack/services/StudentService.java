package src.com.airtribe.learntrack.services;

import src.com.airtribe.learntrack.entity.Student;
import src.com.airtribe.learntrack.exceptions.EntityNotFoundException;
import src.com.airtribe.learntrack.exceptions.InvalidInputException;

import java.util.*;


public class StudentService implements IStudentService {
    
    
    private static final StudentService INSTANCE = new StudentService();
    
    private final List<Student> students;
    
    
    private StudentService() {
        this.students = Collections.synchronizedList(new ArrayList<>());
    }
    
 
    public static StudentService getInstance() {
        return INSTANCE;
    }
    
  
    @Override
    public synchronized void addStudent(Student student) throws InvalidInputException {
        // Validate input
        if (student == null) {
            throw new InvalidInputException("Student cannot be null");
        }
        
        // Check for duplicate student ID
        if (isStudentIdExists(student.getStudentId())) {
            throw new InvalidInputException(
                "Student with ID " + student.getStudentId() + " already exists"
            );
        }
        
        students.add(student);
    }
    
   
    @Override
    public synchronized List<Student> getAllStudents() {
        return Collections.unmodifiableList(new ArrayList<>(students));
    }
    
  
    @Override
    public synchronized Student getStudentById(String studentId) 
            throws EntityNotFoundException, InvalidInputException {
        validateStudentId(studentId);
        
        // Search for student with matching ID
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        
        throw new EntityNotFoundException("Student with ID " + studentId + " not found");
    }
    

    @Override
    public synchronized void removeStudent(String studentId) 
            throws EntityNotFoundException, InvalidInputException {
        validateStudentId(studentId);
        
        // Check if student exists before removal
        if (!isStudentIdExists(studentId)) {
            throw new EntityNotFoundException("Student with ID " + studentId + " not found");
        }
        
        students.removeIf(student -> student.getStudentId().equals(studentId));
    }
  
    @Override
    public synchronized void updateStudentCourse(String studentId, String newCourse) 
            throws EntityNotFoundException, InvalidInputException {
        validateStudentId(studentId);
        validateNonEmpty(newCourse, "Course");
        
        Student student = getStudentById(studentId);
        student.setCourse(newCourse);
    }
    
  
    @Override
    public synchronized void deactivateStudent(String studentId) 
            throws EntityNotFoundException, InvalidInputException {
        validateStudentId(studentId);
        
        Student student = getStudentById(studentId);
        student.deactivateStudent();
    }
    
  
    @Override
    public synchronized void activateStudent(String studentId) 
            throws EntityNotFoundException, InvalidInputException {
        validateStudentId(studentId);
        
        Student student = getStudentById(studentId);
        student.activateStudent();
    }
    
  
    @Override
    public synchronized List<Student> getActiveStudents() {
        List<Student> activeStudents = new ArrayList<>();
        
        // Filter active students using traditional loop for clarity
        for (Student student : students) {
            if (student.isActive()) {
                activeStudents.add(student);
            }
        }
        
        return Collections.unmodifiableList(activeStudents);
    }
    
  
    @Override
    public synchronized void updateStudent(String studentId, String newCourse, 
                                          String newBatch, boolean isActive) 
            throws EntityNotFoundException, InvalidInputException {
        validateStudentId(studentId);
        validateNonEmpty(newCourse, "Course");
        validateNonEmpty(newBatch, "Batch");
        
        Student student = getStudentById(studentId);
        
        student.setCourse(newCourse);
        student.setBatch(newBatch);
        
        if (isActive) {
            student.activateStudent();
        } else {
            student.deactivateStudent();
        }
    }
    

    private void validateStudentId(String studentId) throws InvalidInputException {
        if (studentId == null) {
            throw new InvalidInputException("Student ID cannot be null");
        }
        if (studentId.trim().isEmpty()) {
            throw new InvalidInputException("Student ID cannot be empty");
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
    
 
    private boolean isStudentIdExists(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }
}
