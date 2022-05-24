
package br.com.saks.administradorservice.controller;

import br.com.saks.administradorservice.model.Administrador;
import br.com.saks.administradorservice.repository.AdministradorRepository;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.notFound;
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
@RequestMapping
        
public class AdministradorController {
    
     @Autowired
        private AdministradorRepository administradorRepository;
     
     @GetMapping
        public List<Administrador> listarTodos(){
            return administradorRepository.findAll();
        }
        
     @GetMapping(value = "/{id}")
        public Optional <Administrador> listarPeloId(@PathVariable Long id){
            return administradorRepository.findById(id);
        }
        
     @PostMapping
        public Administrador adicionar (@RequestBody Administrador administrador){
            return administradorRepository.save(administrador);
        }
        
     @PutMapping(value = "/{id}")
        public ResponseEntity editar (@PathVariable Long id, @RequestBody Administrador administrador){
            return administradorRepository.findById(id)
            .map(record->{
                record.setNome(administrador.getNome());
                record.setEmail(administrador.getEmail());
                record.setSenha(administrador.getSenha());
                record.setStatus(administrador.getStatus());
            Administrador administradorUpdate = administradorRepository.save(record);
            return ResponseEntity.ok().body(administradorUpdate);
            }).orElse (ResponseEntity.notFound().build());
        }
        
     @DeleteMapping(value = "/{id}")
        public ResponseEntity deletar (@PathVariable Long id){
            return administradorRepository.findById(id) 
            .map(record->{
                administradorRepository.deleteById(id);
                return  ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
        }
                      
}
