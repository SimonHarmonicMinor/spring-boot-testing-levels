package com.kirekov.test_levels.entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    name = "app",
    uniqueConstraints = @UniqueConstraint(columnNames = {"name", "device"})
)
public class App {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String name;

  @NotNull
  @Column(nullable = false)
  @Enumerated(STRING)
  private Device device;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Device getDevice() {
    return device;
  }

  public App setId(Long id) {
    this.id = id;
    return this;
  }

  public App setName(String name) {
    this.name = name;
    return this;
  }

  public App setDevice(Device device) {
    this.device = device;
    return this;
  }
}
