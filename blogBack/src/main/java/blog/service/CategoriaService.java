package blog.service;

import blog.dto.CategoriaDTO;
import blog.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CategoriaService {

    //public List<CategoriaDTO> findAll();
    Page<CategoriaDTO> findAll(Pageable pageable);

    ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id);

    ResponseEntity<CategoriaDTO> save(@RequestBody Categoria categoria);

    ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @RequestBody Categoria categoria);

    ResponseEntity<Categoria> delete(@PathVariable Integer id);

}
