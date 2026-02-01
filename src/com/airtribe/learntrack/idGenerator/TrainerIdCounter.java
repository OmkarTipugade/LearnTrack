package src.com.airtribe.learntrack.idGenerator;

import java.util.concurrent.atomic.AtomicInteger;


public final class TrainerIdCounter {
    
    private static final String ID_PREFIX = "TRAINER";
    
    private static final int INITIAL_COUNTER_VALUE = 1000;
    
    private static final AtomicInteger counter = new AtomicInteger(INITIAL_COUNTER_VALUE);
    
    private TrainerIdCounter() {
        throw new AssertionError("TrainerIdCounter is a utility class and should not be instantiated");
    }
    
    public static String generateTrainerId() {
        return ID_PREFIX + counter.getAndIncrement();
    }
    
    public static void resetCounter() {
        counter.set(INITIAL_COUNTER_VALUE);
    }
    

    public static String getNextTrainerId() {
        return ID_PREFIX + counter.get();
    }
}