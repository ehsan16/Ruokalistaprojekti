package hh.swd20.ruokaresepti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RyhmaRepository extends CrudRepository<Ryhma, Long>{
List<Ryhma> findByName(String name);
}
