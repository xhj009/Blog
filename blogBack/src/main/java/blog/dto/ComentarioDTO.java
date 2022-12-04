package blog.dto;

import blog.entity.Post;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ComentarioDTO implements Serializable {
    @NotEmpty(message = "El campo comentario es obligatorio")
    private String comentario;
    private String usuario;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;
    private Post post;


}
