package br.com.saks.imovelservice.service;

import br.com.saks.imovelservice.model.Interesse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="interesse-service")
public interface InteresseService {
    @GetMapping(value="/interesse/imovel/{id}")
    Interesse[] listarClientePeloId(@PathVariable("id") Long id);
    
}