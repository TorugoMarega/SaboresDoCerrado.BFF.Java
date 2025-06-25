package br.com.goiabadaatomica.sabores_do_cerrado_bff.Service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class PerfilService {
    @Autowired
    private  RestTemplate restTemplate;

    @Value("${auth.api.url}")
    private String urlAuth;

    public String listarPerfis(){
        try {
            log.info("Tentando realizar chamar listagem de perfis da api de auth");
            String response = restTemplate.getForObject(urlAuth, String.class);
            log.info("Response recebido da api de auth");
            return response;
        }
        catch (Exception e) {
            log.error("Ocorreu um erro durante a busca de perfis na api de auth: {}", e.getMessage());
            return "Erro ao buscar perfis na API de backend.";
        }
    }
}
