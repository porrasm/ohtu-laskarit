package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.ArrayList;

public class Course {

    private String name;
    private String fullName;
    private int[] exercises;

    private String courseStats;

    private static Course[] courses;

    public Course(String course) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void ParseCourses() throws IOException {

        String url = "https://studies.cs.helsinki.fi/courses/courseinfo";
        System.out.println("Parsing courses: " + url);

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Course[] courses = mapper.fromJson(bodyText, Course[].class);

        Course.courses = courses;
    }
    public static Course getRelatedCourse(String name) {

        if (courses == null) {
            return  null;
        }

        for (Course c : courses) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return  null;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public String courseStats() throws IOException {

        if (courseStats != null) {
            return courseStats;
        }

        String url = "https://studies.cs.helsinki.fi/courses/" + name + "/stats";
        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("Parsing course stats: " + url);

        Gson mapper = new Gson();

        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(bodyText).getAsJsonObject();

        int i = 1;

        ArrayList<WeekStat> weeks = new ArrayList<>();

        while (true) {

            JsonElement json = parsittuData.get("" + i);

            i++;
            if (json == null) {break;}

            weeks.add(mapper.fromJson(json, WeekStat.class));
        }

        CourseStat course = new CourseStat();
        course.setExercises(weeks);

        courseStats = fullName + " has " + course.submissionTotal() + " submissions: " + course.exerciseTotal() + " exercises with total " + course.hourTotal() + " hours.";
        return courseStats;
    }
}
