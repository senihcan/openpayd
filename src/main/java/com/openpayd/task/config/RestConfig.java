package com.openpayd.task.config;

import java.util.Collections;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RestConfig {

	@Autowired
	private Environment env;

	@Bean
	@Primary
	@Qualifier("restTemplateSd")
	public RestTemplate restTemplate() {
		int timeout = 300000;
		String timeoutProperty = null;
		try {
			timeoutProperty = env.getProperty("resttemplate.connectiontimeout");
			timeout = Integer.parseInt(timeoutProperty);
		} catch (Exception e) {
			log.warn("couldn't read resttemplate.connectiontimeout");
		}
		log.info("resttemplate.connectiontimeout is {}", timeout);

		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
				.setSocketTimeout(timeout).build();

		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config)
				.setSSLHostnameVerifier(new NoopHostnameVerifier())
				.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).setMaxConnTotal(200)
				.setMaxConnPerRoute(200).build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(requestFactory);
		RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
		return restTemplate;
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
		return mapper;
	}

}
