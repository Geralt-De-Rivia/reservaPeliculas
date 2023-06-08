package com.devmovie.pelicula.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePeliculaRequest {
	
	private String titulo;
	private String descripcion;
	private String genero;
	private String director;
	private String anio;
	private String actores;

}
