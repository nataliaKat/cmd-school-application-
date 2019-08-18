package individualproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private double tuitionFees;
    private List<Course> courses;
    private List<Assignment> studentAssignments;
    public static List<Student> students = new ArrayList();

    public Student() {
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth, double tuitionFees, List<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
        this.courses = new ArrayList(courses);
        students.add(this);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Assignment> getStudentAssignments() {
        return studentAssignments;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(double tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public static void printWithMoreCourses() {
        for (Student st : Student.students) {
            if (st.courses.size() > 1) {
                System.out.println(st.getFirstName() + " " + st.getLastName() + " attends: ");
                for (Course c : st.courses) {
                    System.out.println(c);
                }
            }
        }
    }

    @Override
    public String toString() {
        String x = "First Name: " + firstName
                + "\nLast Name: " + lastName
                + "\nDate of Birth: " + dateOfBirth
                + "\nTuition Fees: " + tuitionFees
                + "\n--------------------";

        return x;
    }

    public static void printList() {
        for (Student s : students) {
            System.out.println(s);
        }
    }

}
