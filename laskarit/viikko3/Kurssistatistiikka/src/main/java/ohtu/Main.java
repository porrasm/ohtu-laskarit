package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {

        String studentNr = "012345678";

        if ( args.length>0) {
            studentNr = args[0];
        }

        Course.ParseCourses();

        Student student = Student.CreateStudent(studentNr);
        System.out.println(student);
    }
}