package br.com.saks.imovelservice.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import lombok.Data;


@Entity
@Data

public class Imovel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
    @Column(nullable = false, name="id_tipoimovel")
        private Long idTipoImovel;
    
    @Column(nullable = false, length = 100)
        private String titulo;
    
    @Column(length = 500)
        private String descricao;
    
    @Column(name="data_criacao") 
    @Temporal(javax.persistence.TemporalType.DATE)
        private Date dataCriado;
    
    @Column(name="valor", precision=8, scale=2)
        private BigDecimal valor;
        
    @Column(nullable = false)
        private Integer status;
    
    @Transient // variavel vindo de outro micro serviço
      TipoImovel tipoImovel;
    
    @Transient// variavel vindo de outro micro serviço
    Interesse[] interesse;
}
