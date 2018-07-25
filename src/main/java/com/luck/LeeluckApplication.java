package com.luck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.luck.mapper")
public class LeeluckApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeeluckApplication.class, args);
	}
}
