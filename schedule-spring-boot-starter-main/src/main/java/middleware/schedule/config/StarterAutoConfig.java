package middleware.schedule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration("middlware-schedule-starterAutoConfig")
@EnableConfigurationProperties(StarterServiceProperties.class)
public class StarterAutoConfig {

    @Autowired
    private StarterServiceProperties properties;

    public StarterServiceProperties getProperties() {
        return properties;
    }

    public void setProperties(StarterServiceProperties properties) {
        this.properties = properties;
    }
}
