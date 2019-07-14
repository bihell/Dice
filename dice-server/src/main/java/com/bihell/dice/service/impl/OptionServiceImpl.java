package com.bihell.dice.service.impl;

import com.bihell.dice.mapper.OptionMapper;
import com.bihell.dice.model.domain.SysOption;
import com.bihell.dice.service.OptionService;
import com.bihell.dice.util.DiceUtil;
import com.bihell.dice.util.OptionKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 设置 Service 实现类
 *
 * @author bihell
 * @since 2019-05-20 22:45
 */
@Service("optionService")
@Transactional(rollbackFor = Throwable.class)
public class OptionServiceImpl implements OptionService {

    public static final String OPTION_CACHE_NAME = "options";

    @Autowired
    private OptionMapper optionMapper;

    @Override
    @Cacheable(value = OPTION_CACHE_NAME, key = "'options'")
    public Map<String, String> getAllOptionMap() {
        return optionMapper.selectAll()
                .stream()
                .collect(Collectors.toMap(SysOption::getOptionKey, SysOption::getOptionValue));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Cacheable(value = OPTION_CACHE_NAME, key = "'option['+#key+':'+#defaultValue+']'")
    public <T> T get(String key, T defaultValue) {
        SysOption record = new SysOption();
        record.setOptionKey(key);
        SysOption sysOption = optionMapper.selectOne(record);
        return (T) (sysOption == null || StringUtils.isEmpty(sysOption.getOptionValue()) ?
                defaultValue :
                DiceUtil.convertStringToType(sysOption.getOptionValue(), defaultValue.getClass()));
    }

    @Override
    @Cacheable(value = OPTION_CACHE_NAME, key = "'option['+#key+']'")
    public String get(String key) {
        return this.get(key, "");
    }

    @Override
    @CacheEvict(value = OPTION_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public void save(String key, String value) {
        SysOption record = new SysOption();
        record.setOptionKey(key);
        SysOption sysOption = optionMapper.selectOne(record);
        if (null != sysOption) {
            sysOption.setOptionValue(value);
            sysOption.setModified(new Date());
            optionMapper.updateByPrimaryKeySelective(sysOption);
        } else {
            record.setOptionValue(value);
            optionMapper.insertSelective(record);
        }
    }

    @Override
    @CacheEvict(value = OPTION_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public void save(Map<String, String> options) {
        options.forEach(this::save);
    }

    @Override
    @Cacheable(value = OPTION_CACHE_NAME, key = "'front_options'")
    public Map<String, String> getFrontOptionMap() {
        Map<String, String> frontOptions = new HashMap<>(16);
        Map<String, String> allOptions = getAllOptionMap();
        OptionKeys.FRONT_OPTION_KEYS.forEach(key -> frontOptions.put(key, allOptions.getOrDefault(key, "")));
        return frontOptions;
    }

}
