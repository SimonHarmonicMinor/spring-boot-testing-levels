package com.kirekov.test_levels.service.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirekov.test_levels.entity.Rule;
import com.kirekov.test_levels.entity.RuleType;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
class RuleValidatorServiceImpl implements
    RuleValidatorService {

  private final ObjectMapper objectMapper;

  RuleValidatorServiceImpl(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public boolean isRuleValid(Rule rule, RuleType ruleType) {
    final var keysMatch = doKeysMatch(rule, ruleType);
    final var valuesMatch = doValuesMatch(rule, ruleType);
    try {
      final var map = objectMapper.convertValue(rule.getValue(), Map.class);
      return keysMatch && mapContainsValue(map, ruleType);
    } catch (IllegalArgumentException e) {
      return keysMatch && valuesMatch;
    }
  }

  private boolean doKeysMatch(Rule rule, RuleType ruleType) {
    return Objects.equals(rule.getKey(), ruleType.getKey());
  }

  private boolean doValuesMatch(Rule rule, RuleType ruleType) {
    return Objects.equals(rule.getValue(), ruleType.getValue());
  }

  private boolean mapContainsValue(Map<?, ?> map, RuleType ruleType) {
    return map.containsValue(ruleType.getValue());
  }
}
