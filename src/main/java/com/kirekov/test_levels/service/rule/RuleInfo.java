package com.kirekov.test_levels.service.rule;

public class RuleInfo {

  private final String name;
  private final String key;
  private final String value;
  private final Long ruleTypeId;
  private final Long appId;

  public RuleInfo(String name, String key, String value, Long ruleTypeId, Long appId) {
    this.name = name;
    this.key = key;
    this.value = value;
    this.ruleTypeId = ruleTypeId;
    this.appId = appId;
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

  public Long getRuleTypeId() {
    return ruleTypeId;
  }

  public Long getAppId() {
    return appId;
  }
}
