package br.com.goiabadaatomica.sabores_do_cerrado_bff.Service;

import br.com.goiabadaatomica.sabores_do_cerrado_bff.Model.PerfilDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

@Service
@Log4j2
public class PerfilService implements IPerfilService {

    private final RestTemplate restTemplate;
    private final String urlAuth;
    private static final String listarPerfilEndpoint = "/perfil/listar";
    private static final String buscaPorIdEndpoint = "/perfil/";

    public PerfilService(RestTemplate restTemplate, @Value("${auth.api.url}") String urlAuth) {
        this.restTemplate = restTemplate;
        this.urlAuth = urlAuth;
    }

    public PerfilDTO[] listarPerfis() {
        log.info("Iniciando chamada para listagem de perfis da API de autenticação: {}", listarPerfilEndpoint);
        try {
            PerfilDTO[] response = restTemplate.getForObject(urlAuth + listarPerfilEndpoint, PerfilDTO[].class);
            log.info("Resposta recebida da API de autenticacao com {} perfis.", response != null ? response.length : 0);
            return response;
        } catch (RestClientException e) {
            log.error("Erro durante a busca de perfis na API de autenticacao: {}", listarPerfilEndpoint, e);
            throw e;
        }
    }

    public PerfilDTO buscaPerfil(Integer id){
        log.info("Iniciando chamada para busca do perfil da API de autenticação: {}", buscaPorIdEndpoint);
        try {
            PerfilDTO response = restTemplate.getForObject(urlAuth + buscaPorIdEndpoint + id, PerfilDTO.class);
            log.info("Resposta recebida da API de autenticacao, perfil encontrado");
            return response;
        } catch (RestClientException e) {
            log.error("Erro durante a busca do perfil na API de autenticacao: {}", buscaPorIdEndpoint, e);
            throw e;
        }
    }
}