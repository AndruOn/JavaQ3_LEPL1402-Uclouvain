import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Person {

    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }

    public static void sortPerson(ArrayList<Person> persons) {
        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person person, Person t1) {
                if (person.name == t1.name) {
                    return person.age - t1.age;
                }
                if (person.name.length() > t1.name.length()) {
                    for (int i = 0; i < t1.name.length(); i++) {
                        if (person.name.charAt(i) != t1.name.charAt(i)) {
                            return person.name.charAt(i) - t1.name.charAt(i);
                        }
                    }
                    return 1;
                }else{
                    for (int i = 0; i < person.name.length(); i++) {
                        if (person.name.charAt(i) != t1.name.charAt(i)) {
                            return person.name.charAt(i) - t1.name.charAt(i);
                        }
                    }
                    return -1;
                }

            }
        };
        Collections.sort(persons, comparator);
    }

    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("Guillaume",20));
        persons.add(new Person("John",50));
        persons.add(new Person("Guillaume",10));
        persons.add(new Person("John",10));
        persons.add(new Person("Luc",5));

        sortPerson(persons);
        System.out.println(persons);


        List<Integer> p = new ArrayList<Integer>();
        str[1] = "ba";
        char character = 'a';
        System.Out.println((int) character);
    }
}


    