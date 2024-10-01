package br.com.bibliotecalivros.controller;

import br.com.bibliotecalivros.dto.LivroDTO;
import br.com.bibliotecalivros.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
@CrossOrigin(allowedHeaders = "*")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroDTO> insereLivro(@Valid @RequestBody LivroDTO livroDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.inserirLivro(livroDTO));
    }

    @GetMapping
    public ResponseEntity<Page<LivroDTO>> listarLivros(@RequestParam(required = false) String titulo,
                                                       @RequestParam(required = false) String autor,
                                                       @RequestParam(required = false) Integer anoPublicacao, Pageable pageable) {
        return ResponseEntity.ok().body(livroService.buscarLivros(titulo, autor, anoPublicacao, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizarLivro(@Valid @PathVariable String id,
                                                   @RequestBody LivroDTO livroDTO) {
        return ResponseEntity.ok().body(livroService.atualizarLivro(id, livroDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLivro(@PathVariable String id) {
        livroService.deletarLivro(id);
        return ResponseEntity.ok().build();
    }
}