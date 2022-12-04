package blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")

public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "El campo titulo es obligatorio")
    private String titulo;
    @NotEmpty(message = "El campo descripcion es obligatorio")
    @Lob()
    @Column(name = "descripcion")
    @Type(type = "org.hibernate.type.TextType")
    private String descripcion;
    private String usuario;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;
    @OneToMany(targetEntity = Comentario.class)
    private List<Comentario> comentarios;
    @ManyToOne(targetEntity = Categoria.class)
    private Categoria categoria;



}
