package com.kirekov.test_levels.service.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirekov.test_levels.entity.Rule;
import com.kirekov.test_levels.entity.RuleType;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
@ActualRuleValidatorServiceQualifier
class RuleValidatorServiceImpl implements RuleValidatorService {
  private final ObjectMapper objectMapper;

  RuleValidatorServiceImpl(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public boolean isRuleValid(Rule rule, RuleType ruleType) {
    final var keysMatch = Objects.equals(rule.getKey(), ruleType.getKey());
    final var valuesMatch = Objects.equals(rule.getValue(), ruleType.getValue());
    try {
      final Map<?, ?> map = objectMapper.readerFor(Map.class).readValue(rule.getValue());
      return keysMatch && map.containsValue(ruleType.getValue());
    } catch (Exception e) {
      return keysMatch && valuesMatch;
    }
  }
}
