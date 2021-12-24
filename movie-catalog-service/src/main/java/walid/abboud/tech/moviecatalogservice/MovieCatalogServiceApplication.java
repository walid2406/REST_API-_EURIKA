package walid.abboud.tech.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogServiceApplication {

	@Bean
	@LoadBalanced // go discover other services
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	public WebClient.Builder getWebClientBuilder(){
		//        create a builder as creating a rest template
		return WebClient.builder();

	}


	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
