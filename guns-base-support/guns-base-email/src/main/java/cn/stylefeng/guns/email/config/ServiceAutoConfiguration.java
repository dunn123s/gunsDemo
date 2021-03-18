package cn.stylefeng.guns.email.config;

import cn.stylefeng.guns.email.provider.MailServiceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 邮件服务的配置
 *
 * @author fengshuonan
 * @Date 2018/7/6 下午3:24
 */
@Configuration
public class ServiceAutoConfiguration {

    @Bean
    public MailServiceProvider mailServiceProvider() {
        return new MailServiceProvider();
    }

}
