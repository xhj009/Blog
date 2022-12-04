package blog.service;

import blog.dto.PostDTO;
import blog.dto.PostSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface PostService {
    //List<PostDTO> findAll();

    Page<PostDTO> findAll(Pageable pageable);

    ResponseEntity<PostDTO> findOneById(@PathVariable Integer id);

    ResponseEntity<PostDTO> save(@RequestBody PostSaveDTO post);

    ResponseEntity<PostDTO> update(@PathVariable Integer id, @RequestBody PostSaveDTO post);

    ResponseEntity<PostDTO> delete(@PathVariable Integer id);



}
