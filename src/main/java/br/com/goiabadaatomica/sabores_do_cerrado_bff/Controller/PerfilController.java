package br.com.goiabadaatomica.sabores_do_cerrado_bff.Controller;

import br.com.goiabadaatomica.sabores_do_cerrado_bff.Service.PerfilService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequestMapping("perfil")
@Log4j2
public class PerfilController {
    @Autowired
    private PerfilService perfilService;
    @GetMapping("listar")
    public ResponseEntity<String> listarPerfis(){
        log.info("Iniciando listagem de perfis");
        String perfisJson = perfilService.listarPerfis();
        return ResponseEntity.ok(perfisJson);
    }
}