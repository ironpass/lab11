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
		Predicate<Student> inTwoWeeks = (s) -> s.getBirthdate().getDayOfYear() >= localDate.getDayOfYear() && s.getBirthdate().getDayOfYear() <= localDate.getDayOfYear()+14;
		Consumer<Student> toString = (s) -> System.out.printf("%s %s will have birthday on %d %s.\n", s.getFirstname(),
				s.getLastname(), s.getBirthdate().getDayOfMonth(), s.getBirthdate().getMonth());
		List<Student> students = Registrar.getInstance().getStudents();
		Comparator<Student> byName = (s1,s2) -> s1.getFirstname().charAt(0) - s2.getFirstname().charAt(0);
		Comparator<Student> byBirthday = (s1,s2) -> s1.getBirthdate().getDayOfMonth() - s2.getBirthdate().getDayOfMonth();
		StudentApp app = new StudentApp();
		System.out.println("Sort by name: ");
		app.filterAndPrint(students, birthdayTest, toString, byName);
		System.out.println("\nSort by birthday: ");
		app.filterAndPrint(students, birthdayTest, toString, byBirthday);
		System.out.println("\nList of people who will have birthday in two weeks: ");
		app.filterAndPrint(students, inTwoWeeks, toString, byBirthday);
	}
}
