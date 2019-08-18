package individualproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Assignment {

    private String title;
    private String description;
    private LocalDate subDateTime;
    private double oralMark;
    private double totalMark;
    private Course course;
    public static List<Assignment> assignments = new ArrayList();

    public Assignment() {

    }

    public Assignment(String title, String description, LocalDate subDateTime, double oralMark, double totalMark, Course course) {
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
        this.course = course;
        assignments.add(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(LocalDate subDateTime) {
        this.subDateTime = subDateTime;
    }

    public static List<Assignment> getAssignments() {
        return assignments;
    }

    public static void setAssignments(List<Assignment> assignments) {
        Assignment.assignments = assignments;
    }

    public double getOralMark() {
        return oralMark;
    }

    public void setOralMark(double oralMark) {
        this.oralMark = oralMark;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public String toString() {
        String x = "Title: " + title + " in " + course.getTitle()
                + "\nDescription: " + description
                + "\nSubmission Date: " + subDateTime
                + "\nOral Mark: " + oralMark
                + "\nTotal Mark: " + totalMark
                + "\n-----------------------";
        return x;
    }

    public static void printList() {
        for (Assignment a : assignments) {
            System.out.println(a);
        }
    }

}
