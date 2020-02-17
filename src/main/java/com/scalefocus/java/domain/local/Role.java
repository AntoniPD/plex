package com.scalefocus.java.domain.local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue
  private Long id;

  private RoleName name;
}
