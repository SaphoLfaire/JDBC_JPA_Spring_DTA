package fr.dta.Jdbc_spring.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.Jdbc_spring.model.Book;
import fr.dta.Jdbc_spring.utils.BookListResultSet;
import fr.dta.Jdbc_spring.utils.BookMapper;
import fr.dta.Jdbc_spring.utils.BookRowCallBackHandler;

@Repository
@Transactional
public class BookDAO {

	private JdbcTemplate entityManager;
	//private EntityManager entityManager;

	@Autowired
	public void setDataSource(DataSource ds) {
		this.entityManager = new JdbcTemplate(ds);
		
	}

	public void getAllBooks() {
		int count = this.entityManager.queryForObject("select count(*) from book", Integer.class);
		System.out.println(count);
	}

	public List<Book> getAllRow() {
		List<Book> bm = this.entityManager.query("select * from book", new BookMapper());
		System.out.println("-------------");
		for (Book b : bm) {
			System.out.println(b.toString());

		}
		return bm;
	}

	public List<Book> getBookRowCallBackHandler() {
		BookRowCallBackHandler cb = new BookRowCallBackHandler();
		this.entityManager.query("select * from book", cb);
		return cb.getBooksList();
	}

	public Set<String> getAuthorsListDAO() {
		BookRowCallBackHandler cb = new BookRowCallBackHandler();
		this.entityManager.query("select * from book", cb);

		return cb.getAuthorsList();
	}

	public List<String> getTitleListDAO() {
		BookRowCallBackHandler cb = new BookRowCallBackHandler();
		this.entityManager.query("select * from book", cb);

		return cb.getTitleList();
	}

	public List<Book> filterByAuthorsDAO(String author) {
		BookRowCallBackHandler cb = new BookRowCallBackHandler();
		this.entityManager.query("select * from book", cb);
		List<Book> lb = cb.filterByAuthors(author);
		System.out.println(lb.toString());
		return lb;
	}
	
	public Map<String, List<Book>> getMapBookDAO(){
		BookRowCallBackHandler cb = new BookRowCallBackHandler();
		return cb.getMapAuthors();
	}
	
	public void exportCSV() {
		BookRowCallBackHandler cb = new BookRowCallBackHandler();
		BookListResultSet blrs = new BookListResultSet();
		this.entityManager.query("select * from book", blrs);
		try {
			
			//ecriture fichier
		}
		catch (Exception e) {
			e.getMessage();
		}

		      
	}

	public void insertBook(int id, String title, String author, Date date, int nb_pages) {

		this.entityManager.update("insert into book (id, title, author, publication_date, nb_pages) values (?,?,?,?,?)",
				id, title, author, date, nb_pages);
	}

	public void updateBook(int id, String colonne, String valeur) {
		this.entityManager.update("update book set author = ? where id = ?", valeur, id);
	}

	public void deleteBook(int id) {
		this.entityManager.update("delete from book where id = ?", id);
	}

	public void addTreeRows() {
		this.entityManager.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (2,'Hello world','Margery Tully',?,1548)",
				new Date());
		this.entityManager.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (15,'Prout','George Sand',?,123)",
				new Date());
		this.entityManager.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (1,'Javac ou le désir de vivre','Joffrey Baratheon',?,1)",
				new Date());

	}

	public void addTreeRowsBug() {
		this.entityManager.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (3,'Yolo','Caleb Joseph',?,4568)",
				new Date());
		this.entityManager.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (7,'La java des gaspard','Gaspard Proust',?,4885)",
				new Date());
		this.entityManager.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (1,'Ca pete de bon matin','Anonyme',?,451)",
				new Date());

	}

	public void addTreeRowsSleep() throws InterruptedException {
		this.entityManager.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (3,'Yolo','Caleb Joseph',?,4568)",
				new Date());
		this.entityManager.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (7,'La java des gaspard','Gaspard Proust',?,4885)",
				new Date());
		this.entityManager.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (16,'Ca pete de bon matin','Anonyme',?,451)",
				new Date());
		Thread.sleep(20000);
	}

}
