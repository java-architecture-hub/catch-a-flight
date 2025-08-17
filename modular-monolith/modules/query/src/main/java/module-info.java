/**
 * The query module defines the query-related functionality for the CatchFlight application.
 * <p>
 * This module provides the infrastructure for handling queries, including RESTful endpoints and
 * service logic for retrieving data. It depends on several external and internal modules to
 * enable dependency injection, logging, web server capabilities, and domain-specific logic.
 */
module query {
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
    exports jah.catchflight.query;
}
