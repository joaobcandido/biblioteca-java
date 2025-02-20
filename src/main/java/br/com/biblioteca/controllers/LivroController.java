package br.com.biblioteca.controllers;

import org.springframework.web.bind.annotation.*;

import br.com.biblioteca.dtos.LivroRequestDTO;
import br.com.biblioteca.dtos.LivroResponseDTO;
import br.com.biblioteca.services.LivroService;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> listarLivros() {
        return ResponseEntity.ok(livroService.listarLivros());
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> cradastrarLivro (@RequestBody LivroRequestDTO requestDTO){

        return ResponseEntity.ok(livroService.cadastraLivro(requestDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizarLivros(@PathVariable Long id, @RequestBody LivroRequestDTO livroDTO) {
        return ResponseEntity.ok(livroService.atualizarLivros(id, livroDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
