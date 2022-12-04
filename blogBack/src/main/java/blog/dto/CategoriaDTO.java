package blog.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoriaDTO {
    private Integer id;
    @NotNull(message = "El campo categoria es obligatorio")
    private String categoria;

}
