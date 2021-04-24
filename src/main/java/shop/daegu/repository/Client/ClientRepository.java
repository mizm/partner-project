package shop.daegu.repository.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.daegu.domain.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long>, ClientQuerydslRepository {

    List<Client> findByNameIn(List<String> names);
}
