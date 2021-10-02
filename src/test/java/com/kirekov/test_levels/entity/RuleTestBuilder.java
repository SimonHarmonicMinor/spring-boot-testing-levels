package com.kirekov.test_levels.entity;

public class RuleTestBuilder {

  private Long id;
  private String name = "";
  private String key = "";
  private String value = "";
  private App app;
  private RuleType ruleType;

  public static RuleTestBuilder builder() {
    return new RuleTestBuilder();
  }

  public Rule build() {
    return new Rule()
        .setId(id)
        .setName(name)
        .setKey(key)
        .setValue(value)
        .setApp(app)
        .setRuleType(ruleType);
  }

  public RuleTestBuilder setId(Long id) {
    this.id = id;
    return this;
  }

  public RuleTestBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public RuleTestBuilder setKey(String key) {
    this.key = key;
    return this;
  }

  public RuleTestBuilder setValue(String value) {
    this.value = value;
    return this;
  }

  public RuleTestBuilder setApp(App app) {
    this.app = app;
    return this;
  }

  public RuleTestBuilder setRuleType(RuleType ruleType) {
    this.ruleType = ruleType;
    return this;
  }
}