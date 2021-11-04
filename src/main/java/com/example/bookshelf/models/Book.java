package com.example.bookshelf.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Builder
@Table(name = "book")
public class Book implements Serializable{
   @Id
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private Long id;
   private String title;
   private String description;
   private String ISBN;
   @Temporal(TemporalType.DATE)
   private Date published_at;
   private Integer edition;
   @ManyToOne
   private Collection collection;
   @ManyToOne
   private Publisher publisher;
   @ManyToMany
   @JoinTable(
      name = "book_author",
      joinColumns = @JoinColumn(name = "author_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
   private List<Author> authors;
    @ManyToMany
    @JoinTable(
      name = "book_genre",
      joinColumns = @JoinColumn(name = "genre_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> genres;
}
