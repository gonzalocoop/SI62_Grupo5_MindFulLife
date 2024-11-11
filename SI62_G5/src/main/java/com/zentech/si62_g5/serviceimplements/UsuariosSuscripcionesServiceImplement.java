package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.dtos.RecaudacionSuscripcionDTO;
import com.zentech.si62_g5.entities.Usuarios;
import com.zentech.si62_g5.entities.UsuariosSuscripciones;
import com.zentech.si62_g5.repositories.IUsuariosSuscripcionesRepository;
import com.zentech.si62_g5.serviceinterfaces.IUsuariosSuscripcionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuariosSuscripcionesServiceImplement implements IUsuariosSuscripcionesService {

    @Autowired
    private IUsuariosSuscripcionesRepository bR;

    @Override
    public void insert(UsuariosSuscripciones usus) {
        bR.save(usus);
    }

    @Override
    public List<UsuariosSuscripciones> list() {
        return bR.findAll();
    }

    @Override
    public void delete(int id) {
        bR.deleteById(id);
    }

    @Override
    public UsuariosSuscripciones listId(int id) {
        return bR.findById(id).orElse(new UsuariosSuscripciones());
    }

    @Override
    public void update(UsuariosSuscripciones usus) {
        bR.save(usus);
    }

    @Override
    public List<String[]> usuariosSuscripcion(String nombresuscripcion) {
        return bR.usuariosSuscripcion(nombresuscripcion);
    }

    @Override
    public List<RecaudacionSuscripcionDTO> obtenerRecaudacionPorSuscripcion(String nombreSuscripcion, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Object[]> resultados = bR.obtenerRecaudacionPorSuscripcion(nombreSuscripcion, fechaInicio, fechaFin);
        List<RecaudacionSuscripcionDTO> listaDTO = new ArrayList<>();

        for (Object[] resultado : resultados) {
            RecaudacionSuscripcionDTO dto = new RecaudacionSuscripcionDTO();
            dto.setNombreSuscripcion((String) resultado[0]);
            dto.setRecaudacionTotal((BigDecimal) resultado[1]);
            listaDTO.add(dto);
        }

        return listaDTO;
    }

    @Override
    public List<UsuariosSuscripciones> listaUsuarioSuscripcionUsuario(String username) {
        return bR.listaUsuarioSuscripcionUsuario(username);
    }


}
