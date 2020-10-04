import java.sql.PseudoColumnUsage;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StudentFunctions implements StudentStreamFunction {

    public Stream<Student> findSecondAndThirdTopStudentForGivenCourse(Stream<Student> studentStream, String name){
        Stream<Student> best3StudentsForGivenCourse = studentStream.filter(student -> student.getCoursesResults().containsKey(name))
                .sorted(
                        ((Comparator<Student>) (s1, s2) -> Double.compare(s1.getCoursesResults().get(name), s2.getCoursesResults().get(name)))
                                .reversed()
                ).limit(3);
        return best3StudentsForGivenCourse.skip(1);
    }

    public Object[] computeAverageForStudentInSection(Stream<Student> studentStream, int section){
        Stream<Object[]> result = studentStream.filter(s -> s.getSection() == section)
                .map( s -> new Object[]{
                        String.format("Student %s %s", s.getFirstName(), s.getLastName()),
                        s.getCoursesResults().values().stream().reduce(0.0,(a,b)->(a+b/2)) }
                ).sorted( (s1,s2)-> (int) ((Double) s2[1] - (Double) s1[1]));
        Object[] arrayResult = result.toArray();
        return result.toArray();
    }

    public int getNumberOfSuccessfulStudents(Stream<Student> studentStream){
        long numberOfSuccesfullStudents = studentStream.filter(
                (s) -> (s.getCoursesResults().values().stream().filter(d->d>10.0).count() == s.getCoursesResults().values().size())
        ).count();
        return (int) numberOfSuccesfullStudents;
    }

    public Student findLastInLexicographicOrder(Stream<Student> studentStream){
      return studentStream.sorted( Comparator
              .comparing(Student::getLastName)
              .thenComparing(Student::getFirstName).reversed() ).findFirst().get();
    }

    public double getFullSum(Stream<Student> studentStream){
      return studentStream.mapToDouble(s -> s.getCoursesResults().values().stream().reduce(0.0, (a, b) -> a + b)).reduce(0.0, (a, b) -> a + b);
    }

    public static void print(Stream<Student> studentStream) {
        Object[] array =
                studentStream.map(student ->
                        String.format("Student %s %s average: %s", student.getFirstName(), student.getLastName(),
                        Double.toString(student.getCoursesResults().values().stream().reduce(0.0, (a, b) -> (a + b / 2))))
                        )
                .toArray();
        print(array);
    }

    private static void print(Object[] objects) {
      for (Object student: objects) {
        System.out.println((String) student);
      }
    }


    public static void main(String[] args) {
        Student[] students =new Student[]{
                new Student("leon","holes", 1, new HashMap<String, Double>(){{ put("math",12.1); put("sciences",0.5); }}),
                new Student("andru","onciul", 1, new HashMap<String, Double>(){{ put("math",18.0); put("sciences",20.5); }}),
                new Student("jo","mangala", 1, new HashMap<String, Double>(){{ put("math",0.8); put("sciences",15.5); }})
        };
        Stream<Student> studentStream = Stream.of(students);
        StudentFunctions studentFunctions = new StudentFunctions();
        print(studentStream);
        studentStream = Stream.of(students);
        studentFunctions.computeAverageForStudentInSection(studentStream,1);
    }
}
