package br.com.saks.tipoImovelservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
}
