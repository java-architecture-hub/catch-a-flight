package jah.catchflight.application;

import jah.catchflight.account.AccountConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Configuration(proxyBeanMethods = false)
@Import({
        AccountConfiguration.class
})
public class AppConfiguration {}
