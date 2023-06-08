package com.devmovie.pelicula.data;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmovie.pelicula.model.pojo.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

	Pelicula findByTitulo(String titulo);

	List<Pelicula> findByGenero(String genero);

	List<Pelicula> findByAnio(String anio);


}
