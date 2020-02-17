package com.scalefocus.java.domain.local;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "episodes")
public class Episode extends Projection {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "season_id")
  private Season season;

  private Integer episodeNumber;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<Subtitles> subtitles = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL)
  private Set<Audio> audios = new HashSet<>();

}
