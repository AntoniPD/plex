package com.scalefocus.java.domain.local;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "seasons")
public class Season {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "series_id")
  private Series series;

  private Integer seasonNumber;

  private String description;

  private Double rating;

  private LocalDateTime releaseDate;


  @OneToMany(
      mappedBy = "season",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private Set<Episode> episodes = new HashSet<>();

  public void addEpisode(Episode episode) {
    episodes.add(episode);
    episode.setSeason(this);
  }

  public void removeEpisode(Episode episode) {
    episodes.remove(episode);
    episode.setSeason(null);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Season season = (Season) o;
    return id.equals(season.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
