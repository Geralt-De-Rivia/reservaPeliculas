package com.devmovie.reserva.facade;

import com.devmovie.reserva.model.pojo.Pelicula;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PeliculaFacade {
	
	@Value("${getPelicula.url}")
	private String getPeliculaUrl;
	
	private final RestTemplate restTemplate;
	
	public Pelicula getPelicula(String id) {

	    try {
	      return restTemplate.getForObject(String.format(getPeliculaUrl, id), Pelicula.class);
	    } catch (HttpClientErrorException e) {
	      log.error("Client Error: {}, Pelicula with ID {}", e.getStatusCode(), id);
	      return null;
	    }
	  }

}
