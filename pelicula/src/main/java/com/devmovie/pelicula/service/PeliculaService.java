package com.devmovie.pelicula.service;

import java.util.List;
import java.util.Map;

import com.devmovie.pelicula.model.pojo.Pelicula;
import com.devmovie.pelicula.model.request.CreatePeliculaRequest;

public interface PeliculaService {
	
	Pelicula createPelicula(CreatePeliculaRequest request);
	
	List<Pelicula> getPelicula();
		
	Pelicula getPelicula(String peliculaId);
		
	Boolean removePelicula(String peliculaId);
	
	Pelicula updatePelicula(CreatePeliculaRequest request,String peliculaId);

	Pelicula buscarPorTitulo(String titulo);

	List<Pelicula> buscarPorGenero(String genero);
	
	List<Pelicula> buscarPorAnio(String anio);

	Boolean updatePeliculaParcial(Long peliculaId, Map<String, Object> request);

}
