package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Student {

    private String student_id;
    private ArrayList<StudentCourse> courses;

    public Student(String student_id, ArrayList<StudentCourse> courses) {
        this.student_id = student_id;
        this.courses = courses;
    }

    @Override
    public String toString() {

        StringBuilder text = new StringBuilder();

        text.append("--------------------------------------------------------------------------\n");
        text.append("Student: " + student_id + "\n\n");

        for (StudentCourse course : courses) {
            text.append(course.toString() + "\n");
        }

        text.append("\nTotal: " + totalExcercises() + " out of " + maxExerciseAmount() + " in " + totalHours() + " hours\n");
        text.append("--------------------------------------------------------------------------\n");

        return text.toString();
    }

    public int totalExcercises() {
        int amount = 0;

        for (StudentCourse course : courses) {
            amount += course.totalExercises();
        }

        return amount;
    }
    public int maxExerciseAmount() {
        int amount = 0;

        for (StudentCourse course : courses) {
            amount += course.maxExerciseAmount();
        }

        return amount;
    }
    public double totalHours() {
        double amount = 0;

        for (StudentCourse course : courses) {
            amount += course.totalHours();
        }

        return amount;
    }

    public static Student CreateStudent(String student_id) throws IOException {

        String url = "https://studies.cs.helsinki.fi/courses/students/"+student_id+"/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("Parsing student: " + url);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        HashSet<String> courseNames = new HashSet<>();

        for (Submission sub : subs) {
            courseNames.add(sub.getCourse());
        }

        ArrayList<StudentCourse> courses = new ArrayList<>();

        for (String courseName : courseNames) {
            courses.add(new StudentCourse(courseName, subs));
        }

        return new Student(student_id, courses);
    }
}
