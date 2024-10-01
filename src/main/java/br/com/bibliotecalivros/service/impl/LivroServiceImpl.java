package br.com.bibliotecalivros.service.impl;

import br.com.bibliotecalivros.dto.LivroDTO;
import br.com.bibliotecalivros.exception.NotFoundException;
import br.com.bibliotecalivros.mapper.LivroMapper;
import br.com.bibliotecalivros.model.Livro;
import br.com.bibliotecalivros.repository.LivroRepository;
import br.com.bibliotecalivros.service.LivroService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.bibliotecalivros.utils.Constants.LIVRO_NAO_ENCONTRADO;
import static java.text.MessageFormat.format;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class LivroServiceImpl implements LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroMapper livroMapper;

    @Override
    public Page<LivroDTO> buscarLivros(String titulo, String autor, Integer anoPublicacao, Pageable pageable) {
        Page<Livro> livros;
        if (isNotBlank(autor) && isNotBlank(titulo) && nonNull(anoPublicacao)) {
            livros = livroRepository.findByTituloAndAutorAndAnoPublicacao(titulo, autor, anoPublicacao, pageable);
        } else if (isNotBlank(titulo)) {
            livros = livroRepository.findAllByTitulo(titulo, pageable);
        } else if (isNotBlank(autor)) {
            livros = livroRepository.findAllByAutor(autor, pageable);
        } else if (nonNull(anoPublicacao)) {
            livros = livroRepository.findAllByAnoPublicacao(anoPublicacao, pageable);
        } else {
            livros = livroRepository.findAll(pageable);
        }
        return livros.map(livroMapper::toDTO);
    }

    @Override
    public LivroDTO inserirLivro(LivroDTO livroDTO) {
        Livro livro = livroMapper.toEntity(livroDTO);
        return livroMapper.toDTO(livroRepository.save(livro));
    }

    @Override
    @SneakyThrows
    public LivroDTO atualizarLivro(String id, LivroDTO livroDTO) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format(LIVRO_NAO_ENCONTRADO, id)));

        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setAnoPublicacao(livroDTO.getAnoPublicacao());

        return livroMapper.toDTO(livroRepository.save(livro));
    }

    @Override
    @SneakyThrows
    public void deletarLivro(String id) {
        livroRepository.findById(id).orElseThrow(() -> new NotFoundException(format(LIVRO_NAO_ENCONTRADO, id)));
        livroRepository.deleteById(id);
    }
}