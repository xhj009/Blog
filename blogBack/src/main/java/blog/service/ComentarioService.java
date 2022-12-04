package blog.service;

import blog.dto.ComentarioDTO;
import blog.dto.ComentarioGetDTO;
import blog.entity.Comentario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ComentarioService {
    List<ComentarioGetDTO> findAll(@PathVariable Integer postId);

    ResponseEntity<ComentarioGetDTO> findById(@PathVariable Integer id, Integer postId);
    ResponseEntity<ComentarioDTO> save(@PathVariable Integer postId, @RequestBody Comentario comentario);

    ResponseEntity<ComentarioDTO> update(@PathVariable Integer id,@PathVariable Integer postId , @RequestBody Comentario comentario);

    ResponseEntity<Comentario> delete(@PathVariable Integer id, @PathVariable Integer postId);

}
