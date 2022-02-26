package org.dzmitry.kapachou.core.config;

import org.dzmitry.kapachou.core.conditional.AuditingManager;
import org.dzmitry.kapachou.core.conditional.CacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("${conditional.creator.enabled:true}")
public class ConditionalCreatorConfiguration {

    @Bean("cacheManager")
    @ConditionalOnExpression("${conditional.creator.cache.enabled:true}")
    public CacheManager cacheManager() {
        System.out.println(">>>>> Creating cacheManager");
        return new CacheManager("EnabledCache");
    }

    @Bean("cacheManager")
    @ConditionalOnMissingBean
    public CacheManager extraCacheManager() {
        System.out.println(">>>>> Creating EXTRA cacheManager");
        return new CacheManager("ExtraAnalogCache");
    }

    @Bean
    @ConditionalOnExpression("${conditional.creator.auditing.enabled:true}")
    public AuditingManager auditingManager() {
        System.out.println(">>>>> Creating auditingManager");
        return new AuditingManager();
    }

}
