package blog.service.impl;

import blog.dto.CategoriaDTO;
import blog.entity.Categoria;
import blog.entity.Mensaje;
import blog.repository.CategoriaRepository;
import blog.service.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoriaImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<CategoriaDTO> findAll(Pageable pageable){
        Page<Categoria> categoria =  categoriaRepository.findAll(pageable);
        return categoria.map(category -> modelMapper.map(category, CategoriaDTO.class));
    }


    @Override
    public ResponseEntity<CategoriaDTO> findById(Integer id) {
        boolean existe = categoriaRepository.existsById(id);

        Categoria categoria = categoriaRepository.getReferenceById(id);

        if (!existe){
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }

        CategoriaDTO categoriaDTO = modelMapper.map(categoria,CategoriaDTO.class);
        return ResponseEntity.ok(categoriaDTO);
    }

    @Override
    public ResponseEntity<CategoriaDTO> save(Categoria categoria) {
        if (categoria.getId() != null){
            return ResponseEntity.notFound().build();
        }
        Categoria category = new Categoria();
        category.setId(categoria.getId());
        category.setCategoria(categoria.getCategoria());
        categoriaRepository.save(categoria);
        CategoriaDTO categoriaDTO = modelMapper.map(category,CategoriaDTO.class);
        return ResponseEntity.ok(categoriaDTO);
    }

    @Override
    public ResponseEntity<CategoriaDTO> update(Integer id, Categoria categoria) {
        Categoria category = categoriaRepository.findById(id).get();
        category.setCategoria(categoria.getCategoria());
        categoriaRepository.save(category);

        CategoriaDTO dto = modelMapper.map(category,CategoriaDTO.class);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Categoria> delete(Integer id) {
        boolean existe = categoriaRepository.existsById(id);
        int empty = categoriaRepository.categoriasRepetidas(id);

        if (!existe){
            return new ResponseEntity(new Mensaje("No existe ninguna categoria con ese id"), HttpStatus.BAD_REQUEST);
        }if (empty > 0){
            return new ResponseEntity(new Mensaje("No puedes borrar categorias relacionadas"), HttpStatus.BAD_REQUEST);
        }

        Categoria categoria = categoriaRepository.findById(id).get();
        categoriaRepository.delete(categoria);

        return new ResponseEntity(new Mensaje("Se ha eliminado exitosamente"),HttpStatus.OK);

    }
}
