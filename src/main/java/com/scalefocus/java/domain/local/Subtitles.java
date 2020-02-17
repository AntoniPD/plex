package com.scalefocus.java.domain.local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subtitles")
public class Subtitles {
  @Id
  @GeneratedValue
  private Long id;

  private String language;
}
