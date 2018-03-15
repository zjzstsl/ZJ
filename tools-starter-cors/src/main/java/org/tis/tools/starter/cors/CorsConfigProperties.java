package org.tis.tools.starter.cors;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 跨域配置项
 * 以 tools.cors 开头
 *
 * @author Shiyunlai
 * @since 2018-03-15
 */
@Data
@ToString
@ConfigurationProperties("tools.cors")
public class CorsConfigProperties {

    /**
     * 是否启用跨域能力，默认true启用。
     */
    boolean open = true ;

    /**
     * 支持CORS请求的url
     * 默认全部url都支持，否则以逗号分割指定多个。
     */
    String mappingPath = "/**";

    /**
     * 允许那些源向服务器发起CORS请求
     * 多个以逗号分割，如："http://testip1:port1, http://testip2:port2"。默认为所有 *
     * 注意：要发送Cookie时，需要指明Origins
     */
    String allowedOrigins = "*";

    /**
     * 是否允许CORS请求向服务器发送Cookie
     * CORS请求默认不发送Cookie和HTTP认证信息。如果要把Cookie发到服务器，需要要服务器同意。
     * 注意： 如果此处设置为true要发送Cookie，那么allowedOrigins不能设为星号，必须指定明确的、与请求网页一致的域名。
     */
    boolean allowCredentials = true;

    /**
     * 允许CORS请求额外发送的头信息字段
     * 多个以逗号分割，默认为所有*
     */
    String allowedHeaders = "*";

    /**
     * 允许CORS请求调用哪些HTTP方法
     * 多个以逗号分割，如："GET,POST,PUT,DELETE"。默认为所有 *
     */
    String allowedMethods = "*";

    /**
     * 指定本次CORS请求预检的有效时间，单位秒，默认 3600（60分钟）
     * 超过有效时间后，CORS请求前重新发起预检
     */
    Long maxAge = 3600L;
}
