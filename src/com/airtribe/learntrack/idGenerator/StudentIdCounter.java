package src.com.airtribe.learntrack.idGenerator;

import java.util.concurrent.atomic.*;


public class StudentIdCounter {
    
    private static final String ID_PREFIX = "STU";
    
    private static final int INITIAL_COUNTER_VALUE = 1000;
  
    private static final AtomicInteger counter = new AtomicInteger(INITIAL_COUNTER_VALUE);
    
   
    private StudentIdCounter() {
        throw new AssertionError("StudentIdCounter is a utility class and should not be instantiated");
    }
    
    public static String generateStudentId() {
        return ID_PREFIX + counter.getAndIncrement();
    }
 
    public static void resetCounter() {
        counter.set(INITIAL_COUNTER_VALUE);
    }
    
    public static String getNextStudentId() {
        return ID_PREFIX + counter.get();
    }
}
