package hh.swd20.ruokaresepti.web;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.swd20.ruokaresepti.domain.User;
import hh.swd20.ruokaresepti.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepository krepository;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository kayttajaRepository) {
		this.krepository = kayttajaRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User nykyinenkayttaja = krepository.findByUsername(username);
		UserDetails kayttaja = new org.springframework.security.core.userdetails.User(username, nykyinenkayttaja.getPasswordHash(),
				AuthorityUtils.createAuthorityList(nykyinenkayttaja.getRole()));
		return kayttaja;
	}


}
