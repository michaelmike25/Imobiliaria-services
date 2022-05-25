package br.com.saks.imovelservice.repository;

import br.com.saks.imovelservice.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mohalk
 */
@Repository

public interface ImovelRepository extends JpaRepository<Imovel, Long>{
    
}
