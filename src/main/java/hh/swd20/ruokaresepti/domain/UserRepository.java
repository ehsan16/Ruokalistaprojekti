package hh.swd20.ruokaresepti.domain;



import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
User findByUsername(String username);
}
