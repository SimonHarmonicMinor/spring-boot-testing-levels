package com.kirekov.test_levels.audit;

import com.kirekov.test_levels.entity.Rule;

/**
 * Audit event service.
 */
public interface AuditService {

  /**
   * Audit wrong rule.
   *
   * @param rule the rule
   */
  void auditWrongRule(Rule rule);
}
