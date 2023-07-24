package com.mksong.mkboard3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.mksong.mkboard3.**.mappers"})
public class Mkboard3Application {

	public static void main(String[] args) {
		SpringApplication.run(Mkboard3Application.class, args);
	}

}
