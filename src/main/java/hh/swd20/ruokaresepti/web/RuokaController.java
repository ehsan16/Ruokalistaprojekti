package hh.swd20.ruokaresepti.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.ruokaresepti.domain.Ruoka;
import hh.swd20.ruokaresepti.domain.RuokaRepository;
import hh.swd20.ruokaresepti.domain.RyhmaRepository;

@Controller
public class RuokaController {
	
@Autowired
private RuokaRepository rrepository;

@Autowired
private RyhmaRepository ryrepository;


//REST:llä haettavat kaikki ruoat
@RequestMapping(value="/ruoat", method = RequestMethod.GET)
public @ResponseBody List<Ruoka> ruokaListRest() {
    return (List<Ruoka>) rrepository.findAll();
} 
// REST:llä haettava ruoka ID:llä.
@RequestMapping(value="/ruoka/{id}", method = RequestMethod.GET)
public @ResponseBody Optional<Ruoka> findRuokaRest(@PathVariable("id") Long ruokaId) {	
	return rrepository.findById(ruokaId);
} 
//REST:llä lisättävä ruoka
@RequestMapping(value= "/ruoat", method=RequestMethod.POST)
public @ResponseBody Ruoka addNewRuokaRest(@RequestBody Ruoka ruoka) {
	return rrepository.save(ruoka);
}

@RequestMapping(value="/ruokalista")
public String newRuoka(Model model) {
	model.addAttribute("ruoat", rrepository.findAll());
	return "ruokalista";
}

@RequestMapping(value = "/lisaaruoka")
public String addRuoka(Model model){
	model.addAttribute("ruoka", new Ruoka());
	model.addAttribute("ryhmat", ryrepository.findAll());
    return "lisaaruoka";
} 

@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Ruoka ruoka){
    rrepository.save(ruoka);
    return "redirect:ruokalista";
} 
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/muokkaa/{id}")
public String muokkaaRuoka(@PathVariable("id") Long ruokaId, @PathVariable("id") Long ryhmaId, Model model) {
	model.addAttribute("ruoka", rrepository.findById(ruokaId));
	model.addAttribute("ryhmat", ryrepository.findAll());
    return "muokkaaruoka";
}
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/poista/{id}", method = RequestMethod.GET)
public String poistaRuoka(@PathVariable("id") Long ruokaId, Model model) {
	rrepository.deleteById(ruokaId);
    return "redirect:../ruokalista";
} 
@RequestMapping(value="/kirjaus")
public String kirjaus() {	
    return "kirjaus";
}

}
