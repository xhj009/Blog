package blog.controller;

import blog.dto.PostDTO;
import blog.dto.PostSaveDTO;
import blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/posts")

public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("")
    public Page<PostDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "true") boolean asc
    ) {
        Page<PostDTO> postDTO = postService.findAll(PageRequest.of(page,size, Sort.by(order)));
        if (!asc) {
            postDTO = postService.findAll(PageRequest.of(page,size,Sort.by(order).descending()));
        }
        return postDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findOneById(@PathVariable Integer id){
        return postService.findOneById(id);
    }

    @PostMapping("")
    public ResponseEntity<PostDTO> save(@Valid @RequestBody PostSaveDTO post){
        return postService.save(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable Integer id, @Valid @RequestBody PostSaveDTO post){
        return postService.update(id,post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostDTO> delete(@PathVariable Integer id){
        return postService.delete(id);
    }
}
