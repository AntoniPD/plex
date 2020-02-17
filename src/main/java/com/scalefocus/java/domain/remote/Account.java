package com.scalefocus.java.domain.remote;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Account {

  @Id
  private Long id;

  private String name;
}