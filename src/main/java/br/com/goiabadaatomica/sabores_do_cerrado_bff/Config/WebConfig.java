package br.com.goiabadaatomica.sabores_do_cerrado_bff.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${frontend.url}")
    String frontEndUrl;
    @Override
    public void addCorsMappings(CorsRegistry registry){

        registry.addMapping("/**") // 1. Aplica a politica para TODOS os endpoints do BFF
                .allowedOrigins(frontEndUrl) // 2. A URL do frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 3. Metodos HTTP que o frontend pode usar
                .allowedHeaders("*") // 4. Permite todos os cabecalhos na requisicao
                .allowCredentials(true); // 5. Permite o envio de credenciais (cookies, tokens de autorizacao)
    }
}
