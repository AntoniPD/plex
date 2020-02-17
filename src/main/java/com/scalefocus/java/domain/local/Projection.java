package com.scalefocus.java.domain.local;

import java.time.Duration;
import java.time.Instant;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class Projection {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  private String imdbId;

  private String name;

  private String description;

  private String genre;

  private Duration duration;

  private Instant releaseDate;

  private Double rating;
}
