package fr.dta.Jdbc_spring.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import fr.dta.Jdbc_spring.model.Book;

public class BookListResultSet implements ResultSetExtractor<String> {

	public String extractData(ResultSet rs) throws SQLException, DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("TITLE,").append("NB PAGES,").append("AUTHOR,").append("ID,").append("DATE");
		while(rs.next()) {
			sb.append(rs.getString("title")).append(",");
			sb.append(rs.getInt("nb_pages")).append(",");
			sb.append(rs.getString("author")).append(",");
			sb.append(rs.getInt("id")).append(",");
			sb.append(rs.getString("publication_date"));
		}
		String finalString = new String(sb);
		return finalString;
	}

}
