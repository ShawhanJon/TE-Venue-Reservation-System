package com.techelevator.model.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.techelevator.model.dao.CategoryDAO;

public class JDBCCategoryDAO implements CategoryDAO{
	
	
	private JdbcTemplate jdbcTemplate;

	public JDBCCategoryDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	


	@Override
	public List<String> retrieveCategoriesByVenueId(int venueID) {
		
		List<String> category = new ArrayList<String>();
		
		String sql = "SELECT category.name AS category_name,"
					+"FROM category "
					+"JOIN category_venue ON category.id = category_venue.category_id"
					+"WHERE venue_id = ?";
					
					SqlRowSet results = jdbcTemplate.queryForRowSet(sql, venueID);
		
		while(results.next()){
			
			category.add(results.getString(("category_name")));
		}
		return category;
	}

	@Override
	public List<String> retrieveAllCategories() {
		
		List<String> categories = new ArrayList<String>();
		
		String sql = "SELECT category.name AS category_name,"
					+"FROM category "
					+"ORDER BY category_name";
					
					
	SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
	while(results.next()){
		
		categories.add(results.getString(("category_name")));
	}
		
		
		return categories;
	}
	
	
	
}
