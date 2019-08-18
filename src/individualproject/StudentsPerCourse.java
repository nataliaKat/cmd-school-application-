package individualproject;

import java.util.ArrayList;
import java.util.List;

public class StudentsPerCourse {

    private Course course;
    private List<Student> studentsPerCourse;
    static List<StudentsPerCourse> allStudentsPerCourse = new ArrayList();

    public StudentsPerCourse() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudentsPerCourse() {
        return studentsPerCourse;
    }

    public void setStudentsPerCourse(List<Student> studentsPerCourse) {
        this.studentsPerCourse = studentsPerCourse;
    }

    public StudentsPerCourse(Course course) {
        studentsPerCourse = new ArrayList();
        this.course = course;
        fillList();
        allStudentsPerCourse.add(this);
    }

    public void fillList() {
        for (Student student : Student.students) {
            for (Course c : student.getCourses()) {

                if (c.equals(course)) {

                    studentsPerCourse.add(student);
                }

            }
        }
    }

    public static void printAssignments() {
        for (Student student : Student.students) {
            System.out.println("Student " + student.getFirstName() + " " + student.getLastName() + "'s assignments are:");
            for (Course c : student.getCourses()) {
                for (AssignmentsPerCourse apc : AssignmentsPerCourse.allAssignmentsPerCourse) {
                    if (apc.getCourse().equals(c)) {

                        System.out.println(apc.getAssignmentsPerCourse());

                    }

                }

            }
        }
    }

    @Override
    public String toString() {
        return "course=" + course + ", studentsPerCourse=" + studentsPerCourse + "\n<<<<<<<<>>>>>>>>";
    }

    public static void printList() {
        for (StudentsPerCourse spc : allStudentsPerCourse) {
            System.out.println(spc.course.getTitle() + ": ");
            for (Student s : spc.studentsPerCourse) {
                System.out.println(s);
            }
        }
    }

}
