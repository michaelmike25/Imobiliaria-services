
package br.com.saks.imovelservice.controller;

import br.com.saks.imovelservice.model.Imovel;
import br.com.saks.imovelservice.repository.ImovelRepository;
import br.com.saks.imovelservice.service.InteresseService;
import br.com.saks.imovelservice.service.TipoImovelService;
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


@RestController
@RequestMapping("/imovel")
public class ImovelController {
    
    @Autowired
    private ImovelRepository imovelRepository;
    
    @Autowired
    private TipoImovelService tipoImovelService;
    
   @Autowired
    private InteresseService interesseService;
    
    @GetMapping
    public List<Imovel> listarTodos() {
        return imovelRepository.findAll();
    }
    
   @GetMapping(value="/{id}")
    public Imovel listarPeloId(@PathVariable Long id) {
        Optional <Imovel> imovelResponse = imovelRepository.findById(id);
        Imovel imovel = imovelResponse.get();
        if(imovel.getStatus()==0){
            return null;
        }        
        imovel.setTipoImovel(tipoImovelService.ListarPeloId(imovel.getIdTipoImovel()));
        return imovel;
        
    }
    
    @GetMapping(value="/tipoImovel/{idTipoImovel}")
    public List<Imovel> listarImovelPeloTipo(@PathVariable Long idTipoImovel){
        List<Imovel> imovelResponse = new ArrayList<Imovel>();
        for(Imovel imovel :imovelRepository.findAll()){
        if(imovel.getIdTipoImovel().equals(idTipoImovel) && imovel.getStatus()==1){
            imovelResponse.add(imovel);
            }
        }        
        return imovelResponse;
    }
    
    
@GetMapping(value="/interesse/{id}")
    public Imovel listarClientePeloId(@PathVariable Long id) {
        Optional<Imovel> clienteResponse = imovelRepository.findById(id);
        Imovel imovel = clienteResponse.get();
        if(imovel.getStatus() ==0 ){
            return null;
        }
        imovel.setTipoImovel(tipoImovelService.ListarPeloId(imovel.getIdTipoImovel()));
        imovel.setInteresse(interesseService.listarClientePeloId(id));
    
        return imovel;
    }
    
    @PostMapping
    public Imovel adicionar(@RequestBody Imovel imovel) {
        return imovelRepository.save(imovel);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Imovel imovel) {
        return imovelRepository.findById(id)
                .map(record -> {
                    record.setTitulo(imovel.getTitulo());
                    record.setDataCriado(imovel.getDataCriado());
                    record.setDescricao(imovel.getDescricao());
                    record.setStatus(1);
                    record.setValor(imovel.getValor());
                    Imovel imovelUpdated = imovelRepository.save(record);
                    return ResponseEntity.ok().body(imovelUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}") // mudanca de exclusão por modificacao do status
    public ResponseEntity deletar(@PathVariable Long id) {
        return imovelRepository.findById(id)
                .map(record-> {
                    record.setStatus(0);
                    Imovel imovelUpdate = imovelRepository.save(record);
                    return ResponseEntity.ok().body(imovelUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }
}
