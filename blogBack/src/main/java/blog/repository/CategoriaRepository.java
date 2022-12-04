package blog.repository;

import blog.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query(value = "select count(categoria) from categorias  inner join posts on posts.categoria_id = categorias.id where categorias.id = :id",nativeQuery = true)
    int categoriasRepetidas(Integer id);
}
