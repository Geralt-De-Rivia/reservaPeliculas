package com.devmovie.reserva.service;

import java.util.List;

import com.devmovie.reserva.model.pojo.Pelicula;
import com.devmovie.reserva.model.pojo.Reserva;
import com.devmovie.reserva.request.CreateReservaRequest;
//import com.devmovie.reserva.request.CreateReservaRequest;
import com.devmovie.reserva.request.ReservaRequest;



public interface ReservaService {
	Reserva createReserva(CreateReservaRequest request);

	List<Reserva> getReserva();

	List<Reserva> buscarPorUsuario(String usuario);

	List<Reserva> buscarPorUsuarioPelicula(String idUsuario, String idPelicula);

	String createReservaPelicula(String idUsuario, ReservaRequest request);


}
