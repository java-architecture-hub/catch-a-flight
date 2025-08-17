/**
 * The order module defines the order-related functionality for the CatchFlight application.
 *
 * This module provides the infrastructure for managing orders, including RESTful endpoints and
 * service logic for creating, updating, and retrieving order data. It depends on various external
 * and internal modules for dependency injection, logging, web server capabilities, and domain-specific logic.
 */
module order {
    requires common;
    requires sharedkernel;
    requires event;
    requires static lombok;
    requires spring.core;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires org.apache.tomcat.embed.core;
    requires org.slf4j;
    exports jah.catchflight.order;
}
