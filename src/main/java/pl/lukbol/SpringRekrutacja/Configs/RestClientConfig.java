package pl.lukbol.SpringRekrutacja.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {
    private static final String HTTP_HEADER = "User-Agent";
    private static final String HEADER_VALUE = "ProjectSpringBootLB";

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .defaultHeader(HTTP_HEADER, HEADER_VALUE)
                .build();
    }
}