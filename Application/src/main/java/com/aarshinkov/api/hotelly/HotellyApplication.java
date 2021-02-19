package com.aarshinkov.api.hotelly;

import com.aarshinkov.api.hotelly.uploader.Uploader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotellyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotellyApplication.class, args);
    }

    @Bean
    public Uploader uploader() {
        return new Uploader();
    }
}
