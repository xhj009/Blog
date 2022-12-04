package blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostDTO implements Serializable {
    private Integer id;
    @NotEmpty(message = "El titulo es obligatorio")
    private String titulo;
    @NotEmpty(message = "El campo descripcion es obligatorio")
    private String descripcion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;
    private String usuario;
    @NotNull(message = "El campo categoria es obligatorio")
    private String categoria;


}
