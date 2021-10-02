package com.kirekov.test_levels.entity;

public class RuleTypeTestBuilder {

  private Long id;
  private String name = "";
  private String key = "";
  private String value = "";

  public static RuleTypeTestBuilder builder() {
    return new RuleTypeTestBuilder();
  }

  public RuleType build() {
    return new RuleType()
        .setId(id)
        .setName(name)
        .setKey(key)
        .setValue(value);
  }

  public RuleTypeTestBuilder setId(Long id) {
    this.id = id;
    return this;
  }

  public RuleTypeTestBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public RuleTypeTestBuilder setKey(String key) {
    this.key = key;
    return this;
  }

  public RuleTypeTestBuilder setValue(String value) {
    this.value = value;
    return this;
  }
}