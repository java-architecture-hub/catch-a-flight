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
