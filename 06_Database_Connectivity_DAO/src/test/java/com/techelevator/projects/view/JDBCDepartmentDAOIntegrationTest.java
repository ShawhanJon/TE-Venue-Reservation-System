package com.techelevator.projects.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;



public class JDBCDepartmentDAOIntegrationTest {
	
	private static final int TEST_EMPLOYEE = 100;


	private static SingleConnectionDataSource dataSource;
	private JDBCDepartmentDAO dao;

	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	@Before
	public void setup() {
		String sqlInsertEmployee = "INSERT INTO employee (employee_id, department_id, first_name, last_name, birth_date, gender, hire_date) VALUES (?, 'Sales', 'Coach', 'Carmichael', 1900-01-01, M, 2000-01-01)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertEmployee, TEST_EMPLOYEE);
		dao = new JDBCDepartmentDAO(dataSource);
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	@Test
	public void save_new_department_and_read_it_back() throws SQLException {
		Department theDepartment = getDepartment(101, "Sales");

		dao.saveDepartment(theDepartment);
		Department savedDepartment = dao.getDepartmentById(theDepartment.getId());

		assertNotEquals(null, theDepartment.getId());
		assertDepartmentsAreEqual(theDepartment, savedDepartment);
	}
	
	@Test
	public void returns_departments_by_name() {
		String testDepartmentName = "Tech Elevator";
		int testDepartmentId = 200;
		
		Department theDepartment = getDepartment(testDepartmentId, testDepartmentName);
		dao.saveDepartment(theDepartment);

		List<Department> results = dao.searchDepartmentsByName(testDepartmentName);

		assertNotNull(results);
		assertEquals(1, results.size());
		Department savedDepartment = results.get(0);
		assertDepartmentsAreEqual(theDepartment, savedDepartment);
	}


	
	private Department getDepartment(int department_id, String name) {
		Department theDepartment = new Department();
		theDepartment.setName(name);
		
		return theDepartment;
	}
	
	private void assertDepartmentsAreEqual(Department expected, Department actual) {
		assertEquals(expected.getId(), actual.getId());
		
	}
	
	


}
