package br.com.saks.interesseservice.controller;

import br.com.saks.interesseservice.model.Interesse;
import br.com.saks.interesseservice.model.InteresseIdentity;
import br.com.saks.interesseservice.repository.InteresseRepository;
import br.com.saks.interesseservice.service.ClienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mohalk
 */

@RestController
@RequestMapping("/interesse")
public class InteresseController {
    
    @Autowired
    private InteresseRepository interesseRepository;
   
    @Autowired
        private ClienteService clienteService;
    
    
    @GetMapping
    public List<Interesse> listarTodos() {
        return interesseRepository.findAll();
    }
    
    
    
    @GetMapping(value = "/{idInteresse}/{idTipoimovel}")
    public Optional<Interesse> listarPeloInteresse(@PathVariable Long idInteresse, @PathVariable Long idTipoImovel) {
        final InteresseIdentity identity = new InteresseIdentity(idInteresse, idTipoImovel);
        return interesseRepository.findById(identity);
    }

        
    @GetMapping(value="/{idInteresse}")
    public Optional<Interesse> listarPeloIdInteresse(@PathVariable Long idImovel) {
        return interesseRepository.findByInteresseIdentityIdImovel(idImovel);
    }
    
    
    @GetMapping(value="/{id}")
        public Interesse listarPeloId(@PathVariable Long id) {
        Optional<Interesse> interesseResponse= interesseRepository.findByInteresseIdentityIdImovel(id);
        Interesse interesse= interesseResponse.get();
        interesse.setCliente(clienteService.listarPeloId(interesse.getInteresseIdentity().getIdInteresse())); // passando id do cliente
            return interesse;
        }
    
    
    @PostMapping
    public Interesse adicionar(@RequestBody Interesse interesse) {
        return interesseRepository.save(interesse);
    }
    
    @DeleteMapping(value="/{idInteresse}/{idTipoimovel}")
    public ResponseEntity deletar(@PathVariable Long idInteresse, @PathVariable Long idTipoImovel) {
        final InteresseIdentity identity = new InteresseIdentity(idInteresse, idTipoImovel);
        return interesseRepository.findById(identity)
                .map(record-> {
                    interesseRepository.deleteById(identity);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
