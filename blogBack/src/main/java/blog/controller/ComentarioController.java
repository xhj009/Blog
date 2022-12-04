package blog.controller;

import blog.dto.ComentarioDTO;
import blog.dto.ComentarioGetDTO;
import blog.entity.Comentario;
import blog.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/posts")

public class ComentarioController {
  @Autowired
  private ComentarioService comentarioService;

    @GetMapping("/{postId}/comentarios")
    public List<ComentarioGetDTO> findAll(@PathVariable Integer postId){
        return comentarioService.findAll(postId);
    }

    @GetMapping("/{postId}/comentarios/{id}")
    public ResponseEntity<ComentarioGetDTO> findById(@PathVariable Integer id, @PathVariable Integer postId){
        return comentarioService.findById(id,postId);
    }

    @PostMapping("/{postId}/comentarios")
    public ResponseEntity<ComentarioDTO> save(@PathVariable Integer postId, @Valid @RequestBody Comentario comentario){
        return comentarioService.save(postId,comentario);
    }

    @PutMapping("/{postId}/comentarios/{id}")
    public ResponseEntity<ComentarioDTO> update(@PathVariable Integer id, @PathVariable Integer postId , @Valid @RequestBody Comentario comentario){
        return comentarioService.update(id,postId,comentario);
    }

    @DeleteMapping("/{postId}/comentarios/{id}")
    public ResponseEntity<Comentario> delete(@PathVariable Integer id,@PathVariable Integer postId){
        return comentarioService.delete(id,postId);
    }



}
