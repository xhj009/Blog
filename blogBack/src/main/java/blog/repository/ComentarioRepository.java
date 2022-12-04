package blog.repository;

import blog.dto.ComentarioDTO;
import blog.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Integer> {

    List<Comentario> findAllByUsuarioAndPostId(String usuario, Integer postId);

    ComentarioDTO findByUsuarioAndPostId(String usuario, Integer postId);
    List<Comentario> findAllByPostId(Integer postId);

    ComentarioDTO findByUsuarioAndIdAndPostId(String usario, Integer id,Integer postId);

    Boolean existsByUsuarioAndId(String username,Integer id);

    Boolean existsByPostId(Integer postId);

}
