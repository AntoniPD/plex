package com.scalefocus.java.domain.local;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movies")
public class Movie extends Projection {

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "director_id")
  private Director director;

  @ManyToMany(mappedBy = "movies", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Set<Writer> writers = new HashSet<>();

  @ManyToMany(mappedBy = "movies", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Set<Actor> actors = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL)
  private Set<Audio> audios = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL)
  private Set<Subtitles> subtitles = new HashSet<>();

  public void addActor(Actor actor) {
    actors.add(actor);
    actor.getMovies().add(this);
  }

  public void removeActor(Actor actor) {
    actors.remove(actor);
    actor.setMovies(null);
  }

  public void addWriter(Writer writer) {
    writers.add(writer);
    writer.getMovies().add(this);
  }
}
