package ohtu;

public class Submission {

    private String course;
    private double hours;
    private int week;
    private int[] exercises;
    private int exerciseCount = -1;

    @Override
    public String toString() {
        String sub = "Course: " + course + ", Week " + week + ": " + exercises.length + " out of" + excercisesPerWeek() + " exercises in " + hours + " hours (";

        for (int i = 0; i < exercises.length - 2; i++) {
            sub += exercises[i] + ", ";
        }
        sub += exercises[exercises.length - 1] + ")";

        return sub;
    }
    public String toShortString() {
        String sub = "Week " + week + ": " + exercises.length + " out of " + excercisesPerWeek() + " exercises in " + hours + " hours (";

        for (int i = 0; i < exercises.length - 2; i++) {
            sub += exercises[i] + ", ";
        }
        sub += exercises[exercises.length - 1] + ")";

        return sub;
    }
    public int excercisesPerWeek() {

        if (exerciseCount != -1) {
            return  exerciseCount;
        }

        Course related =Course.getRelatedCourse(course);

        if (related == null) {
            return 0;
        }

        int[] amounts = related.getExercises();

        if (week < amounts.length) {
            return amounts[week];
        }

        return exerciseCount;
    }


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }
}