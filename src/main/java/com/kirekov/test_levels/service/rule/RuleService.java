package com.kirekov.test_levels.service.rule;

import com.kirekov.test_levels.entity.Rule;
import java.util.List;

public interface RuleService {
    Rule createRule(RuleInfo ruleInfo);

    List<Rule> createRules(List<RuleInfo> ruleInfos);
}
