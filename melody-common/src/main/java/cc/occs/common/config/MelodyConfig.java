package cc.occs.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "melody")
public class MelodyConfig {

    private String version;

    private String author;

    private String link;

}
