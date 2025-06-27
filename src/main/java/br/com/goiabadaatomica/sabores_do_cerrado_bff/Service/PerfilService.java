package br.com.goiabadaatomica.sabores_do_cerrado_bff.Service;

import br.com.goiabadaatomica.sabores_do_cerrado_bff.Model.PerfilDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Log4j2
public class PerfilService implements IPerfilService {

    private final RestTemplate restTemplate;
    private final String urlAuthApiBase;

    public PerfilService(RestTemplate restTemplate, @Value("${auth.api.url}") String urlAuthApiBase) {
        this.restTemplate = restTemplate;
        this.urlAuthApiBase = urlAuthApiBase;
    }
    @Override
    public PerfilDTO[] listarPerfis() {
        String listarPerfilEndpoint = "/perfil/listar";
        String url = UriComponentsBuilder.fromHttpUrl(urlAuthApiBase)
                .path(listarPerfilEndpoint)
                        .toUriString();
        log.info("Iniciando chamada para listagem de perfis da API de autenticação: {}", listarPerfilEndpoint);
        try {
            PerfilDTO[] response = restTemplate.getForObject(url, PerfilDTO[].class);
            log.info("Resposta recebida da API de autenticacao com {} perfis.", response != null ? response.length : 0);
            return response;
        } catch (RestClientException e) {
            log.error("Erro durante a busca de perfis na API de autenticacao: {}", listarPerfilEndpoint, e);
            throw e;
        }
    }

    @Override
    public PerfilDTO buscaPerfil(Integer id){
        String buscaPorIdEndpoint = "/perfil/{id}";
        String url = UriComponentsBuilder.fromHttpUrl(urlAuthApiBase)
                .path(buscaPorIdEndpoint)
                .buildAndExpand(id)
                .toUriString();

        log.info("Iniciando chamada para busca do perfil da API de autenticação: {}", buscaPorIdEndpoint.replace("{id}", id.toString()));
        try {
            ResponseEntity<PerfilDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,PerfilDTO.class);
            if(responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                log.warn("Perfil com id {} não encontrado na API de autenticacao.", id);
                return null;
            }
            log.info("Resposta recebida da API de autenticacao, perfil encontrado");
            return responseEntity.getBody();

        }
        catch (HttpClientErrorException.NotFound e) {
            log.warn("Perfil com id {} não encontrado na API de autenticacao.", id);
            return null;
        }
        catch (RestClientException e) {
            log.error("Erro durante a busca do perfil na API de autenticacao: {}", buscaPorIdEndpoint, e);
            throw e;
        }
    }
}