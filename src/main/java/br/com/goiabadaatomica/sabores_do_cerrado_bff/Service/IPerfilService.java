package br.com.goiabadaatomica.sabores_do_cerrado_bff.Service;

import br.com.goiabadaatomica.sabores_do_cerrado_bff.Model.PerfilDTO;

public interface IPerfilService {
    public PerfilDTO[] listarPerfis();
    public PerfilDTO buscaPerfil(Integer id);
}
