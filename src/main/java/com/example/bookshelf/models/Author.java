package com.example.bookshelf.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "author")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class Author implements Serializable{
   @Id
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private Long id;
   private String name;
   @Temporal(TemporalType.DATE)
   @JsonFormat(pattern="dd-MM-yyyy")
   private Date birth;
   @ManyToMany
    @JoinTable(
         name = "book_author",
         joinColumns = @JoinColumn(name = "book_id"),
         inverseJoinColumns = @JoinColumn(name = "author_id"))
   private List<Book> books;

}
