package com.kirekov.test_levels.audit;

import com.kirekov.test_levels.entity.Rule;
import org.springframework.stereotype.Service;

@Service
class DummyAuditService implements AuditService {

  @Override
  public void auditWrongRule(Rule rule) {
    // do nothing
  }
}
