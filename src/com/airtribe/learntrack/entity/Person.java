package src.com.airtribe.learntrack.entity;

import java.util.Objects;

public abstract class Person {
    
    private static final int MIN_AGE = 0;
    
    private static final int MAX_AGE = 150;
    
    private String firstName;
    
    private String lastName;
    
    private int age;

    public Person(String firstName, String lastName, int age) {
        validateName(firstName, "First name");
        validateName(lastName, "Last name");
        validateAge(age);
        
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.age = age;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }

    public String getPersonInfo() {
        return "Name: " + getName() + ", Age: " + age;
    }

    public void setFirstName(String firstName) {
        validateName(firstName, "First name");
        this.firstName = firstName.trim();
    }

    public void setLastName(String lastName) {
        validateName(lastName, "Last name");
        this.lastName = lastName.trim();
    }

    public void setName(String firstName, String lastName) {
        validateName(firstName, "First name");
        validateName(lastName, "Last name");
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }
    

    private void validateName(String name, String fieldName) {
        if (name == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
    }
    
    private void validateAge(int age) {
        if (age < MIN_AGE || age > MAX_AGE) {
            throw new IllegalArgumentException(
                "Age must be between " + MIN_AGE + " and " + MAX_AGE + ", got: " + age
            );
        }
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Person other = (Person) obj;
        return age == other.age &&
               Objects.equals(firstName, other.firstName) &&
               Objects.equals(lastName, other.lastName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", age=" + age +
               '}';
    }
}
