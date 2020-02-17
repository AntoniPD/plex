package com.scalefocus.java.domain.local;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "audios")
public class Audio {
  @Id
  @GeneratedValue
  private Long id;

  private String language;
}
