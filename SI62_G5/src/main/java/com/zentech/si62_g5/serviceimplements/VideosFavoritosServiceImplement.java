package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.entities.UsuariosSuscripciones;
import com.zentech.si62_g5.entities.VideosFavoritos;
import com.zentech.si62_g5.repositories.IVideosFavoritosRepository;
import com.zentech.si62_g5.serviceinterfaces.IVideosFavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideosFavoritosServiceImplement implements IVideosFavoritosService {

    @Autowired
    private IVideosFavoritosRepository fR;

    @Override
    public void insert(VideosFavoritos vfav) {
        fR.save(vfav);
    }

    @Override
    public List<VideosFavoritos> list() {
        return fR.findAll();
    }

    @Override
    public void delete(int id) {
        fR.deleteById(id);
    }

    @Override
    public VideosFavoritos listId(int id) {
        return fR.findById(id).orElse(new VideosFavoritos());
    }

    @Override
    public void update(VideosFavoritos vfav) {
        fR.save(vfav);
    }

    @Override
    public List<VideosFavoritos> findAllVideoFavByUsuario(String username) {
        return fR.listaUsuarioCursoFav(username);
    }
}
