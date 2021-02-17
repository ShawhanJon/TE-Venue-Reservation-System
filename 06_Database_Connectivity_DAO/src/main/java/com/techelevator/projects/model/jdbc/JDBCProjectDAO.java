package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.ProjectDAO;

public class JDBCProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCProjectDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Project> getAllActiveProjects() {
		
	List<Project> allProjects = new ArrayList<Project>();
		
		String sql = "SELECT project.*" +
					 "FROM project"; 
					 
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while(results.next()) {
		Project project = mapRowToProject(results);
		allProjects.add(project);
			}
		
		return allProjects;
		
	}
	
		
		

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
		
jdbcTemplate.update("DELETE FROM project_employee WHERE project_id = ? AND employee_id = ?" ,projectId, employeeId);
}
		


	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
		
		String employeeToProjectSQL = "INSERT INTO project_employee (project_id, employee_id )" +
											"VALUES(?, ?)";
		
		jdbcTemplate.update(employeeToProjectSQL, 
				                   
				                     projectId,
				                     employeeId
				                     
				                     );
		
		
	}
	
	private Project mapRowToProject(SqlRowSet results) {
		Project project = new Project();
		
		project.setId(results.getLong("project_id"));
		project.setName(results.getString("name"));
		project.setEndDate(results.getDate("from_date").toLocalDate());
		project.setEndDate(results.getDate("to_date").toLocalDate());
		
		return project;
	}	

}
