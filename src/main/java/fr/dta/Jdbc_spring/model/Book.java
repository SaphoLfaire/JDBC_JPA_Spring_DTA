package fr.dta.Jdbc_spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Book extends AbstractEntity{

	@Column
	private String title;
	@Column
	private String author;
	@Id
	@Column(name="id")
	private int id;
	@Column(name="nb_pages")
	private int nbPages;
	@Column(name="publication_date") 
	@Temporal(TemporalType.DATE)
	private Date publicationDate;

	public Book() {

	}

	public String getTitle() {		return title;	}

	public String getAuthor() {		return author;	}

	public int getId() {		return id;	}
	
	public int getNb_pages() {		return nbPages;	}

	public Date getPubli() {		return publicationDate;	}
	
	public void setId(int id) {		this.id = id;	}

	public void setTitle(String title) {		this.title = title;	}

	public void setAuthor(String author) {		this.author = author;	}

	public void setNb_pages(int nb_pages) {		this.nbPages = nb_pages;	}

	public void setPubli(Date publi) {		this.publicationDate = publi;	}

	@Override
	public String toString() {
		return "Book [id = " + id + ", title=" + title + ", author=" + author  + ", nb_pages=" + nbPages + ", publi="
				+ publicationDate + "]";
	}

	

}