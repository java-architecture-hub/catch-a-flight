/**
 * Defines the common module for the CatchFlight application, providing shared functionality
 * and utilities used across other modules. This module includes packages for event handling,
 * policy enforcement, persistence, and annotations for domain and hexagonal architecture patterns.
 * It also includes controller-related components for handling web requests.
 *
 * <p>Dependencies include Lombok for reducing boilerplate code, Spring Context and Spring Web
 * for dependency injection and web-related functionality, Apache Tomcat for embedded server support,
 * and SLF4J for logging.</p>
 */
module common {
    requires static lombok;
    requires spring.context;
    requires spring.web;
    requires org.apache.tomcat.embed.core;
    requires org.slf4j;

    exports jah.catchflight.common.annotations.domain;
    exports jah.catchflight.common.annotations.event;
    exports jah.catchflight.common.events;
    exports jah.catchflight.common.annotations.hexagonal;
    exports jah.catchflight.common.policy;
    exports jah.catchflight.common.controller;
    exports jah.catchflight.common.persistence;
}
