package com.devmovie.reserva.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devmovie.reserva.model.pojo.Pelicula;
import com.devmovie.reserva.model.pojo.Reserva;
import com.devmovie.reserva.request.CreateReservaRequest;
import com.devmovie.reserva.request.ReservaRequest;
import com.devmovie.reserva.service.ReservaService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reservas")
public class ReservaController {
	
	private final ReservaService service;

	@GetMapping
	public ResponseEntity<List<Reserva>> getReserva(@RequestHeader Map<String, String> headers) {

		log.info("headers: {}", headers);
		List<Reserva> reservas = service.getReserva();

		if (reservas != null) {
			return ResponseEntity.ok(reservas);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}
		
	@PostMapping
	public ResponseEntity<?> createReserva(@RequestBody CreateReservaRequest request) {

	  Reserva createdReserva = service.createReserva(request);
	  if (createdReserva == null) {
		  return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", "Ya existe una reserva con ese usuario y pelicula"));
	  }
	  if (createdReserva != null) {
		  return ResponseEntity.status(HttpStatus.CREATED).body(createdReserva);
	  }
	  return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/peliculas/{idUsuario}")
	public ResponseEntity<String> createReservaPelicula(@PathVariable String idUsuario, @RequestBody ReservaRequest request) {

		String result = service.createReservaPelicula(idUsuario, request);
		  
		  if(request != null && "OK".equals(result)) {
		    return ResponseEntity.ok(result);
		  } else {
		    return ResponseEntity.badRequest().build();
		  }
		
	}
	
	@GetMapping("usuario/{idUsuario}")
	public ResponseEntity<List<Reserva>> buscarPorUsuario(@PathVariable String idUsuario) {

		log.info("Request received for pelicula {}", idUsuario);
		List<Reserva> reservas = service.buscarPorUsuario(idUsuario);

		if (reservas != null) {
			return ResponseEntity.ok(reservas);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
