package interview.streams;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GLPreparationStreamsDemo {

	public static void main(String[] args) {
		Student student1 = new Student(1, "John", List.of("Math", "History", "Ukrainian"));
		Student student2 = new Student(2, "Albert", List.of("Math", "English", "Astronomy"));
		Student student3 = new Student(3, "Tom", List.of("Math", "English", "Astronomy"));
		Student student4 = new Student(4, "Gregory", List.of("Math", "Ukrainian", "Astronomy"));
		Student student5 = new Student(5, "Max", List.of("Math", "Ukrainian", "Astronomy"));
		Student student6 = new Student(6, "Steven", List.of("Math", "Astronomy"));
		Student student7 = new Student(7, "Bob", List.of("Math", "Astronomy"));

		List<Student> students = List.of(
			student1,
			student2,
			student3,
			student4,
			student5,
			student6,
			student7
		);
		List<Integer> studentIds = List.of(1, 5);

		//1. Знайти студентів зі списку students, які мають id зі списку studentIds

		List<Integer> satisfiedIds = students.stream()
			.map(student -> student.id)
			.filter(studentIds::contains)
			.toList();

		System.out.println(satisfiedIds);

		//2. Знайти унікальні предмети, які вивчають студенти зі списку students
		List<String> uniqueSubjects = students.stream()
			.flatMap(student -> student.subjects.stream()
				.distinct()
			)
			.toList();

		System.out.println(uniqueSubjects);

		//3. Створити мапу, де ключ - це id студента, а значення - об'єкт студента зі списку students
		Map<Integer, Student> studentsById = students.stream()
			.collect(Collectors.toMap(Student::id, Function.identity()));

		System.out.println(studentsById);

		//4. Відсортувати студентів за предметами відповідно до наведеного списку
		List<String> compareExamples = List.of("Math", "English", "History", "Ukrainian", "Astronomy");

		Comparator<Student> comparator = buildComparatorChain(compareExamples,  (s1, s2) -> 0);

		List<Student> sortedStudents = students.stream()
			.sorted(comparator)
			.toList();

		sortedStudents.forEach(
			student -> System.out.println(student.firstName)
		);
	}

	private static Comparator<Student> buildComparatorChain(
		List<String> compareExamples,
		Comparator<Student> comparator
	) {
		for (String subject : compareExamples) {
			Comparator<Student> next = Comparator.comparing(
				student -> !student.subjects().contains(subject)
			);
			comparator = comparator.thenComparing(next);
		}
		return comparator;
	}
}

class Student {

	int id;
	String firstName;
	List<String> subjects;

	public Student(
		int id,
		String firstName,
		List<String> subjects
	) {
		this.id = id;
		this.firstName = firstName;
		this.subjects = subjects;
	}

	public int id() {
		return id;
	}

	public String firsName() {
		return firstName;
	}

	public List<String> subjects() {
		return subjects;
	}

	@Override
	public String toString() {
		return "Student{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", subjects=" + subjects +
			'}';
	}
}
