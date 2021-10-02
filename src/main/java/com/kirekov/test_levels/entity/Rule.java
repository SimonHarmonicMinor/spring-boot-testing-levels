package com.kirekov.test_levels.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rule")
public class Rule {
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

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "app_id")
  @NotNull
  @Column(nullable = false)
  private App app;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "rule_type_id")
  @NotNull
  @Column(nullable = false)
  private RuleType ruleType;

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

  public App getApp() {
    return app;
  }

  public RuleType getRuleType() {
    return ruleType;
  }

  public Rule setName(String name) {
    this.name = name;
    return this;
  }

  public Rule setKey(String key) {
    this.key = key;
    return this;
  }

  public Rule setValue(String value) {
    this.value = value;
    return this;
  }

  public Rule setApp(App app) {
    this.app = app;
    return this;
  }

  public Rule setRuleType(RuleType ruleType) {
    this.ruleType = ruleType;
    return this;
  }
}
