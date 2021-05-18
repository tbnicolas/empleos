package net.itinajero.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

import net.itinajero.model.Vacante;
import net.itinajero.service.IVacantesService;

@Controller
public class HomeController {
	
	@Autowired
	private IVacantesService serviceVacantes;
	
	@GetMapping("/index")
	public String mostrarIndex(Authentication auth) {
		String userName = auth.getName();
		System.out.println("Nombre del usuario: "+ userName);
		
		for (GrantedAuthority rol : auth.getAuthorities()) {
			System.out.println("ROL: "+rol.getAuthority());
		}
		
		return"redirec:/";
	}
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return "tabla";
	}
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de comunicaciones");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.0);
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero  de Sistemas");
		lista.add("Auxiliar de Contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		
		model.addAttribute("empleos", lista);
		
		return "listado";
	}
	
	@GetMapping("/search")
	public String buscar(Vacante vacante) {
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion", contains()); 
		Example<Vacante> example = Example.of(vacante,matcher);
		List<Vacante> lista = this.serviceVacantes.buscarByExample(example);
		return null;
	}

	@GetMapping("/")
	public String mostrarHome(Model model) {
		//List<Vacante> lista = serviceVacantes.buscarTodas();
		//model.addAttribute("vacantes", lista);
		return "home";
	}
	
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("vacantes", this.serviceVacantes.buscarPorDestacadas());
	}
	
}
