package Java8.comparator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

public class ComparatorExample {

    public static void main(String[] args) {

        //сортируем по имение и возрасту
        Comparator<Person> pcomp = new PersonNameComparator().thenComparing(new PersonAgeComparator());
        TreeSet<Person> people = new TreeSet(pcomp);
        people.add(new Person("Abba", 100));
        people.add(new Person("Babba",34));
        people.add(new Person("Damma",1));
        people.add(new Person("Citta",14));

        for(Person  p : people){
            System.out.println(p.getName() + " " + p.getAge());
        }

	    Optional<Person> abba = List.of(new Person("Abba", 100), new Person("Babba",34))
		    .stream()
		    .max(Comparator.comparing(Person::getName));

    }

    private static class Person {
        private String name;
        private int age;
        public Person(String n, int a){

            name=n;
            age=a;
        }
        String getName(){return name;}
        int getAge(){return age;}
    }

    static class PersonNameComparator implements Comparator<Person>{

        public int compare(Person a, Person b){

            return a.getName().compareTo(b.getName());
        }
    }

    static class PersonAgeComparator implements Comparator<Person>{

        public int compare(Person a, Person b){

            if(a.getAge()> b.getAge())
                return 1;
            else if(a.getAge()< b.getAge())
                return -1;
            else
                return 0;
        }
    }
}
