package fr.dta.Jdbc_spring.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import fr.dta.Jdbc_spring.model.Book;

public final class BookMapper implements RowMapper<Book> {

	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book b = new Book();
		b.setTitle(rs.getString("title"));
		b.setNb_pages(rs.getInt("nb_pages"));
		b.setAuthor(rs.getString("author"));
		b.setId(rs.getInt("id"));
		//b.setPubli(rs.getString("publication_date"));
		
		return b;
	}
}