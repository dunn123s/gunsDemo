package cn.stylefeng.guns.sys.modular.consts.service;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.sys.modular.consts.entity.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 参数配置 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-20
 */
@Component
@Slf4j
public class SysConfigInit implements CommandLineRunner {

    @Autowired
    private SysConfigService sysConfigService;

    @Resource private Environment environment;

    @Override
    public void run(String... args) {

        //初始化所有的常量
        List<SysConfig> list = sysConfigService.list();

        if (list != null && list.size() > 0) {
            for (SysConfig sysConfig : list) {
                ConstantsContext.putConstant(sysConfig.getCode(), sysConfig.getValue());
            }
            log.info("初始化常量" + list.size() + "条！");
        }

        boolean random = false;
        for(String profile : environment.getActiveProfiles()) {
            if(profile.endsWith("_random")) {
                random = true;
                break;
            }
        }
        if(random) {
            ConstantsContext.putConstant("GUNS_SYSTEM_RELEASE_VERSION", RandomStringUtils.random(10));
        }

    }
}
