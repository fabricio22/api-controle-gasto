package br.com.controle.gastos;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket apiConfigDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(infoDocs())
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.controle.gastos.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo infoDocs() {
		return new ApiInfo("Api Rest - Controle de Gastos", "Api para controle de gastos diário", "1.0.0", "Termo",
				new Contact("Fabrício Nascimento Pires", "", "Fanpir13@hotmail.com"), "Apache License", "URL",
				new ArrayList<VendorExtension>());
	}
}
