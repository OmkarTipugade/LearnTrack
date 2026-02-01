package src.com.airtribe.learntrack.entity;

import java.util.*;

/**
 * Represents a Trainer entity in the LearnTrack system.
 * Extends Person and adds trainer-specific details.
 */
public class Trainer extends Person {

    private final String trainerId;

    private String expertise;

    private boolean isAvailable;

    private String email;

    /**
     * Creates a new Trainer and initializes required fields.
     */
    public Trainer(String firstName, String lastName, int age,
                   String trainerId, String expertise, String email) {

        super(firstName, lastName, age);

        validateTrainerId(trainerId);
        validateExpertise(expertise);
        validateEmail(email);

        this.email = email.trim();

        this.trainerId = trainerId.trim();
        this.expertise = expertise.trim();
        this.email = email.trim();
        this.isAvailable = true;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        validateExpertise(expertise);
        this.expertise = expertise.trim();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    /**
     * Returns trainer-specific information.
     */
    @Override
    public String getPersonInfo() {
        return "Trainer ID: " + trainerId +
               ", Name: " + getName() +
               ", Age: " + getAge() +
               ", Expertise: " + expertise +
               ", Email: " + email +
               ", Available: " + isAvailable;
    }


    private void validateTrainerId(String trainerId) {
        if (trainerId == null || trainerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Trainer ID cannot be null or empty");
        }
    }

    private void validateExpertise(String expertise) {
        if (expertise == null || expertise.trim().isEmpty()) {
            throw new IllegalArgumentException("Expertise cannot be null or empty");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        // Basic email format validation
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Trainer)) return false;
        Trainer other = (Trainer) obj;
        return Objects.equals(trainerId, other.trainerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainerId);
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "trainerId='" + trainerId + '\'' +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", expertise='" + expertise + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
