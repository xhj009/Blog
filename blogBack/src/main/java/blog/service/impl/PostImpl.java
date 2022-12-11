package blog.service.impl;

import blog.config.ObtenerUsuario;
import blog.dto.PostDTO;
import blog.dto.PostSaveDTO;
import blog.entity.Categoria;
import blog.entity.Mensaje;
import blog.entity.Post;
import blog.repository.CategoriaRepository;
import blog.repository.PostRepository;
import blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service

public class PostImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObtenerUsuario obtenerUsuario;
    LocalDate fecha = LocalDate.now();

    @Override
    public Page<PostDTO> findAll(Pageable pageable){
        Page<Post> post = postRepository.findAll(pageable);
        return post.map(postDTO -> modelMapper.map(postDTO, PostDTO.class));
    }

    @Override
    public ResponseEntity<PostDTO> findOneById(Integer id) {
        Post respuesta = postRepository.getReferenceById(id);

        boolean existe = postRepository.existsById(id);

        if (!existe){
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.BAD_REQUEST);
        }
        PostDTO dto = modelMapper.map(respuesta,PostDTO.class);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<PostDTO> save(PostSaveDTO post) {
        if (post.getId() != null){
            ResponseEntity.badRequest().build();
        }

        Post pos = new Post();
        pos.setTitulo(post.getTitulo());
        pos.setDescripcion(post.getDescripcion());
        Categoria categoria = categoriaRepository.findByCategoria(post.getCategoria());
        pos.setCategoria(categoria);
        pos.setFechaCreacion(fecha);
        pos.setUsuario(obtenerUsuario.usuario());
        postRepository.save(pos);

        PostDTO dto = modelMapper.map(pos, PostDTO.class);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<PostDTO> update(Integer id, PostSaveDTO postDTO) {

        boolean existe = postRepository.existsByUsuarioAndId(obtenerUsuario.usuario(),id);

        if (!existe){
            return new ResponseEntity(new Mensaje("No existe"),HttpStatus.BAD_REQUEST);
        }

        Post post = postRepository.getReferenceById(id);
        post.setTitulo(postDTO.getTitulo());
        post.setDescripcion(postDTO.getDescripcion());
        Categoria categoria = categoriaRepository.findByCategoria(postDTO.getCategoria());
        post.setCategoria(categoria);
        post.setFechaCreacion(postDTO.getFechaCreacion());
        post.setFechaActualizacion(fecha);
        post.setUsuario(postDTO.getUsuario());
        postRepository.save(post);

        PostDTO dto = modelMapper.map(post, PostDTO.class);
        return ResponseEntity.ok(dto);

    }

    @Override
    public ResponseEntity<PostDTO> delete(Integer id) {
        boolean existe = postRepository.existsByUsuarioAndId(obtenerUsuario.usuario(),id);

        if (!existe){
            return new ResponseEntity(new Mensaje("No existe"),HttpStatus.BAD_REQUEST);
        }
        postRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Se elimino existosamente"),HttpStatus.OK);

    }

}
