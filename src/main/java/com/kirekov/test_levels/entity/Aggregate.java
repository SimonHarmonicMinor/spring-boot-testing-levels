package com.kirekov.test_levels.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import com.sun.istack.NotNull;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "aggregate")
public class Aggregate {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private OffsetDateTime date;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "rule_id", nullable = false)
  @NotNull
  private Rule rule;

  @NotNull
  @Column(name = "users_count", nullable = false)
  private int usersCount;

  public Long getId() {
    return id;
  }

  public OffsetDateTime getDate() {
    return date;
  }

  public Rule getRule() {
    return rule;
  }

  public int getUsersCount() {
    return usersCount;
  }

  public Aggregate setId(Long id) {
    this.id = id;
    return this;
  }

  public Aggregate setDate(OffsetDateTime dateGenerated) {
    this.date = dateGenerated;
    return this;
  }

  public Aggregate setRule(Rule rule) {
    this.rule = rule;
    return this;
  }

  public Aggregate setUsersCount(int count) {
    this.usersCount = count;
    return this;
  }
}
