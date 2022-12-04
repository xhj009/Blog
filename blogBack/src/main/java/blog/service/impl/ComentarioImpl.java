package blog.service.impl;

import blog.config.ObtenerUsuario;
import blog.dto.ComentarioDTO;
import blog.dto.ComentarioGetDTO;
import blog.entity.Comentario;
import blog.entity.Mensaje;
import blog.entity.Post;
import blog.repository.ComentarioRepository;
import blog.service.ComentarioService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class ComentarioImpl implements ComentarioService {
   @Autowired
   ComentarioRepository comentarioRepository;
   @Autowired
   ModelMapper modelMapper;
   @Autowired
    private ObtenerUsuario obtenerUsuario;
    LocalDate fecha = LocalDate.now();


    @Override
    public List<ComentarioGetDTO> findAll(Integer postId) {
      List<Comentario> comentario =  comentarioRepository.findAllByPostId(postId);
      List<ComentarioGetDTO> respuesta = modelMapper.map(comentario, new TypeToken<List<ComentarioGetDTO>>() {}.getType());
      return respuesta;
    }

    @Override
    public ResponseEntity<ComentarioGetDTO> findById(Integer id,Integer postId) {
        Comentario respuesta = comentarioRepository.getReferenceById(id);

       boolean existe = comentarioRepository.existsByPostId(postId);

       if (!existe){
           return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
       }

       ComentarioGetDTO dto = modelMapper.map(respuesta, ComentarioGetDTO.class);

       return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ComentarioDTO> save(Integer postId ,Comentario comentario) {

        if (comentario.getId() !=null ){
            return ResponseEntity.badRequest().build();
        }

         Post post = new Post();
         post.setId(postId);
        
        ComentarioDTO coment = new ComentarioDTO();
        coment.setComentario(comentario.getComentario());
        coment.setUsuario(obtenerUsuario.usuario());
        coment.setFechaCreacion(fecha);
        coment.setPost(post);

        Comentario comenta = modelMapper.map(coment, Comentario.class);
        comentarioRepository.save(comenta);
        return  ResponseEntity.ok(coment);

    }


    public ResponseEntity<ComentarioDTO> update(Integer id, Integer postId, Comentario comentario) {

        Boolean existe = comentarioRepository.existsByUsuarioAndId(obtenerUsuario.usuario(), id);

        if (!existe){
            return new ResponseEntity(new Mensaje("No existe"),HttpStatus.BAD_REQUEST);
        }

        Post post = new Post();
        post.setId(postId);

        Comentario coment = comentarioRepository.getReferenceById(id);
        coment.setComentario(comentario.getComentario());
        coment.setPost(post);
        coment.setUsuario(comentario.getUsuario());
        coment.setFechaCreacion(coment.getFechaCreacion());
        coment.setFechaActualizacion(fecha);
        comentarioRepository.save(coment);

        ComentarioDTO comentarioDTO = modelMapper.map(coment,ComentarioDTO.class);
        return ResponseEntity.ok(comentarioDTO);
    }


    public ResponseEntity<Comentario> delete(Integer id, Integer postId) {
        boolean existe = comentarioRepository.existsByUsuarioAndId(obtenerUsuario.usuario(), id);

        if (!existe){
            return new ResponseEntity(new Mensaje("No existe"),HttpStatus.BAD_REQUEST);
        }

        Comentario comentario = comentarioRepository.getReferenceById(id);
        comentarioRepository.delete(comentario);
        return new ResponseEntity(new Mensaje("Se elimino exitosamente"),HttpStatus.OK);


    }

}
