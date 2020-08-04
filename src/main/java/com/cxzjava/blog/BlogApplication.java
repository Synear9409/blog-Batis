package com.cxzjava.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@MapperScan("com.cxzjava.blog.dao")
@SpringBootApplication
public class BlogApplication {

    /*private static final Logger log = LoggerFactory.getLogger(BlogApplication.class);*/

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
        /*log.info("================info================");
        log.info("================info================");
        log.info("================info================");
        log.info("================info================");

        log.warn("+++++++++++++++++warn+++++++++++++++");
        log.warn("+++++++++++++++++warn+++++++++++++++");
        log.warn("+++++++++++++++++warn+++++++++++++++");

        log.debug("===========debug================");
        log.debug("===========debug================");
        log.debug("===========debug================");

        log.error("===========debug================");
        log.error("===========debug================");
        log.error("===========debug================");*/
    }

}
