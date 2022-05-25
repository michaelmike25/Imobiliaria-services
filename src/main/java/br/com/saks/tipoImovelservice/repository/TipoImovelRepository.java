
package br.com.saks.tipoImovelservice.repository;

import br.com.saks.tipoImovelservice.model.TipoImovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mohalk
 */
@Repository
public interface TipoImovelRepository extends JpaRepository <TipoImovel, Long>{
    
}
