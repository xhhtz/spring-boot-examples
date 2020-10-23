package cn.xhhtz.sbe.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 全局配置文件参数
 */
@Component
@ConfigurationProperties(prefix = "extconfig")
public class PropertiesBean {
    private String pathlocation;
    private String pathpattern;

    public String getPathlocation() {
        return pathlocation;
    }

    public void setPathlocation(String pathlocation) {
        this.pathlocation = pathlocation;
    }

    public String getPathpattern() {
        return pathpattern;
    }

    public void setPathpattern(String pathpattern) {
        this.pathpattern = pathpattern;
    }

}
