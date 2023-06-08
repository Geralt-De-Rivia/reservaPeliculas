package com.devmovie.reserva.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmovie.reserva.model.pojo.Reserva;


public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	List<Reserva> findByUsuario(String usuario);

	List<Reserva> findByUsuarioAndPeliculaId(String usuario, String idPelicula);

}
