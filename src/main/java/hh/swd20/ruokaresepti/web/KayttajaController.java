package hh.swd20.ruokaresepti.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.ruokaresepti.domain.User;
import hh.swd20.ruokaresepti.domain.UserRepository;
import hh.swd20.ruokaresepti.domain.SignupForm;

@Controller
public class KayttajaController {
@Autowired
private UserRepository krepository;

@RequestMapping(value = "rekisterointi")
public String addRuoka(Model model){
	model.addAttribute("signupform", new SignupForm());
    return "rekisterointi";
}	
@RequestMapping(value = "saveuser", method = RequestMethod.POST)
public String save(@Valid @ModelAttribute("signupform") SignupForm signupform, BindingResult bindingResult) {
	if (!bindingResult.hasErrors()) { // validation errors
		if (signupform.getPassword().equals(signupform.getPasswordCheck())) { 	
    		String pwd = signupform.getPassword();
	    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
	    	String hashPwd = bc.encode(pwd);

	    	User uusiKayttaja = new User();
	    	uusiKayttaja.setPasswordHash(hashPwd);
	    	uusiKayttaja.setUsername(signupform.getUsername());
	    	uusiKayttaja.setRole("USER");
	    	if (krepository.findByUsername(signupform.getUsername()) == null) { // Check if user exists
	    		krepository.save(uusiKayttaja);
	    	}
	    	else {
    			bindingResult.rejectValue("username", "err.username", "Kayttajanimi on jo käytössä");    	
    			return "rekisterointi";		    		
	    	}
		}
		else {
			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Salasanat eivät mätchää");    	
			return "rekisterointi";
		}
	}
	else {
		return "rekisterointi";
	}
	return "redirect:/kirjaus";    	
}    
}
