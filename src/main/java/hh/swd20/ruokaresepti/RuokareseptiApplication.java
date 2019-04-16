package hh.swd20.ruokaresepti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import hh.swd20.ruokaresepti.domain.User;
import hh.swd20.ruokaresepti.domain.UserRepository;
import hh.swd20.ruokaresepti.domain.Ruoka;
import hh.swd20.ruokaresepti.domain.RuokaRepository;
import hh.swd20.ruokaresepti.domain.Ryhma;
import hh.swd20.ruokaresepti.domain.RyhmaRepository;



@SpringBootApplication
public class RuokareseptiApplication {
	private static final Logger log = LoggerFactory.getLogger(RuokareseptiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(RuokareseptiApplication.class, args);
	}
@Bean
public CommandLineRunner ruokaDemo(RuokaRepository rrepository, RyhmaRepository ryrepository, UserRepository krepository) {
	return (args) -> {
		log.info("tallenna ruokia");
		ryrepository.save(new Ryhma("Liharuoka"));
		ryrepository.save(new Ryhma("Kasvisruoka"));
		
		rrepository.save(new Ruoka("Hernekeitto", ryrepository.findByName("Liharuoka").get(0)));
		rrepository.save(new Ruoka("Pinaattikeitto", ryrepository.findByName("Kasvisruoka").get(0)));
		
		//luo käyttäjä: admin/admin   , kayttaja/kayttaja
		User kayttaja1 = new User("admin", "$2a$04$f1SECmm1k9bALiBGV20J2e2Uw.guP0J3NrvUTqFEc5SSAaOU5Qx.6", "ADMIN");
		User kayttaja2 = new User("user", "$2a$04$j.nyghRVCos4gbc2rQCL3OlybukCnmyNR97MOOb9JnB47py3lPgia", "USER");
		krepository.save(kayttaja1);
		krepository.save(kayttaja2);
		
		
		log.info("fetchaa kaikki ruuat");
		for (Ruoka ruoka : rrepository.findAll()) {
			log.info(ruoka.toString());
		}
	};
}
}
