package com.devmovie.pelicula.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.devmovie.pelicula.data.PeliculaRepository;
import com.devmovie.pelicula.model.pojo.Pelicula;
import com.devmovie.pelicula.model.request.CreatePeliculaRequest;

@Service
public class PeliculaServiceImpl implements PeliculaService {

	@Autowired
	private PeliculaRepository repository;
	
	@Override
	public Pelicula createPelicula(CreatePeliculaRequest request) {
		if (request != null && StringUtils.hasLength(request.getTitulo().trim())
				&& StringUtils.hasLength(request.getDescripcion().trim())
				&& StringUtils.hasLength(request.getGenero().trim())
				&& StringUtils.hasLength(request.getDirector().trim())
				&& StringUtils.hasLength(request.getAnio().trim())
				&& StringUtils.hasLength(request.getActores().trim())
				) {

			Pelicula pelicula = Pelicula.builder().titulo(request.getTitulo()).descripcion(request.getDescripcion()).genero(request.getGenero())
					.director(request.getDirector()).anio(request.getAnio()).actores(request.getActores()).build();

			return repository.save(pelicula);
		} else {
			return null;
		}
	}
	
	@Override
	public List<Pelicula> getPelicula() {
		List<Pelicula> pelicula = repository.findAll();
		return pelicula.isEmpty() ? null : pelicula;
	}

	@Override
	public Pelicula getPelicula(String peliculaId) {
		return repository.findById(Long.valueOf(peliculaId)).orElse(null);
	}

	@Override
	public Boolean removePelicula(String peliculaId) {
		Pelicula pelicula = repository.findById(Long.valueOf(peliculaId)).orElse(null);

		if (pelicula != null) {
			repository.delete(pelicula);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Pelicula updatePelicula(CreatePeliculaRequest request, String peliculaId) {
		Optional<Pelicula> requestPelicula = repository.findById(Long.valueOf(peliculaId));
		
		if(requestPelicula.isPresent()) {
			Pelicula pelicula = requestPelicula.get();
			pelicula.setTitulo(request.getTitulo());
			pelicula.setDescripcion(request.getDescripcion());
			pelicula.setGenero(request.getGenero());
			pelicula.setDirector(request.getDirector());
			pelicula.setAnio(request.getAnio());
			pelicula.setActores(request.getActores());
			return repository.save(pelicula);
		}else {
			return null;
		}
	}

	@Override
	public Pelicula buscarPorTitulo(String titulo) {
		return (Pelicula) repository.findByTitulo(titulo);
	}

	@Override
	public List<Pelicula> buscarPorGenero(String genero) {
		List<Pelicula> pelicula = repository.findByGenero(genero);
		return pelicula.isEmpty() ? null : pelicula;
	}

	@Override
	public List<Pelicula> buscarPorAnio(String anio) {
		List<Pelicula> pelicula = repository.findByAnio(anio);
		return pelicula.isEmpty() ? null : pelicula;
	}

	@Override
	public Boolean updatePeliculaParcial(Long peliculaId, Map<String, Object> request) {
		if (request != null) {
            Optional<Pelicula> requestPelicula = repository.findById(peliculaId);            

            if (requestPelicula.isPresent()) {
            	Pelicula pelicula = requestPelicula.get();              

                request.forEach((campo, valor) -> {

                    switch (campo) {
                        case "titulo":
                        	pelicula.setTitulo((String) valor);
                            break;
                        case "descripcion":
                        	pelicula.setDescripcion((String) valor);
                            break;
                        case "genero":
                        	pelicula.setGenero((String) valor);
                            break;
                        case "director":
                        	pelicula.setDirector((String) valor);
                            break;
                        case "anio":
                        	pelicula.setAnio((String) valor);
                            break;
                        case "actores":
                        	pelicula.setActores((String) valor);
                            break;
                        default:
                        	break;   
                    }

                });                

                repository.save(pelicula);
				return Boolean.TRUE;
            } else {
				return Boolean.FALSE;
			}
        } else {
            return Boolean.FALSE;
        }
	}

}
