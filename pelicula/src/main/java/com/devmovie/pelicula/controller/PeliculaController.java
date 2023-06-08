package com.devmovie.pelicula.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.devmovie.pelicula.model.pojo.Pelicula;
import com.devmovie.pelicula.model.request.CreatePeliculaRequest;
import com.devmovie.pelicula.service.PeliculaService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/peliculas")
public class PeliculaController {
	
	private final PeliculaService service;

	@GetMapping
	public ResponseEntity<List<Pelicula>> getPelicula(@RequestHeader Map<String, String> headers) {

		log.info("headers: {}", headers);
		List<Pelicula> peliculas = service.getPelicula();

		if (peliculas != null) {
			return ResponseEntity.ok(peliculas);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@PostMapping
	public ResponseEntity<Pelicula> getPelicula(@RequestBody CreatePeliculaRequest request) {

		Pelicula createdPelicula = service.createPelicula(request);

		if (createdPelicula != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdPelicula);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("{peliculaId}")
	public ResponseEntity<Pelicula> getPelicula(@PathVariable String peliculaId) {

		log.info("Request received for peliculas {}", peliculaId);
		Pelicula pelicula = service.getPelicula(peliculaId);

		if (pelicula != null) {
			return ResponseEntity.ok(pelicula);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("{peliculaId}")
	public ResponseEntity<Void> deletePelicula(@PathVariable String peliculaId) {

		Boolean removed = service.removePelicula(peliculaId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@PutMapping("{peliculaId}")
	public ResponseEntity<Pelicula> updatePelicula(@PathVariable String peliculaId, @RequestBody CreatePeliculaRequest request) {
		
		
		Pelicula createdPelicula = service.updatePelicula(request,peliculaId);

		if (createdPelicula != null) {
			return ResponseEntity.status(HttpStatus.OK).body(createdPelicula);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PatchMapping("{peliculaId}")
	public ResponseEntity<String> actualizarParcialMovie(@PathVariable Long peliculaId, @RequestBody Map<String, Object> request) {

		Boolean updatePelicula = service.updatePeliculaParcial(peliculaId, request);

		if (Boolean.TRUE.equals(updatePelicula)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("titulo/{titulo}")
	public ResponseEntity<Pelicula> buscarPorTitulo(@PathVariable String titulo) {

		log.info("Request received for pelicula {}", titulo);
		Pelicula pelicula = service.buscarPorTitulo(titulo);

		if (pelicula != null) {
			return ResponseEntity.ok(pelicula);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("genero/{genero}")
	public ResponseEntity<List<Pelicula>> buscarPorGenero(@PathVariable String genero) {

		log.info("Request received for pelicula {}", genero);
		List<Pelicula> pelicula = service.buscarPorGenero(genero);

		if (pelicula != null) {
			return ResponseEntity.ok(pelicula);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("anio/{anio}")
	public ResponseEntity<List<Pelicula>> buscarPorAnio(@PathVariable String anio) {

		log.info("Request received for pelicula {}", anio);
		List<Pelicula> pelicula = service.buscarPorAnio(anio);

		if (pelicula != null) {
			return ResponseEntity.ok(pelicula);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
}
