package br.com.bibliotecalivros.strategy;

import br.com.bibliotecalivros.model.Livro;
import br.com.bibliotecalivros.repository.LivroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface BuscaLivroStrategy {

    Page<Livro> buscar(LivroRepository livroRepository, String titulo, String autor, Integer anoPublicacao, Pageable pageable);

    String keyBuscaLivro();
}
