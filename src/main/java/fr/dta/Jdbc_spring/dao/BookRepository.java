package fr.dta.Jdbc_spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.Jdbc_spring.model.Book;

@Repository
@Transactional
public class BookRepository {

	@PersistenceContext
	private EntityManager entityManager;

	protected Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	public void persist(Object entity) {
		entityManager.persist(entity);
	}

	public List<Book> getAll() {
		try {
			List<Book> b;
			TypedQuery<Book> tq = entityManager.createQuery("select b from Book b ", Book.class);
			b = tq.getResultList();
			return b;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public Book getById(int id) {
		try {
			Book b = new Book();

			TypedQuery<Book> tq = entityManager.createQuery("select b from Book b where b.id = :id ", Book.class);
			tq.setParameter("id", id);
			b = tq.getSingleResult();

			return b;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public boolean delete(Book id) {
		try {
			entityManager.remove(id);
			
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean add(Book b) {
		try {
			entityManager.persist(b);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean update(Book b) {
		try {
			entityManager.merge(b);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

}