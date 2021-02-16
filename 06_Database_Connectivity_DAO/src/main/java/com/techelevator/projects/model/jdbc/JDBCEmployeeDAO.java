package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		
		
		List<Employee> allEmployees = new ArrayList<Employee>();
		
		String sql = "SELECT employee.*" +
					 "FROM employee"; 
					 
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while(results.next()) {
		Employee employee = mapRowToEmployee(results);
				allEmployees.add(employee);
			}
		
		return allEmployees;
		
	}
		
		
		

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
	
		
List<Employee> searchEmployees = new ArrayList<Employee>();
		
		String sql = "SELECT employee.*" +
					 "FROM employee" +
					 "WHERE name = ? ";
					 
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, firstNameSearch, lastNameSearch);
		
		while(results.next()) {
		Employee employee = mapRowToEmployee(results);
				searchEmployees.add(employee);
			}
		
		return searchEmployees;
		
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id) {
		
List<Employee> departmentEmployees = new ArrayList<Employee>();
		
		String sql = "SELECT employee.*" +
					 "FROM employee" +
					 "WHERE employee_id = ? ";
					 
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		
		while(results.next()) {
		Employee employee = mapRowToEmployee(results);
				departmentEmployees.add(employee);
			}
		
		return departmentEmployees;
	}
		
		
	

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
	
		
List<Employee> noProjectEmployees = new ArrayList<Employee>();
		
		String sql = "SELECT employee.*" +
					 "FROM employee" +
					 "JOIN project_employee ON employee.employee_id = project_employee.employee.id" +
					 "WHERE project_id = null ";
					 
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while(results.next()) {
		Employee employee = mapRowToEmployee(results);
				noProjectEmployees.add(employee);
			}
		
		return noProjectEmployees;
	}
	

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		
		List<Employee> projectEmployees = new ArrayList<Employee>();
		
		String sql = "SELECT employee.*" +
					 "FROM employee" +
					 "JOIN project_employee ON employee.employee_id = project_employee.employee.id" +
					 "WHERE project_id != null ";
					 
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while(results.next()) {
		Employee employee = mapRowToEmployee(results);
				projectEmployees.add(employee);
			}
		
		return projectEmployees;
	}
	
	

	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId) {
		
	
			String sqlChangeEmployeeDepartment = "UPDATE employee " +
					   					"SET department_id = ? " +
					   					"WHERE employee_id = ? ";
		
			jdbcTemplate.update(sqlChangeEmployeeDepartment, 
					employeeId,
					departmentId);
								  
	}

		
	
	
	private Employee mapRowToEmployee(SqlRowSet results) {
		Employee employee = new Employee();
		
		employee.setFirstName(results.getString("first_name"));
		employee.setId(results.getLong("employee_id"));
		employee.setLastName(results.getString("last_name"));
		employee.setBirthDay(results.getDate("birth_date").toLocalDate());
		employee.setDepartmentId(results.getLong("department_id"));
		employee.setHireDate(results.getDate("hire_date").toLocalDate());
		employee.setGender(results.getString("gender"));
		
		return employee;
	}	

}
