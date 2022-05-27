
package br.com.saks.imovelservice.service;

import br.com.saks.imovelservice.model.TipoImovel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="tipoImovel-service")
public interface TipoImovelService {
    
    @GetMapping(value = "/tipoImovel/{idTipoImovel}")
    TipoImovel ListarPeloId(@PathVariable("idTipoImovel") Long idTipoImovel);
    
}

