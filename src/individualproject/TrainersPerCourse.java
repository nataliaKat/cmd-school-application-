package individualproject;

import java.util.ArrayList;
import java.util.List;

public class TrainersPerCourse {

    private Course course;
    private List<Trainer> trainersPerCourse;
    public static List<TrainersPerCourse> allTrainersPerCourse = new ArrayList();

    public TrainersPerCourse() {
    }

    public TrainersPerCourse(Course course) {
        trainersPerCourse = new ArrayList();

        this.course = course;
        fillList();
        allTrainersPerCourse.add(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void fillList() {
        for (Trainer trainer : Trainer.trainers) {

            if (trainer.getCourse().equals(this.course)) {
                trainersPerCourse.add(trainer);

            }

        }
    }

    @Override
    public String toString() {
        return "course=" + course + ", trainersPerCourse=" + trainersPerCourse;
    }

    public static void printList() {
        for (TrainersPerCourse tpc : allTrainersPerCourse) {
            System.out.println(tpc.course.getTitle() + ": ");
            for (Trainer t : tpc.trainersPerCourse) {
                System.out.println(t);
            }
        }
    }

}
