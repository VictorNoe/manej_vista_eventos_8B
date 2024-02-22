package mx.edu.utez.proyectoslibrosbackend.controller.libros;

import mx.edu.utez.proyectoslibrosbackend.model.libros.Libros;
import mx.edu.utez.proyectoslibrosbackend.services.libros.LibrosServices;
import mx.edu.utez.proyectoslibrosbackend.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = {"*"})
public class LibrosController {
    @Autowired
    LibrosServices services;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Libros>>> getAll() {
        return new ResponseEntity<>(this.services.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Libros>> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.services.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/autor")
    public ResponseEntity<CustomResponse<?>> getAutor(@RequestBody Map<String, String> requets) {
        String autor = requets.get("data");
        return new ResponseEntity<>(this.services.getAllAutor(autor), HttpStatus.CREATED);
    }

    @PostMapping("/genero")
    public ResponseEntity<CustomResponse<?>> getGenero(@RequestBody Map<String, String> requets) {
        String genero = requets.get("data");
        return new ResponseEntity<>(this.services.getAllGenero(genero), HttpStatus.CREATED);
    }

    @PostMapping("/nombre")
    public ResponseEntity<CustomResponse<?>> getNombre(@RequestBody Map<String, String> requets) {
        String nombre = requets.get("data");
        return new ResponseEntity<>(this.services.getAllNombre(nombre), HttpStatus.CREATED);
    }

    @PostMapping("/fechas")
    public ResponseEntity<CustomResponse<List<Libros>>> buscarPorRangoDeFechas(@RequestBody Map<String, Object> request) {
        try {
            Date fechaInicio = (Date) request.get("fechaInicio");
            Date fechaFin = (Date) request.get("fechaFin");

            CustomResponse<List<Libros>> response = services.buscarPorRangoDeFechas(fechaInicio, fechaFin);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse<>(null, true, 500, "Error al buscar libros por rango de fechas"));
        }
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Libros>> insert(@Validated @RequestBody Libros libros) {
        return  new ResponseEntity<>(this.services.insert(libros), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Libros>> update(@Validated  @RequestBody Libros libros)  {
        return new ResponseEntity<>(this.services.update(libros),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Libros>> delete(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.services.delete(id),HttpStatus.CREATED);
    }
}
