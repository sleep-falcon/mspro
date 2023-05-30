package middleware.whitelist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(WhiteListProperties.class)
@EnableConfigurationProperties(WhiteListProperties.class)
public class WhiteListAutoConfigure {
    @Autowired
    WhiteListProperties properties;
    @Bean("whiteListConfig") //名称为whiteListConfig
    @ConditionalOnMissingBean
    public String whiteListConfig() {
        return properties.getUsers();
    }

}