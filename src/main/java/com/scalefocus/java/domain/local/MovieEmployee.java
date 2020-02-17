package com.scalefocus.java.domain.local;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class MovieEmployee {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  private String firstName;

  private String lastName;
}
