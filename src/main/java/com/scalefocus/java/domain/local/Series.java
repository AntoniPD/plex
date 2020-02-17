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
@Table(name = "series")
public class Series extends Projection{

  @OneToMany(
      mappedBy = "series",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private Set<Season> seasons = new HashSet<>();

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "director_id")
  private Director director;

  @ManyToMany(mappedBy = "series",cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Set<Writer> writers = new HashSet<>();

  @ManyToMany(mappedBy = "series", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Set<Actor> actors = new HashSet<>();

  public void addSeason(Season season) {
    seasons.add(season);
    season.setSeries(this);
  }

  public void removeSeason(Season season) {
    seasons.remove(season);
    season.setSeries(null);
  }

  public void addActor(Actor actor) {
    actors.add(actor);
    actor.getSeries().add(this);
  }

  public void removeActor(Actor actor) {
    actors.remove(actor);
    actor.setSeries(null);
  }

  public void addWriter(Writer writer) {
    writers.add(writer);
    writer.getSeries().add(this);
  }
}
