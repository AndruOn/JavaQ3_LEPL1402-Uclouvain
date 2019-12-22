import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StudentFunctions implements StudentStreamFunction {

    @Override
    public Stream<Student> findSecondAndThirdTopStudentForGivenCourse(Stream<Student> studentStream, String name) {
        Stream<Student> results = studentStream
                .sorted( ((Comparator<Student>) (o1,o2) -> {
                      double d1 = o1.getCoursesResults().get(name);
                      double d2 = o2.getCoursesResults().get(name);
                      return Double.compare(d1,d2);
                  }).reversed()
                ).limit(3).skip(1);
        return results;
    }

    @Override
    public Object[] computeAverageForStudentInSection(Stream<Student> studentStream, int section) {
        Object[] results = studentStream
              .filter( (s) -> (s.getSection() == section) )
              .map(stud -> new Object[]{
                      String.format("% % %", "Student", stud.getFirstName(), stud.getLastName()),
                              stud.getCoursesResults().values().stream()
                                      .reduce(0.0, (a,b) -> (a+b)) / (double) stud.getCoursesResults().size() }
              ).toArray();
        return results;
    }

    @Override
    public int getNumberOfSuccessfulStudents(Stream<Student> studentStream) {
        Stream<Student> successfulStudents = studentStream
                .filter( (stud)-> {
                    Stream<Double> successOfStudent = stud.getCoursesResults().values().stream()
                            .filter((i) -> (i>10.0));
                    return successOfStudent.count() == stud.getCoursesResults().size();
                });
        return (int) successfulStudents.count();
    }

    @Override
    public Student findLastInLexicographicOrder(Stream<Student> studentStream) {
        Student last = studentStream
                .sorted((s1,s2) -> Comparator
                        .comparing(Student::getLastName)
                        .thenComparing(Student::getFirstName)
                        .reversed().compare(s1, s2))
                .limit(1).findFirst().get();
        return last;
    }

    @Override
    public double getFullSum(Stream<Student> studentStream) {
        return studentStream.map( (st) -> (st.getCoursesResults().values().stream().reduce(0.0, Double::sum)) )
                  .reduce(0.0, Double::sum);
    }

    public static void main(String[] args) {
      List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
      myList
              .stream()
              .filter(s -> s.startsWith("c"))
              .map(String::toUpperCase)
              .sorted()
              .forEach(System.out::println);
      /*
      Stream<Student> studentStream = Stream.of(
              new Student("Leon", "Holeschovsky", 1, ),
              new Student())

       */
    }
}
