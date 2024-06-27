package org.zabalburu.servicio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.zabalburu.exception.ProfesorNotFound;
import org.zabalburu.modelo.Curso;
import org.zabalburu.modelo.CursoDTO;
import org.zabalburu.modelo.TipoCurso;
import org.zabalburu.repo.CursoTipoRepo;
import org.zabalburu.repo.CursosRepo;


@Service
public class Servicio {


	private final String URL_PROFESORES  = "http://servicio-profesores/profesores";

	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CursosRepo CursoRepo;
	
	@Autowired
	private CursoTipoRepo TipoCursoRepo;
	
	
	public CursoDTO getCursoDTO(Integer numPagina){
		PageRequest pg = PageRequest.of(numPagina, 10);
		Page<Curso> page = CursoRepo.findAll(pg);
		return new CursoDTO(numPagina + 1, page.getTotalPages(), page.getContent());
	}
	/*
	public List<Curso> getCursos() {
        return CursoRepo.findAll();
    }
	*/
	public Curso getCurso(Integer id) {
		return CursoRepo.findById(id).orElse(null);
	}
	
	public Curso añadirCurso(Curso nuevo) throws ProfesorNotFound {
		System.out.println("Curso:" + nuevo);
		try {
			 restTemplate.getForObject(URL_PROFESORES + "/{id}", Object.class, nuevo.getProfesorId());

			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			throw new ProfesorNotFound("El profesor con el id: " + nuevo.getProfesorId() + " No Existe!");
		}
		return CursoRepo.save(nuevo);
	}
	
	public boolean eliminarCurso(Integer id) {
		Curso c = getCurso(id);
		if(c != null) {
			CursoRepo.delete(c);
			return true;
		}
		return false;
	}
	
	public Curso modificarCurso(Curso modificar) throws ProfesorNotFound {
		try {
			 restTemplate.getForObject(URL_PROFESORES + "/{id}", Object.class, modificar.getProfesorId());
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			throw new ProfesorNotFound("El profesor con el id: " + modificar.getProfesorId() + " No Existe!");
		}
		return CursoRepo.save(modificar);
	}
	
	public List<Curso> getCursosByProfesor(Integer idProfesor) throws ProfesorNotFound{
		try {
			 restTemplate.getForObject(URL_PROFESORES + "/{id}", Object.class, idProfesor);
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			throw new ProfesorNotFound("El profesor con el id: " + idProfesor + " No Existe!");
		}
		return CursoRepo.findByProfesorId(idProfesor);
	}
	
	
	/* ========== Tipo Curso ========== */
	
	public List<TipoCurso> getTipoCurso() {
        return TipoCursoRepo.findAll();
    }
	
	
	public TipoCurso getTipoCurso(Integer id) {
		return TipoCursoRepo.findById(id).orElse(null);
	}
	
	public TipoCurso añadirTipoCurso(TipoCurso nuevo) {
		Optional<TipoCurso>  exist = TipoCursoRepo.findByTipoNombre(nuevo.getTipoNombre());
		System.out.println("Exist:" + exist.isEmpty());
		if(exist.isEmpty()) {
			return TipoCursoRepo.save(nuevo);
		}
		return null;
	}
	
	public boolean eliminarTipoCurso(Integer id) {
		TipoCurso tc = getTipoCurso(id);
		if(tc != null) {
			TipoCursoRepo.delete(tc);
			return true;
		}
		return false;
	}
	
	public TipoCurso modificarTipoCurso(TipoCurso modificar) {
		return TipoCursoRepo.save(modificar);
	}
	
	public List<Curso> getCursosByTipo(Integer idTipo){
		TipoCurso tc = getTipoCurso(idTipo);
		if(tc != null) {
			return CursoRepo.findByTipo(tc);
		}
		return null;
	}
	
	public Integer getNumeroCursosDeCadaTipo(Integer idTipo) {
		TipoCurso tc = getTipoCurso(idTipo);
		if(tc != null) {
			return CursoRepo.findByTipo(tc).size();
		}
		return 0;
	}
}
