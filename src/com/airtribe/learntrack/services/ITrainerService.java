package src.com.airtribe.learntrack.services;

import src.com.airtribe.learntrack.entity.Trainer;
import src.com.airtribe.learntrack.exceptions.EntityNotFoundException;
import src.com.airtribe.learntrack.exceptions.InvalidInputException;
import java.util.*;

public interface ITrainerService {
    
   
    void addTrainer(Trainer trainer) throws InvalidInputException;
    
  
    List<Trainer> getAllTrainers();
    
   
    Trainer getTrainerById(String trainerId) throws EntityNotFoundException, InvalidInputException;
    
 
    void removeTrainer(String trainerId) throws EntityNotFoundException, InvalidInputException;
    
 
    void updateTrainerExpertise(String trainerId, String newExpertise)
            throws EntityNotFoundException, InvalidInputException;
    
  
    void setTrainerAvailability(String trainerId, boolean availability)
            throws EntityNotFoundException, InvalidInputException;
}
