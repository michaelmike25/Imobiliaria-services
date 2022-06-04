package br.com.saks.clienteservice.service;

import br.com.saks.clienteservice.model.Interesse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="interesse-service")

    public interface InteresseService {
        @GetMapping(value="/interesse/cliente/{id}") // pega o que a Url retorna
        Interesse[] listarImovelPeloId(@PathVariable("id") Long id); // retorno do parametro
}

