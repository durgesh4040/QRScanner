package com.qrcode.Config;

import java.awt.image.BufferedImage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class BufferedimageConverter {

	@Bean
	public HttpMessageConverter<BufferedImage> httpMessageConverter(){
		System.out.println("config");
		return new BufferedImageHttpMessageConverter();
	}
}
