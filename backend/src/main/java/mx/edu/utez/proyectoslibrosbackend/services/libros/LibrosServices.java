package mx.edu.utez.proyectoslibrosbackend.services.libros;

import mx.edu.utez.proyectoslibrosbackend.model.libros.Libros;
import mx.edu.utez.proyectoslibrosbackend.model.libros.LibrosRepository;
import mx.edu.utez.proyectoslibrosbackend.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class LibrosServices {
    @Autowired
    LibrosRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Libros>> getAll() {
        try {
            return new CustomResponse<>(this.repository.findAll(), false, 200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null,true, 500, "Error de libros");
        }
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Libros>> getAllAutor(String autor) {
        try {
            return new CustomResponse<>(this.repository.findAutor(autor), false, 200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null,true, 500, "Error de libros");
        }
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Libros>> getAllGenero(String genero) {
        try {
            return new CustomResponse<>(this.repository.findGemero(genero), false, 200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null,true, 500, "Error de libros");
        }
    }

    public CustomResponse<List<Libros>> buscarPorRangoDeFechas(Date fechaInicio, Date fechaFin) {
        try {
            return new CustomResponse<>(repository.buscarPorRangoDeFechas(fechaInicio, fechaFin), false, 200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al buscar libros por rango de fechas");
        }
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Libros>> getAllNombre(String nombre) {
        try {
            return new CustomResponse<>(this.repository.findNombre(nombre), false, 200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null,true, 500, "Error de libros");
        }
    }

    @Transactional(readOnly = true)
    public CustomResponse<Libros> getOne(Long id) {
        try {
            return new CustomResponse<>(this.repository.findById(id).get(), false, 200,"OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error de servidor");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Libros> insert(Libros libro) {
        System.out.println(libro);
        try {
            return new CustomResponse<>(this.repository.saveAndFlush(libro),false, 200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null,true, 500, "Error de servidor");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Libros> update(Libros libros) {
        try {
            if (this.repository.existsById(libros.getId()))
                return new CustomResponse<>(this.repository.saveAndFlush(libros),false, 200, "OK");
            return new CustomResponse<>(null, true, 400,"Envio de datos erroneo");
        } catch (Exception e) {
            return new CustomResponse<>(null,true, 500, "Error de servidor");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Libros> delete(Long id) {
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true, 400, "Envio de datos erroneo");
            this.repository.deleteById(id);
            return new CustomResponse<>(null, false, 200,"OK");
        } catch (Exception e) {
            return new CustomResponse<>(null,true, 500, "Error de servidor");
        }
    }
}
