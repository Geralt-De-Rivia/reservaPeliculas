package com.devmovie.reserva.service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.devmovie.reserva.data.ReservaRepository;
import com.devmovie.reserva.facade.PeliculaFacade;
import com.devmovie.reserva.model.pojo.Pelicula;
import com.devmovie.reserva.model.pojo.Reserva;
import com.devmovie.reserva.request.CreateReservaRequest;
import com.devmovie.reserva.request.ReservaRequest;


@Service
public class ReservaServiceImpl implements ReservaService{

	@Autowired
	private ReservaRepository repository;
	
	@Autowired
	private PeliculaFacade peliculaFacade;


	@Override
	public List<Reserva> getReserva() {
		List<Reserva> reservas = repository.findAll();
		return reservas.isEmpty() ? null : reservas;
	}

	@Override
	public List<Reserva> buscarPorUsuario(String usuario) {
		List<Reserva> reserva = repository.findByUsuario(usuario);
		return reserva.isEmpty() ? null : reserva;
	}

	@Override
	public List<Reserva> buscarPorUsuarioPelicula(String idUsuario, String idPelicula) {
		return repository.findByUsuarioAndPeliculaId(idUsuario, idPelicula);
	}
	
	@Override
	public Reserva createReserva(CreateReservaRequest request) {
		if (request != null && StringUtils.hasLength(request.getUsuario().trim())
				&& StringUtils.hasLength(request.getPelicula_id().trim())) 
		{
			List<Reserva> usuarioPelicula = buscarPorUsuarioPelicula(request.getUsuario(), request.getPelicula_id());
			if(usuarioPelicula.isEmpty()) {
				Reserva reserva = Reserva.builder().usuario(request.getUsuario()).peliculaId(request.getPelicula_id()).build();
				return repository.save(reserva);
			}
		}
		return null;
	}
	
	@Override
	public String createReservaPelicula(String idUsuario, ReservaRequest request) {
		List<Pelicula> peliculas = request.getPeliculas().stream().map(peliculaFacade::getPelicula).filter(Objects::nonNull).collect(Collectors.toList());
		if(peliculas.size() == request.getPeliculas().size()) {
			for(int i=0; i<peliculas.size(); i++) {
				Reserva reserva = new Reserva();
				List<Reserva> usuarioPelicula = buscarPorUsuarioPelicula(idUsuario, peliculas.get(i).getId().toString());
				if(usuarioPelicula.isEmpty()) {
					reserva.setUsuario(idUsuario);
					reserva.setPeliculaId(peliculas.get(i).getId().toString());
					repository.save(reserva);
				}
			}
			return peliculas.size() == request.getPeliculas().size() ? "OK" : "KO";
		}
		return null;
	}

}
