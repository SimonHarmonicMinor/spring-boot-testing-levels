package com.kirekov.test_levels.service.validation;

import com.kirekov.test_levels.audit.AuditService;
import com.kirekov.test_levels.entity.Rule;
import com.kirekov.test_levels.entity.RuleType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AuditingRuleValidatorService implements RuleValidatorService {

  @ActualRuleValidatorServiceQualifier
  private final RuleValidatorService ruleValidatorService;
  private final AuditService auditService;

  public AuditingRuleValidatorService(
      RuleValidatorService ruleValidatorService,
      AuditService auditService) {
    this.ruleValidatorService = ruleValidatorService;
    this.auditService = auditService;
  }

  @Override
  public boolean isRuleValid(Rule rule, RuleType ruleType) {
    final var res = ruleValidatorService.isRuleValid(rule, ruleType);
    if (!res) {
      auditService.auditWrongRule(rule);
    }
    return res;
  }
}
