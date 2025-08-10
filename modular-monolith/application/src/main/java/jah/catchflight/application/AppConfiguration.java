package jah.catchflight.application;

import jah.catchflight.account.AccountConfiguration;
import jah.catchflight.apisimulator.ApiSimulatorConfiguration;
import jah.catchflight.bestdeal.BestDealConfiguration;
import jah.catchflight.booking.BookingConfiguration;
import jah.catchflight.engagement.EngagementConfiguration;
import jah.catchflight.order.OrderConfiguration;
import jah.catchflight.query.QueryConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration class for the Spring Boot application, enabling auto-configuration
 * and importing specific configuration classes to set up beans for various
 * application components such as account, API simulation, best deals, booking,
 * engagement, order, and query functionalities.
 */
@EnableAutoConfiguration
@Configuration(proxyBeanMethods = false)
@Import({
        AccountConfiguration.class,
        ApiSimulatorConfiguration.class,
        BestDealConfiguration.class,
        BookingConfiguration.class,
        EngagementConfiguration.class,
        OrderConfiguration.class,
        QueryConfiguration.class
})
public class AppConfiguration {}
