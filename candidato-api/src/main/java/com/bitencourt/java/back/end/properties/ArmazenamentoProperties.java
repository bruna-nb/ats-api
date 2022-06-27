package com.bitencourt.java.back.end.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "file")
@Data
public class ArmazenamentoProperties {
	private String uploadDir;
}
