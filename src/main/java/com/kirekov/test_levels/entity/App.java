package com.kirekov.test_levels.entity;

import static javax.persistence.GenerationType.IDENTITY;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    name = "app",
    uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class App {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String name;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public App setName(String name) {
    this.name = name;
    return this;
  }
}
