package br.com.ifooddevweek.sacola.documentation;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private Contact contato() {
		return new Contact("Willian K.","http://www.seusite.com.br","billmetal9@gmail.com");
	}
	
	private ApiInfoBuilder informacoesApi() {
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		apiInfoBuilder.title("Sacola - API");
		apiInfoBuilder.description("Ifood Dev Week - desenvolvimento de API para sacola para delivery");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("Termo de uso: Open source");
		apiInfoBuilder.license("Licença - Dev week Dio");
		apiInfoBuilder.licenseUrl("http://www.seusite.com.br");
		apiInfoBuilder.contact(this.contato());
		return apiInfoBuilder;
	}
	
	@Bean
	public Docket detalheApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.select()
			.apis(RequestHandlerSelectors.basePackage("br.com.ifooddevweek.sacola.controllers"))
			.paths(PathSelectors.regex("/ifood-devweek/.*"))
			.build()
			.apiInfo(this.informacoesApi().build())
			.consumes(new HashSet<String>(Arrays.asList("application/json")))
			.produces(new HashSet<String>(Arrays.asList("application/json")));
		return docket;
	}  
}
