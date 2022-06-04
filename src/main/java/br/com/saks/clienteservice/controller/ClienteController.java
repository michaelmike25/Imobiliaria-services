package br.com.saks.clienteservice.controller;

import br.com.saks.clienteservice.model.Cliente;
import br.com.saks.clienteservice.repository.ClienteRepository;
import java.security.NoSuchAlgorithmException;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping("/cliente")

public class ClienteController {
    
    @Autowired
        private ClienteRepository clienteRepository;
    
    @GetMapping // listar 
        public List<Cliente> listarTodos(){
            return clienteRepository.findAll();
        }
    
    @GetMapping(value = "/{id}")
        public Optional<Cliente>ListarPeloId(@PathVariable Long id){
            return clienteRepository.findById(id);
        }
        
    @PostMapping
        public Cliente adicionar (@RequestBody Cliente cliente) throws NoSuchAlgorithmException{
            cliente.setSenha(createHash(cliente.getSenha()));;
            return clienteRepository.save(cliente);
        }
        
    @PutMapping(value = "/{id}")
        public ResponseEntity editar (@PathVariable Long id, @RequestBody Cliente cliente){
            Optional<Cliente> clienteResponse = clienteRepository.findById(id);
        Cliente c = clienteResponse.get();
        if(c.getStatus()==0){
            return null;             
        } return clienteRepository.findById(id)
            .map(record -> {
                record.setNome(cliente.getNome()); 
                record.setEmail(cliente.getEmail());
               try {
                record.setSenha(createHash(cliente.getSenha()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
                record.setTelefone(cliente.getTelefone());
                    Cliente clienteUpdated = clienteRepository.save(record);
                    return ResponseEntity.ok().body(clienteUpdated);
                }).orElse(ResponseEntity.notFound().build());
}

        @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(record-> {
                    clienteRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    //hash criptografia da senha
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
