package individualproject;

import java.util.ArrayList;
import java.util.List;

public class Trainer {

    private String firstName;
    private String lastName;
    private String subject;
    private Course course;
    public static List<Trainer> trainers = new ArrayList();

    public Trainer() {
    }

    public Trainer(String firstName, String lastName, String subject, Course course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.course = course;
        trainers.add(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        String x = "First Name: " + firstName
                + "\nLast Name: " + lastName
                + "\nSubject: " + subject
                + "\n--------------------";
        return x;

    }

    public static void printList() {
        for (Trainer t : trainers) {
            System.out.println(t);
        }
    }

}
