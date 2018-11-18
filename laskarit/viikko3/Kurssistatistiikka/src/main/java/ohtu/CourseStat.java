package ohtu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class CourseStat {

    private ArrayList<WeekStat> exercises;

    public int submissionTotal() {

        int amount = 0;

        for (WeekStat s : exercises) {
            amount += s.students;
        }

        return  amount;
    }
    public double hourTotal() {

        double amount = 0;

        for (WeekStat s : exercises) {
            amount += s.hour_total;
        }

        return  amount;
    }
    public int exerciseTotal() {

        int amount = 0;

        for (WeekStat s : exercises) {
            amount += s.exercise_total;
        }

        return  amount;
    }

    public void setExercises(ArrayList<WeekStat> exercises) {
        this.exercises = exercises;
    }
}

class WeekStat {
    public int students;
    public double hour_total;
    public int exercise_total;
}