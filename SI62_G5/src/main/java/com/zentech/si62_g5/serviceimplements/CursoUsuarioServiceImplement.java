package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.entities.Cronogramas;
import com.zentech.si62_g5.entities.CursosUsuarios;
import com.zentech.si62_g5.repositories.ICursoUsuarioRepository;
import com.zentech.si62_g5.serviceinterfaces.ICursoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoUsuarioServiceImplement implements ICursoUsuarioService {

    @Autowired
    private ICursoUsuarioRepository cuR;

    @Override
    public void insert(CursosUsuarios cus) {
        cuR.save(cus);

    }

    @Override
    public List<CursosUsuarios> list() {
        return cuR.findAll();
    }

    @Override
    public void delete(int id) {
        cuR.deleteById(id);
    }

    @Override
    public void update(CursosUsuarios cus) {
        cuR.save(cus);
    }

    @Override
    public List<String[]> ObtenerUrl(String nombreUsuario, String nombreCurso) {
        return cuR.ObtenerUrl(nombreUsuario,nombreCurso);
    }

    @Override
    public List<String[]> cantidadDeCursosCompletadosYNoCompletados() {
        return cuR.cantidadDeCursosCompletadosYNoCompletados();
    }

    @Override
    public CursosUsuarios listId(int id) {
        return cuR.findById(id).orElse(new CursosUsuarios());
    }

    @Override
    public void registrarUsuarioEnCurso(int idCurso, int idUsuario) {
        if (cuR.existeRegistro(idUsuario, idCurso)) {
            // Lanza una excepción si ya existe el registro
            throw new IllegalArgumentException("Usted ya está registrado en este curso");
        } else {
            cuR.registrarCurso(idCurso, idUsuario);

        }
    }

    @Override
    public CursosUsuarios findByCursoAndUsuario(int idCurso, int idUsuario) {
        return cuR.findByCursoAndUsuario(idCurso,idUsuario);
    }

    @Override
    public void actualizarProgresoYEstado(int idCursoUsuario) {
        cuR.actualizarProgresoYEstado(idCursoUsuario);
    }

    @Override
    public List<CursosUsuarios> findByUsername(String username) {
        return cuR.findByUsername(username);
    }

    @Override
    public List<String[]> CantidadCursoCompletadosNoCompletadosGeneral() {
        return cuR.CantidadCursoCompletadosNoCompletadosGeneral();
    }
}
