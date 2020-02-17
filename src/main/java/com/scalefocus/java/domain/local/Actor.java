package com.scalefocus.java.domain.local;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "actors")
public class Actor extends MovieEmployee {
  @ManyToMany(cascade =
        {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "Actor_Movie",
        joinColumns = {
            @JoinColumn(
                name = "actor_id",
                referencedColumnName = "id"
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                name = "movie_id",
                referencedColumnName = "id"
            )
        }
    )
  private Set<Movie> movies = new HashSet<>();

  @ManyToMany(cascade =
        {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "Actor_Series",
        joinColumns = {
            @JoinColumn(
                name = "actor_id",
                referencedColumnName = "id"
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                name = "series_id",
                referencedColumnName = "id"
            )
        }
    )
  private Set<Series> series = new HashSet<>();
}
