package src.com.airtribe.learntrack.exceptions;

public class Exceptions {
    
    public static class EntityNotFoundException extends Exception {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }
    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }
}
