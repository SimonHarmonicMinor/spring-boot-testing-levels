package com.kirekov.test_levels.service.validation;

import com.kirekov.test_levels.entity.Rule;
import com.kirekov.test_levels.entity.RuleType;

/**
 * Service that checks {@linkplain Rule} validness.
 */
public interface RuleValidatorService {

  /**
   * Checks whether the given {@linkplain Rule} is valid according to provided {@linkplain
   * RuleType}.
   *
   * @param rule     the rule
   * @param ruleType the rule type
   * @return the validness status
   */
  boolean isRuleValid(Rule rule, RuleType ruleType);
}
