package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employees.*;

class CompanyTest {
private static final long ID1 = 123;
private static final int SALARY1 = 1000;
private static final String DEPARTMENT1 = "Development";
private static final long ID2 = 120;
private static final int SALARY2 = 2000;
private static final long ID3 = 125;
private static final int SALARY3 = 3000;
private static final String DEPARTMENT2 = "QA";
private static final long ID4 = 122;
private static final int SALARY4 = 4000;
Employee empl1 = new Employee(ID1, SALARY1, DEPARTMENT1);
Employee empl2 = new Employee(ID2, SALARY2, DEPARTMENT1);
Employee empl3 = new Employee(ID3, SALARY3, DEPARTMENT2);
Employee empl4 = new Employee(ID4, SALARY4, DEPARTMENT2);

Company company;
@BeforeEach
void setCompany() {
	company = new Company(new Employee[] {empl1, empl2, empl3});
}

	@Test
	void testAddEmployee() {
	 
		assertThrowsExactly(IllegalStateException.class,
		() -> company.addEmployee(empl1));
		company.addEmployee(empl4);
		Employee[] expected = {empl2, empl4, empl1, empl3};
		Employee[] actual = company.getAllEmployees();
		assertArrayEquals(expected, actual);
	}

	@Test
	void testGetEmployee() {
	 
		int salary1 = company.getEmployee(ID1).getBasicSalary();
		int salary2 = company.getEmployee(ID2).getBasicSalary();
		String department3 = company.getEmployee(ID3).getDepartment();
		assertEquals(SALARY1, salary1);
		assertEquals(SALARY2, salary2);
		assertTrue(DEPARTMENT2.equals(department3));
		assertNull(company.getEmployee(200));
	}

	@Test
	void testRemoveEmployee() {
		assertThrowsExactly(NoSuchElementException.class,
				() -> company.removeEmployee(200));
		Employee removedEmployee = company.removeEmployee(ID2);
		assertEquals(ID2, removedEmployee.getId());
		Employee[] actual = company.getAllEmployees();
		Employee[] expected = {empl1, empl3};
		assertArrayEquals(expected, actual);
	}

	@Test
	void testGetDepartmentBudget() {
		assertEquals(SALARY1+SALARY2, company.getDepartmentBudget(DEPARTMENT1));
		assertEquals(SALARY3, company.getDepartmentBudget(DEPARTMENT2));
		assertEquals(0, company.getDepartmentBudget("XXX"));
		Company comp = new Company(new Employee[] {});
		assertEquals(0, comp.getDepartmentBudget(DEPARTMENT1));
		comp = new Company(null);
		assertEquals(0, comp.getDepartmentBudget(DEPARTMENT1));
	}

	@Test
	void testIterator() {
		Employee[] expected = {empl2, empl1, empl3};
		Employee[] actual = new Employee[expected.length];
		Iterator<Employee> itr = company.iterator();
		int index = 0;
		while(itr.hasNext()) {
			actual[index++] = itr.next();
		}
		assertEquals(expected.length, index);
		assertArrayEquals(expected, actual);
		Company empty = new Company(new Employee[] {});
		Iterator<Employee> it = empty.iterator();
		assertFalse(it.hasNext());
		Company nnn = new Company(null);
		Iterator<Employee> it1 = nnn.iterator();
		assertFalse(it1.hasNext());
	}
	@Test
	void testGetAll() {
		Employee[] expected = {empl2, empl1, empl3};
		Employee[] actual = company.getAllEmployees();
		assertArrayEquals(expected, actual);
	}

}