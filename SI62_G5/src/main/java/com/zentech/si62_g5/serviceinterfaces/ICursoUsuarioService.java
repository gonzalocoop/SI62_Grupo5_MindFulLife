package com.zentech.si62_g5.serviceinterfaces;


import com.zentech.si62_g5.entities.Cronogramas;
import com.zentech.si62_g5.entities.CursosUsuarios;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICursoUsuarioService {
    public void insert(CursosUsuarios cus);
    public List<CursosUsuarios> list();
    public void delete(int id);
    public void update(CursosUsuarios cus);
    public List<String[]>ObtenerUrl(String nombreUsuario,String nombreCurso);
    public List<String[]>cantidadDeCursosCompletadosYNoCompletados();
    public CursosUsuarios listId(int id);

    public void registrarUsuarioEnCurso(int idCurso, int idUsuario);

}
