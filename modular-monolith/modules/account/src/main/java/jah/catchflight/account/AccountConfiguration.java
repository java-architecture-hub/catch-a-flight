package jah.catchflight.account;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

/**
 * Configuration class for the account module.
 * This class enables component scanning for the {@code jag.catchflight.account} package
 * to register beans related to account management. Proxying of bean methods is disabled
 * to optimize performance by avoiding unnecessary proxy creation.
 */
@EnableJdbcRepositories
@Configuration(proxyBeanMethods = false)
@ComponentScan("jah.catchflight.account")
public class AccountConfiguration {}
