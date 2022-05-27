package br.com.saks.interesseservice.repository;

import br.com.saks.interesseservice.model.Interesse;
import br.com.saks.interesseservice.model.InteresseIdentity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InteresseRepository extends JpaRepository<Interesse, InteresseIdentity> {
    //O Spring JPA irá analisar automaticamente o nome do método e criar uma consulta a partir dele.
  public Optional<Interesse> findByInteresseIdentityIdImovel(Long idImovel);
}
