package src.com.airtribe.learntrack.idGenerator;

import java.util.concurrent.atomic.AtomicInteger;

public final class CourseIdCounter {
    
    private static final String ID_PREFIX = "COURSE";
    
    private static final int INITIAL_COUNTER_VALUE = 1000;
    
    private static final AtomicInteger counter = new AtomicInteger(INITIAL_COUNTER_VALUE);
    
    private CourseIdCounter() {
        throw new AssertionError("CourseIdCounter is a utility class and should not be instantiated");
    }
    
    public static String generateCourseId() {
        return ID_PREFIX + counter.getAndIncrement();
    }

    public static void resetCounter() {
        counter.set(INITIAL_COUNTER_VALUE);
    }

    public static String getNextCourseId() {
        return ID_PREFIX + counter.get();
    }
}