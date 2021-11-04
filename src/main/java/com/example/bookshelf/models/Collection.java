package com.example.bookshelf.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity()
@Table(name = "collection")
public class Collection implements Serializable{
   @Id
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private Long id;
   private String name;
   @ManyToOne
   private Publisher publisher;
   @OneToMany
   @JoinColumn(name = "collection_id")
   private List<Book> books;
}
