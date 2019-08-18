package individualproject;

import java.util.ArrayList;
import java.util.List;

public class AssignmentsPerCourse {

    private Course course;
    private List<Assignment> assignmentsPerCourse;
    public static List<AssignmentsPerCourse> allAssignmentsPerCourse = new ArrayList();

    public AssignmentsPerCourse() {
    }

    public AssignmentsPerCourse(Course course) {
        assignmentsPerCourse = new ArrayList();

        this.course = course;
        fillList();
        allAssignmentsPerCourse.add(this);
    }

    public void fillList() {
        for (Assignment assignment : Assignment.assignments) {

            if (assignment.getCourse().equals(course)) {
                assignmentsPerCourse.add(assignment);
            }

        }
    }

    @Override
    public String toString() {
        return "AssignmentsPerCourse{" + "course=" + course + ", assignmentsPerCourse=" + assignmentsPerCourse + '}';
    }

    public static void printList() {
        for (AssignmentsPerCourse apc : allAssignmentsPerCourse) {
            System.out.println(apc.course.getTitle() + ": ");
            for (Assignment a : apc.assignmentsPerCourse) {
                System.out.println(a);
            }
        }
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Assignment> getAssignmentsPerCourse() {
        return assignmentsPerCourse;
    }

    public void setAssignmentsPerCourse(List<Assignment> assignmentsPerCourse) {
        this.assignmentsPerCourse = assignmentsPerCourse;
    }

}
