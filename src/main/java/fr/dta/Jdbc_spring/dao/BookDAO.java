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
	/**
	 * BookDAO uniquement utilisé pour JDBC, ne pas utiliser avec JPA.
	 */

	private JdbcTemplate template; //hibernate s'occupe de générer le template;

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
		
	}

	/*
	 * Récupère le nombre de ligne dans la table
	 */
	public void getAllBooks() {
		int count = this.template.queryForObject("select count(*) from book", Integer.class);
		System.out.println(count);
	}

	/*
	 * Affiche toutes les lignes de la table, utilise un BookMapper()
	 * NE PAS OUBLIER LE SYSOUT
	 */
	public List<Book> getAllRow() {
		List<Book> bm = this.template.query("select * from book", new BookMapper());
		System.out.println("-------------");
		for (Book b : bm) {
			System.out.println(b.toString());
		}
		return bm;
	}

	/*
	 * Affiche toutes les lignes de la tables en utilisant un BookRowCallBackHandler();
	 */
	public List<Book> getBookRowCallBackHandler() {
		BookRowCallBackHandler cb = new BookRowCallBackHandler();
		this.template.query("select * from book", cb);
		return cb.getBooksList();
	}

	/*
	 * Affiche tous les auteurs avec un BookRowCallBackHandler();
	 */
	public Set<String> getAuthorsListDAO() {
		BookRowCallBackHandler cb = new BookRowCallBackHandler();
		this.template.query("select * from book", cb);

		return cb.getAuthorsList();
	}

	/*
	 * Affiche tous les titres avec un BookRowCallBackHandler();
	 */
	public List<String> getTitleListDAO() {
		BookRowCallBackHandler cb = new BookRowCallBackHandler();
		this.template.query("select * from book", cb);

		return cb.getTitleList();
	}

	/*
	 * Filtre par auteur avec un BookRowCallBackHandler();
	 */
	public List<Book> filterByAuthorsDAO(String author) {
		BookRowCallBackHandler cb = new BookRowCallBackHandler();
		this.template.query("select * from book", cb);
		List<Book> lb = cb.filterByAuthors(author);
		System.out.println(lb.toString());
		return lb;
	}
	
	/*
	 * Renvoie toute la table sous forme de Map(auteur;liste de livres)
	 */
	public Map<String, List<Book>> getMapBookDAO(){
		BookRowCallBackHandler cb = new BookRowCallBackHandler();
		return cb.getMapAuthors();
	}
	
	/*
	 * Ecrit toute la table dans n fichier csv 
	 * NE MARCHE PAS
	 */
	public void exportCSV() {
		BookRowCallBackHandler cb = new BookRowCallBackHandler();
		BookListResultSet blrs = new BookListResultSet();
		this.template.query("select * from book", blrs);
		try {
			
			//ecriture fichier
		}
		catch (Exception e) {
			e.getMessage();
		}

		      
	}

	/*
	 * Insertion, deletion, update dans la table.
	 */
	public void insertBook(int id, String title, String author, Date date, int nb_pages) {

		this.template.update("insert into book (id, title, author, publication_date, nb_pages) values (?,?,?,?,?)",
				id, title, author, date, nb_pages);
	}

	public void updateBook(int id, String colonne, String valeur) {
		this.template.update("update book set author = ? where id = ?", valeur, id);
	}

	public void deleteBook(int id) {
		this.template.update("delete from book where id = ?", id);
	}

	public void addTreeRows() {
		this.template.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (2,'Hello world','Margery Tully',?,1548)",
				new Date());
		this.template.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (15,'Prout','George Sand',?,123)",
				new Date());
		this.template.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (1,'Javac ou le désir de vivre','Joffrey Baratheon',?,1)",
				new Date());

	}

	public void addTreeRowsBug() {
		this.template.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (3,'Yolo','Caleb Joseph',?,4568)",
				new Date());
		this.template.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (7,'La java des gaspard','Gaspard Proust',?,4885)",
				new Date());
		this.template.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (1,'Ca pete de bon matin','Anonyme',?,451)",
				new Date());

	}

	public void addTreeRowsSleep() throws InterruptedException {
		this.template.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (3,'Yolo','Caleb Joseph',?,4568)",
				new Date());
		this.template.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (7,'La java des gaspard','Gaspard Proust',?,4885)",
				new Date());
		this.template.update(
				"insert into book (id, title, author, publication_date, nb_pages) values (16,'Ca pete de bon matin','Anonyme',?,451)",
				new Date());
		Thread.sleep(20000);
	}

}
