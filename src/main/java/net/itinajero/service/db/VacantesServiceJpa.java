package net.itinajero.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import net.itinajero.model.Vacante;
import net.itinajero.repository.IVacantesRepository;
import net.itinajero.service.IVacantesService;

@Service
@Primary
public class VacantesServiceJpa implements IVacantesService {
	
	@Autowired
	private IVacantesRepository vacantesRepo;
	
	@Override
	public List<Vacante> buscarTodas() {
		
		return this.vacantesRepo.findAll();
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		Optional<Vacante> optional = this.vacantesRepo.findById(idVacante);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		this.vacantesRepo.save(vacante);
	}

	@Override
	public List<Vacante> buscarPorDestacadas() {
		return this.vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> exammple) {
		return this.vacantesRepo.findAll(exammple);
	}

}
