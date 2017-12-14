package fr.dta.Jdbc_spring.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.jdbc.core.RowCallbackHandler;

import fr.dta.Jdbc_spring.model.Book;

public class BookRowCallBackHandler implements RowCallbackHandler{
	
	private List<Book> books;
	
	public BookRowCallBackHandler() {
		books = new ArrayList<Book>();
	}

	public void processRow(ResultSet rs) throws SQLException {
		int row = 0;
		
		while(rs.next()) {
			Book b = new BookMapper().mapRow(rs, row);
			books.add(b);
			row++;
		}
	}
	
	public List<Book> getBooksList(){ return this.books; }
	
	public Set<String> getAuthorsList(){
		Set<String> ss = new TreeSet<String>();
		for (Book b : this.books) {
			ss.add(b.getAuthor());
		}
		return ss;
	}
	
	public List<String> getTitleList(){
		List<String> titles = new ArrayList();
		for (Book b : this.books) {
			titles.add(b.getTitle());
		}
		return titles;
	}
	
	public List<Book> filterByAuthors(String a){
		List<Book> bookA = new ArrayList();
		for (Book b : this.books) {
			
			if (a.equals(b.getAuthor())) {
				bookA.add(b);
			}
		}
		return bookA;
	}
	
	public Map<String, List<Book>> getMapAuthors(){
		Map<String, List<Book>> mapBook = new TreeMap();
		for (Book b : books) {
			mapBook.putIfAbsent(b.getAuthor(), new ArrayList<Book>());
		}
		for (Book b : books) {
			for (Map.Entry<String, List<Book>> entry : mapBook.entrySet()) {
				if(entry.getKey().equals(b.getAuthor())) {
					List<Book> bbb = entry.getValue();
					bbb.add(b);
					entry.setValue(bbb);
				}
			}
		}
		
		
		return mapBook;
	}

}
