
package br.com.saks.administradorservice.controller;

import br.com.saks.administradorservice.model.Administrador;
import br.com.saks.administradorservice.repository.AdministradorRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import static org.apache.coyote.http11.Constants.a;
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
@RequestMapping("/administrador")
        
public class AdministradorController {
    
     @Autowired
        private AdministradorRepository administradorRepository;
     
     @GetMapping
        public List<Administrador> listarTodos(){
            return administradorRepository.findAll();
        }
        
     @GetMapping(value = "/{id}") // get adminstradores ativos
        public Optional <Administrador> listarPeloId(@PathVariable Long id){
            Optional<Administrador> administradorResponse = administradorRepository.findById(id);
            Administrador administrador = administradorResponse.get();
            if(administrador.getStatus()==0){
                return null;
            }
            return administradorRepository.findById(id);
        }
        
     @PostMapping
        public Administrador adicionar (@RequestBody Administrador administrador)throws NoSuchAlgorithmException{
        administrador.setSenha(createHash(administrador.getSenha()));
        return administradorRepository.save(administrador);
    }
        
     @PutMapping(value = "/{id}")
        public ResponseEntity editar(@PathVariable Long id,@RequestBody Administrador administrador){
        Optional<Administrador> administradorResponse = administradorRepository.findById(id);
        Administrador administrador2 = administradorResponse.get();
        if(administrador2.getStatus()==0){
            return null;
        } 
            return administradorRepository.findById(id)
            .map(record->{
                record.setNome(administrador.getNome());
                record.setEmail(administrador.getEmail());
            try{
                record.setSenha(createHash(administrador.getSenha()));
            }catch (NoSuchAlgorithmException ex){
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
                record.setStatus(administrador.getStatus());
            Administrador administradorUpdate = administradorRepository.save(record);
            return ResponseEntity.ok().body(administradorUpdate);
            }).orElse (ResponseEntity.notFound().build());
        }
        
     @DeleteMapping(value = "/{id}")
        public ResponseEntity deletar (@PathVariable Long id){
            return administradorRepository.findById(id) 
            .map(record->{
                record.setStatus(0);
                Administrador AdministradorUpdate = administradorRepository.save(record);
                return  ResponseEntity.ok().body(AdministradorUpdate);
            }).orElse(ResponseEntity.notFound().build());
        }
                      


 //hash
    public static String createHash(String pass) throws NoSuchAlgorithmException{
        String passwordToHash = pass;
        String salt = getSalt();
        return get_SHA_256_SecurePassword(passwordToHash,salt);
    }    
    
    private static String get_SHA_256_SecurePassword(String passwordToHash,
            String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
}