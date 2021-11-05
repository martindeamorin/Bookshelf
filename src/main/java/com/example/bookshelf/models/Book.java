package com.example.bookshelf.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "book")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class Book implements Serializable{
   @Id
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private Long id;
   private String title;
   @Column(columnDefinition = "TEXT")
   private String description;
   private String ISBN;
   @Temporal(TemporalType.DATE)
   @JsonFormat(pattern="dd-MM-yyyy")
   private Date published_at;
   private Integer edition;
   @ManyToOne
   private Collection collection;
   @ManyToOne
   private Publisher publisher;
   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
      name = "book_author",
      joinColumns = @JoinColumn(name = "author_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
   private List<Author> authors;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
      name = "book_genre",
      joinColumns = @JoinColumn(name = "genre_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> genres;
}
