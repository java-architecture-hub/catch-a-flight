package jah.catchflight.order;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This class enables component scanning for the {@code jah.catchflight.order} package
 * to automatically detect and register Spring beans. The {@code proxyBeanMethods}
 * attribute is set to {@code false} to optimize performance by disabling proxying
 * of bean methods, as method-level proxying is not required for this configuration.
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan("jah.catchflight.order")
public class OrderConfiguration {}
