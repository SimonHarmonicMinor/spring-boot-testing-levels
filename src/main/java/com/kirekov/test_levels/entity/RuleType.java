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
    name = "rule_type",
    uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class RuleType {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String name;

  @NotNull
  @Column(nullable = false)
  private String key;

  @NotNull
  @Column(nullable = false)
  private String value;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  public RuleType setId(Long id) {
    this.id = id;
    return this;
  }

  public RuleType setName(String name) {
    this.name = name;
    return this;
  }

  public RuleType setKey(String key) {
    this.key = key;
    return this;
  }

  public RuleType setValue(String value) {
    this.value = value;
    return this;
  }
}
