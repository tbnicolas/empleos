package net.itinajero.service;

import java.util.List;

import org.springframework.data.domain.Example;

import net.itinajero.model.Vacante;

public interface IVacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	void guardar(Vacante vacante);
	List<Vacante> buscarPorDestacadas();
	List<Vacante> buscarByExample(Example<Vacante> exammple);
}
