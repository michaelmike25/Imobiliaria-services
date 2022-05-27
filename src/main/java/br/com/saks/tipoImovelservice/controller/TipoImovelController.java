package br.com.saks.tipoImovelservice.controller;

import br.com.saks.tipoImovelservice.model.TipoImovel;
import br.com.saks.tipoImovelservice.repository.TipoImovelRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mohalk
 */

@RestController
@RequestMapping("/tipoImovel")

public class TipoImovelController {
    
    
    @Autowired
        private TipoImovelRepository tipoImovelRepository;
    
    @GetMapping
        public List<TipoImovel> listarTodos(){
            return tipoImovelRepository.findAll();
        }
    
    @GetMapping(value = "/{id}")
        public Optional<TipoImovel>ListarPeloId(@PathVariable Long id){
            return tipoImovelRepository.findById(id);
        }
       
    @PostMapping
        public TipoImovel adicionar (@RequestBody TipoImovel tipoImovel){
            return tipoImovelRepository.save(tipoImovel);
        }
       
    @PutMapping(value = "/{id}")
        public ResponseEntity editar (@PathVariable Long id, @RequestBody TipoImovel tipoImovel){
            return tipoImovelRepository.findById(id)
            .map(record -> {
                record.setNome(tipoImovel.getNome()); 
                    TipoImovel tipoImovelUpdated = tipoImovelRepository.save(record);
                    return ResponseEntity.ok().body(tipoImovelUpdated);
                }).orElse(ResponseEntity.notFound().build());
}

        @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return tipoImovelRepository.findById(id)
                .map(record-> {
                    tipoImovelRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
