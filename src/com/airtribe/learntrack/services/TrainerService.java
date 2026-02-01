package src.com.airtribe.learntrack.services;

import src.com.airtribe.learntrack.entity.Trainer;
import src.com.airtribe.learntrack.exceptions.EntityNotFoundException;
import src.com.airtribe.learntrack.exceptions.InvalidInputException;

import java.util.*;

/**
 * Implementation of {@link ITrainerService} using the Singleton pattern.
 * 
 * <p>This class provides thread-safe trainer management operations for the LearnTrack
 * system. It implements the <strong>Singleton pattern</strong> to ensure only one
 * instance manages the trainer data, preventing data inconsistencies.</p>
 * 
 * <p><strong>Thread Safety:</strong> All public methods are synchronized to ensure
 * thread-safe operation in multi-threaded environments.</p>
 * 
 * @author LearnTrack Development Team
 * @version 2.0
 * @since 2026-01-31
 */
public class TrainerService implements ITrainerService {
    
    private static final TrainerService INSTANCE = new TrainerService();
    
    private final List<Trainer> trainers;
    
    private TrainerService() {
        this.trainers = Collections.synchronizedList(new ArrayList<>());
    }
    
 
    public static TrainerService getInstance() {
        return INSTANCE;
    }
    
    @Override
    public synchronized void addTrainer(Trainer trainer) throws InvalidInputException {
        if (trainer == null) {
            throw new InvalidInputException("Trainer cannot be null");
        }
        
        // Check for duplicate trainer ID
        if (isTrainerIdExists(trainer.getTrainerId())) {
            throw new InvalidInputException(
                "Trainer with ID " + trainer.getTrainerId() + " already exists"
            );
        }
        
        trainers.add(trainer);
    }
    
    @Override
    public synchronized List<Trainer> getAllTrainers() {
        return Collections.unmodifiableList(new ArrayList<>(trainers));
    }
    
    @Override
    public synchronized Trainer getTrainerById(String trainerId) 
            throws EntityNotFoundException, InvalidInputException {
        validateTrainerId(trainerId);
        
        for (Trainer trainer : trainers) {
            if (trainer.getTrainerId().equals(trainerId)) {
                return trainer;
            }
        }
        
        throw new EntityNotFoundException("Trainer with ID " + trainerId + " not found");
    }
    
    @Override
    public synchronized void removeTrainer(String trainerId) 
            throws EntityNotFoundException, InvalidInputException {
        validateTrainerId(trainerId);
        
        if (!isTrainerIdExists(trainerId)) {
            throw new EntityNotFoundException("Trainer with ID " + trainerId + " not found");
        }
        
        trainers.removeIf(trainer -> trainer.getTrainerId().equals(trainerId));
    }
    
    @Override
    public synchronized void updateTrainerExpertise(String trainerId, String newExpertise) 
            throws EntityNotFoundException, InvalidInputException {
        validateTrainerId(trainerId);
        validateNonEmpty(newExpertise, "Expertise");
        
        Trainer trainer = getTrainerById(trainerId);
        trainer.setExpertise(newExpertise);
    }
    
    @Override
    public synchronized void setTrainerAvailability(String trainerId, boolean availability) 
            throws EntityNotFoundException, InvalidInputException {
        validateTrainerId(trainerId);
        
        Trainer trainer = getTrainerById(trainerId);
        trainer.setAvailable(availability);
    }
    

    private void validateTrainerId(String trainerId) throws InvalidInputException {
        if (trainerId == null) {
            throw new InvalidInputException("Trainer ID cannot be null");
        }
        if (trainerId.trim().isEmpty()) {
            throw new InvalidInputException("Trainer ID cannot be empty");
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
    
 
    private boolean isTrainerIdExists(String trainerId) {
        for (Trainer trainer : trainers) {
            if (trainer.getTrainerId().equals(trainerId)) {
                return true;
            }
        }
        return false;
    }
}
