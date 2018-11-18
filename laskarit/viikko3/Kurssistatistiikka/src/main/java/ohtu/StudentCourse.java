package ohtu;

import java.io.IOException;
import java.util.ArrayList;

public class StudentCourse {

    private Course course;
    private ArrayList<Submission> submissions;

    public StudentCourse(String courseName, Submission[] allSubs) {
        course = Course.getRelatedCourse(courseName);
        submissions = new ArrayList<>();

        for (Submission sub : allSubs) {
            if (sub.getCourse().equals(courseName)) {
                submissions.add(sub);
            }
        }
    }

    @Override
    public String toString() {

        String text = "Course: " + course.getFullName() + "\n";

        for (Submission sub : submissions) {
            text += sub.toShortString() + "\n";
        }

        try {
            text += "\n" + course.courseStats() + "\n\n";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    public int totalExercises() {

        int amount = 0;

        for (Submission sub : submissions) {
            amount += sub.getExercises().length;
        }

        return amount;
    }
    public int maxExerciseAmount() {
        int amount = 0;

        for (Submission sub : submissions) {
            amount += sub.excercisesPerWeek();
        }

        return amount;
    }
    public double totalHours() {
        double amount = 0;

        for (Submission sub : submissions) {
            amount += sub.getHours();
        }

        return amount;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(ArrayList<Submission> submissions) {
        this.submissions = submissions;
    }
}