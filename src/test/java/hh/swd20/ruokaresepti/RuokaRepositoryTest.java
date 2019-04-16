package hh.swd20.ruokaresepti;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import hh.swd20.ruokaresepti.domain.Ruoka;
import hh.swd20.ruokaresepti.domain.RuokaRepository;
import hh.swd20.ruokaresepti.domain.Ryhma;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RuokaRepositoryTest {

	@Autowired
	private RuokaRepository rrepository;
	
	@Test
	public void findByRuokaPitaisiReturnaaRuoka() {
		List<Ruoka> ruoat = rrepository.findByName("kanakeitto");
		
		assertThat(ruoat).isNotNull();
		assertThat(ruoat.get(0).getName()).isEqualTo("kanakeitto");
	}
	@Test
	public void createNewRuoka() {
    	Ruoka ruoka = new Ruoka("poronkaristys", new Ryhma("glutooniton"));
    	rrepository.save(ruoka);
    	assertThat(ruoka.getId()).isNotNull();
    } 
	@Test 
	public void deleteRuoka() {
		Long id = ((Ruoka) rrepository.findByName("peruna")).getId();
		
		rrepository.deleteById(id);
		assertThat(rrepository.findById(id)).isEmpty();
	}
}
