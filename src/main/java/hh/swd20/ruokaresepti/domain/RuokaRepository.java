package hh.swd20.ruokaresepti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RuokaRepository extends CrudRepository<Ruoka, Long> {
	List<Ruoka> findByName(String name);
}
