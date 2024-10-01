package br.com.bibliotecalivros.service;

import br.com.bibliotecalivros.dto.LivroDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LivroService {

    Page<LivroDTO> buscarLivros(String titulo, String autor, Integer anoPublicacao, Pageable pageable);

    LivroDTO inserirLivro(LivroDTO livroDTO);

    LivroDTO atualizarLivro(String id, LivroDTO livroDTO);

    void deletarLivro(String id);
}