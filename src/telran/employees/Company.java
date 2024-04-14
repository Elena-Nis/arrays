package telran.employees;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;
//SO far we don't consider optimization
public class Company implements Iterable {
	private Employee[] employees;
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
		 	int index = Arrays.binarySearch(
			employees, new Employee(id, 0, ""), (e1,e2) -> e1.compareTo(e2));
		return index<0 ? null : employees[index];
	}
	public Employee removeEmployee(long id) {
		Employee removedEmployee = getEmployee(id);
		if(removedEmployee == null) {
			throw new NoSuchElementException();
		}
		employees = Arrays.removeIf(employees, e -> e.getId()==id);
		return removedEmployee;
	}
	public int getDepartmentBudget(String department) {
		Employee[] emps = Arrays.search(employees, e -> e.getDepartment().equals(department));
		int budget = 0;
		for(Employee emp : emps) {
			budget += emp.getBasicSalary();
		}
		return budget;
	}
	public Company(Employee[] employees) {
		 
		if(employees != null) {
			this.employees = Arrays.copy(employees);
			Arrays.bubbleSort(this.employees);
		} else {
			this.employees = new Employee[0];
		}
	}
	 
	public Employee[] getAllEmployees() {
		return Arrays.copy(employees);
	}
	@Override
	public Iterator<Employee> iterator() {
		
		return new CompanyIterator();
	}
	private class CompanyIterator implements Iterator<Employee> {
		 
		int current = 0;
		@Override
		public boolean hasNext() {
			 
			return current < employees.length;
		}

		@Override
		public Employee next() {
			if(!hasNext()) {
                throw new NoSuchElementException();
			}
			return employees[current++];
		}
		
	}
}