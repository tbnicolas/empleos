package net.itinajero.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.itinajero.model.Categoria;
import net.itinajero.repository.ICategoriasRepository;
import net.itinajero.service.ICategoriasService;

@Service
//@Primary
public class CategoriasServiceJpa implements ICategoriasService {
	
	@Autowired
	private ICategoriasRepository repoCategorias;
	
	@Override
	public void guardar(Categoria categoria) {
		this.repoCategorias.save(categoria);

	}

	@Override
	public List<Categoria> buscarTodas() {
		return this.repoCategorias.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> op = this.repoCategorias.findById(idCategoria);
		if( op.isPresent() ) {
			return op.get();
		}
		return null;
	}

}
