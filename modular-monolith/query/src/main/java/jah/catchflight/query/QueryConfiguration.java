package jah.catchflight.query;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the query module of the application.
 * Enables component scanning for Spring beans in the {@code jah.catchflight.query} package,
 * allowing automatic detection and registration of query-related components.
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan("jah.catchflight.query")
public class QueryConfiguration {}
