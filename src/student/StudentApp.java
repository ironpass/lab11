package student;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Display reminders of students having a birthday soon.
 * 
 * @author you
 */
public class StudentApp {

	/**
	 * Print the names (and birthdays) of students having a birtday in the
	 * specified month.
	 * 
	 * @param students
	 *            list of students
	 * @param month
	 *            the month to use in selecting bithdays
	 */
	public void filterAndPrint(List<Student> students, Predicate<Student> filter, Consumer<Student> action, Comparator<Student> sorter) {
		students.sort(sorter);
		for (Student s : students) {
			if (filter.test(s)) 
				action.accept(s);
		}
//		students.stream().filter(filter).sorted(sorter).forEach(action);
	}

	public static void main(String[] args) {
		LocalDate localDate = LocalDate.now();
		Predicate<Student> birthdayTest = (s) -> s.getBirthdate().getMonthValue() == localDate.getMonthValue();
		Consumer<Student> toString = (s) -> System.out.printf("%s %s will have birthday on %d %s.\n", s.getFirstname(),
				s.getLastname(), s.getBirthdate().getDayOfMonth(), localDate.getMonth());
		List<Student> students = Registrar.getInstance().getStudents();
		Comparator<Student> sorter = (s1,s2) -> s1.getFirstname().charAt(0) - s2.getFirstname().charAt(0);
		StudentApp app = new StudentApp();
		app.filterAndPrint(students, birthdayTest, toString, sorter);
	}
}
