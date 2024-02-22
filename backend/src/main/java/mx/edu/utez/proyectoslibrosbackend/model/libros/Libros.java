package mx.edu.utez.proyectoslibrosbackend.model.libros;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="libros")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String nombreLibro;

    @Column(nullable = false)
    private String genero;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(nullable = false)
    private Date fechaPublicacion;
}
