package jah.catchflight.bestdeal;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This class enables component scanning for the {@code jah.catchflight.bestdeal} package,
 * allowing Spring to automatically detect and register beans defined in that package.
 * The {@code proxyBeanMethods} attribute is set to {@code false} to optimize performance
 * by disabling proxying of bean methods, as method-level proxying is not required for this configuration.
 */
@EnableScheduling
@Configuration(proxyBeanMethods = false)
@ComponentScan("jah.catchflight.bestdeal")
public class BestDealConfiguration {}
