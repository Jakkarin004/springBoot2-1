package pccth.sp.pccthspseedservice.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
public class AppController {
	
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Value("${application.name}")
    private String applicationName;

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.timestamp}")
    private String buildTimestamp;

    @Autowired
    private MessageSource messageSource;
    
    @ApiOperation(value = "Test Display Application Informaition")
    @GetMapping(value = "/testappinfo")
    public Object testAppInfo() {
        Object[] obj = { applicationName, buildVersion, buildTimestamp };
        String msg = messageSource.getMessage("msg.app.info", obj, LocaleContextHolder.getLocale());
        logger.info("API:appinfo,value:{} ", msg);

        return msg;
    }

    @ApiOperation(value = "Display Application Informaition")
    @GetMapping(value = "/appinfo", headers = {"Authorization"})
    public Object appInfo() {
        Object[] obj = { applicationName, buildVersion, buildTimestamp };
        String msg = messageSource.getMessage("msg.app.info", obj, LocaleContextHolder.getLocale());
        logger.info("API:appinfo,value:{} ", msg);

        return msg;
    }
}
