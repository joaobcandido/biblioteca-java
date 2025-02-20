package br.com.biblioteca.services;

import br.com.biblioteca.dtos.LivroRequestDTO;
import br.com.biblioteca.dtos.LivroResponseDTO;
import br.com.biblioteca.models.LivroModel;
import br.com.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroResponseDTO cadastraLivro(LivroRequestDTO livroDTO) {
        LivroModel livro = new LivroModel(
                null, livroDTO.getTitulo(),
                livroDTO.getAutor(),
                livroDTO.getAnoPublicacao());
        LivroModel livroSalvo = livroRepository.save(livro);
        return new LivroResponseDTO(
                livroSalvo.getId(),
                livroSalvo.getTitulo(),
                livroSalvo.getAutor(),
                livroSalvo.getAnoPublicacao());
    }

    public LivroResponseDTO atualizarLivros(Long id, LivroRequestDTO livroDTO) {
        LivroModel livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setAnoPublicacao(livroDTO.getAnoPublicacao());

        LivroModel livroAtualizado = livroRepository.save(livro);
        return new LivroResponseDTO(
                livroAtualizado.getId(),
                livroAtualizado.getTitulo(),
                livroAtualizado.getAutor(),
                livroAtualizado.getAnoPublicacao());
    }
    public List<LivroResponseDTO> listarLivros() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> new LivroResponseDTO(
                        livro.getId(),
                        livro.getTitulo(),
                        livro.getAutor(),
                        livro.getAnoPublicacao()))
                .collect(Collectors.toList());
    }
    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }
}