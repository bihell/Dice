package com.bihell.dice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bihell.dice.model.domain.SysOption;
import com.bihell.dice.service.OptionService;
import com.bihell.dice.utils.DiceUtil;
import com.bihell.dice.utils.OptionKeys;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
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

    private static final String OPTION_CACHE_NAME = "options";

    private final ApplicationContext applicationContext;


    public OptionServiceImpl(
            ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取所有设置的key-value
     *
     * @return Map
     */
    @Override
    @Cacheable(value = OPTION_CACHE_NAME, key = "'options'")
    public Map<String, String> getAllOptionMap() {
        return new SysOption().selectAll().stream().collect(Collectors.toMap(SysOption::getOptionKey, SysOption::getOptionValue));
    }

    /**
     * 获取设置value
     *
     * @param key          设置key
     * @param defaultValue 默认值
     * @return value
     */
    @SuppressWarnings("unchecked")
    @Override
    @Cacheable(value = OPTION_CACHE_NAME, key = "'option['+#key+':'+#defaultValue+']'")
    public <T> T get(String key, T defaultValue) {
        SysOption sysOption = new SysOption().selectOne(new QueryWrapper<SysOption>().lambda().eq(SysOption::getOptionKey, key));
        return (T) (sysOption == null || StringUtils.isEmpty(sysOption.getOptionValue()) ?
                defaultValue :
                DiceUtil.convertStringToType(sysOption.getOptionValue(), defaultValue.getClass()));
    }

    /**
     * 获取设置value
     *
     * @param key 设置key
     * @return value
     */
    @Override
    @Cacheable(value = OPTION_CACHE_NAME, key = "'option['+#key+']'")
    public String get(String key) {
        return this.get(key, "");
    }

    /**
     * 保存设置
     *
     * @param key   设置key
     * @param value 设置value
     */
    @Override
    @CacheEvict(value = OPTION_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public void save(String key, String value) {
        SysOption record = new SysOption();
        record.setOptionKey(key);
        SysOption sysOption = new SysOption().selectOne(new QueryWrapper<SysOption>().lambda().eq(SysOption::getOptionKey, key));
        if (null != sysOption) {
            sysOption.setOptionValue(value);
            sysOption.setModified(new Date());
            sysOption.update(new UpdateWrapper<SysOption>().lambda().eq(SysOption::getId, sysOption.getId()));
        } else {
            record.setOptionValue(value);
            record.insert();
        }
    }

    /**
     * 批量保存设置
     *
     * @param options 设置key-value
     */
    @Override
    @CacheEvict(value = OPTION_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public void save(Map<String, String> options) {
        options.forEach(this::save);
    }

    /**
     * 获取前端使用的设置
     *
     * @return Map
     */
    @Override
    @Cacheable(value = OPTION_CACHE_NAME, key = "'front_options'")
    public Map<String, String> getFrontOptionMap() {
        Map<String, String> frontOptions = new HashMap<>(16);
        Map<String, String> allOptions = getAllOptionMap();
        OptionKeys.FRONT_OPTION_KEYS.forEach(key -> frontOptions.put(key, allOptions.getOrDefault(key, "")));
        return frontOptions;
    }

    /**
     * Gets blog base url. (Without /)
     *
     * @return blog base url (If blog url isn't present, current machine IP address will be default)
     */
    @Override
    public String getBaseUrl() {
        // Get server port
        String serverPort = applicationContext.getEnvironment().getProperty("server.port", "8080");

        return String.format("http://%s:%s", DiceUtil.getMachineIP(), serverPort);
    }
}
