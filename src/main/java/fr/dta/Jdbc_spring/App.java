package fr.dta.Jdbc_spring;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

import fr.dta.Jdbc_spring.dao.BookRepository;
import fr.dta.Jdbc_spring.model.Book;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan
public class App {
	/*
	 * Attention le projet est en VALIDATE
	 */
	public App() {
		System.out.println("Start created");
	}

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		//sans jdbc
		BookRepository books = context.getBean(BookRepository.class);
		System.out.println("---tous les livres");
		System.out.println(books.getAll());
		System.out.println("---Livre C++ le meilleur");
		System.out.println(books.getById(8));
		System.out.println("---un livre en moins");
		Book b2 = new Book();
		b2.setAuthor("Anais Lassalle");
		b2.setId(23);
		b2.setNb_pages(458);
		b2.setPubli(new Date());
		b2.setTitle("La vie ou la pistolet à eau");
		books.delete(b2);
		System.out.println(books.getAll());
		System.out.println("---Un livre en plus");
		//books.add(b2);
		System.out.println(books.getAll());
		
		
		
		
		
		
		/* avec jdbc
		BookDAO books = context.getBean(BookDAO.class);
		books.getAllBooks();
		books.getAllRow();

		books.insertBook(25, "olala il fait chaud", "Marthe laFouinne", new Date(2014-12-15), 1584);
		books.updateBook(5, "author", "Jean michel doudoux");
		books.getAllRow();
		books.deleteBook(25);
		books.getAllRow();
		//books.addTreeRows();
		//books.getAllRow();
		//books.addTreeRowsBug();
		//books.getAllRow();
		/*
		try {
			books.addTreeRowsSleep();
			books.getAllRow();
			Thread.sleep(10000);
			books.getAllRow();
		}
		catch (InterruptedException i) {
			System.out.println(i.getMessage());
		}
		
		/*
		books.deleteBook(16);
		books.deleteBook(7);
		books.deleteBook(3);
		books.getAllRow();
		
		books.filterByAuthorsDAO("Anonyme");
		System.out.println(books.getTitleListDAO());
		System.out.println(books.getAuthorsListDAO());
		System.out.println(books.getMapBookDAO());
		*/
		context.close();
	}
}
