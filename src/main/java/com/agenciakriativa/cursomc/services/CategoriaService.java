package com.agenciakriativa.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.agenciakriativa.cursomc.domain.Categoria;
import com.agenciakriativa.cursomc.repositories.CategoriaRepository;
import com.agenciakriativa.cursomc.services.exceptions.DataIntegrityException;
import com.agenciakriativa.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);	
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		
		find(obj.getId());
		
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		
		try {
			repo.deleteById(id);
			
		}catch(DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não e possivel excluir uma categoria que possue produtos!");
		}
		
	}

}
