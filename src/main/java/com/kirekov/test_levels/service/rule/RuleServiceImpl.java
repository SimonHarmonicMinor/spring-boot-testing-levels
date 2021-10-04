package com.kirekov.test_levels.service.rule;

import com.kirekov.test_levels.entity.Rule;
import com.kirekov.test_levels.repository.AppRepository;
import com.kirekov.test_levels.repository.RuleRepository;
import com.kirekov.test_levels.repository.RuleTypeRepository;
import com.kirekov.test_levels.service.validation.RuleValidatorService;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
class RuleServiceImpl implements RuleService {

  private final RuleValidatorService ruleValidatorService;
  private final AppRepository appRepository;
  private final RuleTypeRepository ruleTypeRepository;
  private final RuleRepository ruleRepository;

  RuleServiceImpl(
      RuleValidatorService ruleValidatorService,
      AppRepository appRepository,
      RuleTypeRepository ruleTypeRepository,
      RuleRepository ruleRepository) {
    this.ruleValidatorService = ruleValidatorService;
    this.appRepository = appRepository;
    this.ruleTypeRepository = ruleTypeRepository;
    this.ruleRepository = ruleRepository;
  }

  @Override
  @Transactional
  public Rule createRule(RuleInfo ruleInfo) {
    final var ruleType = ruleTypeRepository.findById(ruleInfo.getRuleTypeId()).orElseThrow();
    final var app = appRepository.findById(ruleInfo.getAppId()).orElseThrow();
    final var rule =
        new Rule()
            .setRuleType(ruleType)
            .setApp(app)
            .setKey(ruleInfo.getKey())
            .setValue(ruleInfo.getValue())
            .setName(ruleInfo.getName());
    if (!ruleValidatorService.isRuleValid(rule, ruleType)) {
      throw new IllegalArgumentException("Not valid rule arguments");
    }
    return ruleRepository.save(rule);
  }
}
