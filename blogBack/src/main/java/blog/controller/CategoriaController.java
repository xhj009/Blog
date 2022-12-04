package blog.controller;

import blog.dto.CategoriaDTO;
import blog.entity.Categoria;
import blog.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/categorias")

public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("")
    public Page<CategoriaDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "true") boolean asc
    ) {
        Page<CategoriaDTO> categoriaDTO = categoriaService.findAll(PageRequest.of(page,size, Sort.by(order)));
        if (!asc) {
            categoriaDTO = categoriaService.findAll(PageRequest.of(page,size,Sort.by(order).descending()));
        }
        return categoriaDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id){
        return categoriaService.findById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriaDTO> save(@RequestBody @Valid Categoria categoria){
        return categoriaService.save(categoria);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @RequestBody @Valid Categoria categoria){
        return categoriaService.update(id,categoria);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Categoria> delete(@PathVariable Integer id){
        return categoriaService.delete(id);
    }
}
