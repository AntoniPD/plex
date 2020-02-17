package com.scalefocus.java.domain.local;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "directors")
public class Director extends MovieEmployee {
  @OneToMany(mappedBy = "director", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Set<Movie> movies = new HashSet<>();

  @OneToMany(mappedBy = "director", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Set<Series> series = new HashSet<>();
}
