package br.com.bibliotecalivros.strategy.impl;

import br.com.bibliotecalivros.model.Livro;
import br.com.bibliotecalivros.repository.LivroRepository;
import br.com.bibliotecalivros.strategy.BuscaLivroStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static br.com.bibliotecalivros.utils.Constants.BUSCA_POR_TODOS_FILTROS;

public class TituloAutorAnoStrategy implements BuscaLivroStrategy {

    @Override
    public Page<Livro> buscar(LivroRepository livroRepository, String titulo, String autor, Integer anoPublicacao, Pageable pageable) {
        return livroRepository.findByTituloAndAutorAndAnoPublicacao(titulo, autor, anoPublicacao, pageable);
    }

    @Override
    public String keyBuscaLivro() {
        return BUSCA_POR_TODOS_FILTROS;
    }
}
