package br.com.saks.tipoImovelservice.service;

import br.com.saks.tipoImovelservice.model.Imovel;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="imovel-service")
public interface ImovelService {
    @GetMapping(value="/imovel/tipoImovel/{id}")
    List<Imovel> listarImovelPeloTipo(@PathVariable("id") Long id);
}