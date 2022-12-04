package blog.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PostSaveDTO {
    private Integer id;
    @NotEmpty(message = "El campo titulo es obligatorio")
    private String titulo;
    @NotEmpty(message = "El campo descripcion es obligatorio")
    private String descripcion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;
    private String usuario;
    @NotNull(message = "El campo categoria es obligatorio")
    private Integer categoria;
}
