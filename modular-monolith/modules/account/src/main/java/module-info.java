module account {
    requires common;
    requires sharedkernel;
    requires event;
    requires static lombok;
    requires spring.tx;
    requires spring.core;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.data.jdbc;
    requires spring.data.commons;
    requires spring.data.relational;
    requires org.apache.tomcat.embed.core;
    requires jakarta.validation;
    requires org.slf4j;
    exports jah.catchflight.account;
}