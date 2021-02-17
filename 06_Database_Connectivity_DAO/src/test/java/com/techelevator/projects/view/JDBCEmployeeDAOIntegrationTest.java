package com.techelevator.projects.view;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;
import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;


public class JDBCEmployeeDAOIntegrationTest {
	
	private static final int TEST_EMPLOYEE = 100;


	private static SingleConnectionDataSource dataSource;
	private JDBCEmployeeDAO dao;

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
		dao = new JDBCEmployeeDAO(dataSource);
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}



	@Test 
	public void tests_changes_employee_department() {
		
		//changeEmployeeDepartment for an UPDATE test
		
	}




}
