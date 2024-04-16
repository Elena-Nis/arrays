package telran.employees;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import telran.util.Arrays;
public class Company implements Iterable<Employee>{
	private Employee[] employees;
	public Company(Employee[] employees) {
		this.employees = Arrays.copy(employees);
		Arrays.bubbleSort(this.employees);
	}
public void addEmployee(Employee empl) {
		
		if(empl==null) {
			throw new IllegalArgumentException();
		}
		int index = Arrays.binarySearch(employees, empl, (e1,e2)->e1.compareTo(e2));
		if(index>=0) {
			throw new IllegalStateException();
		}
		employees = Arrays.insert(employees, empl, -(index+1));
	}
	public Employee getEmployee(long id) {
		int index = Arrays.binarySearch(employees,  new Employee(id, 0, null), Comparator.naturalOrder());
		return index < 0 ? null : employees[index];
	}
	public Employee removeEmployee(long id) {
		Employee result = getEmployee(id);
		if(result == null) {
			throw new NoSuchElementException
			(String.format("Employee with id %d doesn't exist", id));
		}
		employees = Arrays.removeIf(employees, e -> e.getId() == id);
		return result;
	}
	public int getDepartmentBudget(String department) {
		int result = 0;
		for(Employee empl: employees) {
			if(empl.getDepartment().equals(department)) {
				result += empl.computeSalary();
			}
		}
		return result;
	}
	public Employee[] getAllEmployees() {
		return Arrays.copy(employees);
	}
	public String[] getDepartments() {
		String[] departments = {};
		for(Employee empl: employees) {
			String dep = empl.getDepartment();
			if (Arrays.indexOf(departments, dep) < 0) 
				departments=Arrays.add(departments, dep);
		}
		return departments;
	}
	@Override
	public Iterator<Employee> iterator() {
		return new CompanyIterator();
	}
	private class CompanyIterator implements Iterator<Employee> {
		int currentIndex = 0;
		@Override
		public boolean hasNext() {
			return currentIndex < employees.length;
		}
		@Override
		public Employee next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return employees[currentIndex++];
		}

	}

	
}