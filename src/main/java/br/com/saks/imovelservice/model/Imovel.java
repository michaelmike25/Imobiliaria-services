/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.saks.imovelservice.model;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import lombok.Data;

/**
 *
 * @author Mohalk
 */
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
    
    @Column(nullable = false, length = 500)
        private String descricao;
    
    @Column(name="data_criacao") 
    @Temporal(javax.persistence.TemporalType.DATE)
        private Date dataCriado;
    
    @Column(name="valor", precision=8, scale=2)
        private BigDecimal valor;
        
    @Column(nullable = false)
        private Integer status;
}
