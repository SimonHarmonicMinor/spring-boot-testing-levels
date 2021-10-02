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
  @Column(name = "date_generated", nullable = false)
  private OffsetDateTime dateGenerated;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "rule_id")
  @NotNull
  @Column(nullable = false)
  private Rule rule;

  @NotNull
  @Column(nullable = false)
  private int count;

  public Long getId() {
    return id;
  }

  public OffsetDateTime getDateGenerated() {
    return dateGenerated;
  }

  public Rule getRule() {
    return rule;
  }

  public int getCount() {
    return count;
  }

  public Aggregate setId(Long id) {
    this.id = id;
    return this;
  }

  public Aggregate setDateGenerated(OffsetDateTime dateGenerated) {
    this.dateGenerated = dateGenerated;
    return this;
  }

  public Aggregate setRule(Rule rule) {
    this.rule = rule;
    return this;
  }

  public Aggregate setCount(int count) {
    this.count = count;
    return this;
  }
}
