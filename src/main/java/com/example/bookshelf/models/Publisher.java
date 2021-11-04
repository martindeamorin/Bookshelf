package com.example.bookshelf.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "publisher")
public class Publisher implements Serializable{
   @Id
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private Long id;
   private String name;
   @Temporal(TemporalType.DATE)
   private Date foundation;
   @OneToMany
   @JoinColumn(name = "publisher_id")
   private List<Collection> collections;
   @OneToMany
   @JoinColumn(name = "publisher_id")
   private List<Book> books;
   
}
