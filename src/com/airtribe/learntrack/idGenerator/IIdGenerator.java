package src.com.airtribe.learntrack.idGenerator;


public interface IIdGenerator {
    
    String generateId();
    
    String getNextId();
    
    void resetCounter();
}
