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
