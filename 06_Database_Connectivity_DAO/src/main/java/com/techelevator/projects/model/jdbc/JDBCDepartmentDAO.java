package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() {
		
		List<Department> departments = new ArrayList<Department>();
		
		String sql = "SELECT department.*" +
					 "FROM department"; 
					 
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while(results.next()) {
		Department department = mapRowToDepartment(results);
				departments.add(department);
			}
		
		return departments;
		
	}
				
	
	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {
		
		
		List<Department> departments = new ArrayList<Department>();
		
		String sql = "SELECT department.*" +
					 "FROM department" +
					 "WHERE name = ? ";
					 
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, nameSearch);
		
		while(results.next()) {
		Department department = mapRowToDepartment(results);
				departments.add(department);
			}
		
		return departments;
		
		}

	
	@Override
	public void saveDepartment(Department updatedDepartment) {
		
		String sqlInsertDepartment = "INSERT INTO department(department_id, name) " +
				   					"VALUES(?, ?)";
	
		jdbcTemplate.update(sqlInsertDepartment, 
				updatedDepartment.getId(),
				updatedDepartment.getName());
							  
}

		
	@Override
	public Department createDepartment(Department newDepartment) {
		
		String departmentSQL = "INSERT INTO department(department_id, name) "  +
                							"VALUES(?, ?)";
		
		jdbcTemplate.update(departmentSQL, 
				                     
				                     newDepartment.getId(), 
				                     newDepartment.getName());
			return newDepartment;	                    
	
	}

	@Override
	public Department getDepartmentById(Long id) {
		
		
		Department theDepartment = null;
		String sqlFindDepartmentById = "SELECT department.*" +
							   "FROM department " +
							   "WHERE id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindDepartmentById, id);
		if(results.next()) {
			theDepartment = mapRowToDepartment(results);
		}
		return theDepartment;
	}
		
	
	
	private Department mapRowToDepartment(SqlRowSet results) {
		Department department = new Department();
		
		department.setName(results.getString("name"));
		department.setId(results.getLong("id"));
		
        
		return department;
	}	

}
