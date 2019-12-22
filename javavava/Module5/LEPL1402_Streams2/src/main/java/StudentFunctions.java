import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StudentFunctions implements StudentStreamFunction {

  //    Key : String that is one of the fields of Student ( "firstName", "lastName", "section", "courses_results")
    public Student findFirst(Stream<Student> studentsStream, Map<String, Predicate<?>> conditions){
        Student[] students = findAll(studentsStream, conditions);
        if (students.length == 0){
            return null;
        }
        return students[0];
    }

    public Student[] findAll(Stream<Student> studentsStream, Map<String, Predicate<?>> conditions){
        if (conditions.containsKey("firstName")){
            Predicate<String> predFirstName = (Predicate<String>) conditions.get("firstName");
             studentsStream = studentsStream.filter( s -> predFirstName.test(s.getFirstName()) );
        }
        if (conditions.containsKey("lastName")){
            Predicate<String> predLastName = (Predicate<String>) conditions.get("lastName");
            studentsStream = studentsStream.filter( (s) -> (predLastName.test(s.getLastName())) );
        }
        if (conditions.containsKey("section")){
            Predicate<Integer> predSection = (Predicate<Integer>) conditions.get("section");
            studentsStream = studentsStream.filter( (s) -> (predSection.test(s.getSection())) );
        }
        if (conditions.containsKey("courses_results")){
            Predicate<Map<String,Double>> predCoursesResults = (Predicate<Map<String,Double>>) conditions.get("courses_results");
            studentsStream = studentsStream.filter( (s) -> (predCoursesResults.test(s.getCourses_results())) );
        }
        return studentsStream.toArray(Student[]::new);
    }

    public boolean exists(Stream<Student> studentsStream,
                          Map<String, Predicate<?>> conditions,
                          int n)
    {
        return findAll(studentsStream, conditions).length >= n;
    }

    public Student[] filterThenSort(Stream<Student> studentsStream,
                                    Map<String, Predicate<?>> conditions,
                                    Comparator<Student> comparator)
    {
        Stream<Student> filteredStudentStream = Stream.of(findAll(studentsStream, conditions));
        Stream<Student> sortedStudentStream = filteredStudentStream.sorted(comparator);
        return sortedStudentStream.toArray(Student[]::new);
    }

}
