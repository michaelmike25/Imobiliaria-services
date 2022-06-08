package br.com.saks.tipoImovelservice.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Data;

/**
 *
 * @author Mohalk
 */

@Entity
@Data
public class TipoImovel {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;
    
    @Column(nullable = false, length = 100)
        private String nome;
    
    @Transient
            List<Imovel> imovel;
}
