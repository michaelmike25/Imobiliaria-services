
package br.com.saks.administradorservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;


@Entity
@Data

public class Administrador {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 100)
        private String nome;
        
    @Column (nullable = false, unique = true, length = 200)
        private String email;
        
    @Column (nullable = false, length = 200)
        private String senha;
    
    @Column()
        private int status;
    
}
