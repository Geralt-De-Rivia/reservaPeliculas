package com.devmovie.reserva.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservaRequest {

	private String usuario;
	private String pelicula_id;
	
}
