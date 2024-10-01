package br.com.bibliotecalivros.service;

import br.com.bibliotecalivros.dto.LivroDTO;
import br.com.bibliotecalivros.exception.NotFoundException;
import br.com.bibliotecalivros.mapper.LivroMapper;
import br.com.bibliotecalivros.model.Livro;
import br.com.bibliotecalivros.repository.LivroRepository;
import br.com.bibliotecalivros.service.impl.LivroServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LivroServiceTest {

    private static final String ID_LIVRO = "66fb4abec631ea74ae989879";
    private static final String TITULO = "Moby Dick";
    private static final String AUTOR = "Herman Melville";
    private static final String ISBN = "978-1503280786";
    private static final Integer ANO_PUBLICACAO = 1851;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private LivroMapper livroMapper;

    @InjectMocks
    private LivroServiceImpl livroService;

    private Pageable pageable;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pageable = PageRequest.of(0, 10);
    }

    @Test
    public void testBuscarLivrosPorTituloAutorAnoPublicacao() {
        Page<Livro> livroPage = new PageImpl<>(Collections.singletonList(mockLivro()));
        when(livroRepository.findByTituloAndAutorAndAnoPublicacao(anyString(), anyString(), anyInt(), any(Pageable.class)))
                .thenReturn(livroPage);
        when(livroMapper.toDTO(any(Livro.class))).thenReturn(mockLivroDTO());

        Page<LivroDTO> resultado = livroService.buscarLivros(TITULO, AUTOR, ANO_PUBLICACAO, pageable);

        assertNotNull(resultado);
        verify(livroRepository).findByTituloAndAutorAndAnoPublicacao(anyString(), anyString(), anyInt(), any(Pageable.class));
        verify(livroMapper).toDTO(any(Livro.class));
    }

    @Test
    public void testBuscarLivrosPorTitulo() {
        Page<Livro> livroPage = new PageImpl<>(Collections.singletonList(mockLivro()));
        when(livroRepository.findAllByTitulo(anyString(), any(Pageable.class))).thenReturn(livroPage);
        when(livroMapper.toDTO(any(Livro.class))).thenReturn(mockLivroDTO());

        Page<LivroDTO> resultado = livroService.buscarLivros(TITULO, null, null, pageable);

        assertNotNull(resultado);
        verify(livroRepository).findAllByTitulo(anyString(), any(Pageable.class));
        verify(livroMapper).toDTO(any(Livro.class));
    }

    @Test
    public void testBuscarLivrosPorAutor() {
        Page<Livro> livroPage = new PageImpl<>(Collections.singletonList(mockLivro()));
        when(livroRepository.findAllByAutor(anyString(), any(Pageable.class))).thenReturn(livroPage);
        when(livroMapper.toDTO(any(Livro.class))).thenReturn(mockLivroDTO());

        Page<LivroDTO> resultado = livroService.buscarLivros(null, AUTOR, null, pageable);

        assertNotNull(resultado);
        verify(livroRepository).findAllByAutor(anyString(), any(Pageable.class));
        verify(livroMapper).toDTO(any(Livro.class));
    }

    @Test
    public void testBuscarLivrosPorAnoPublicacao() {
        Page<Livro> livroPage = new PageImpl<>(Collections.singletonList(mockLivro()));
        when(livroRepository.findAllByAnoPublicacao(anyInt(), any(Pageable.class))).thenReturn(livroPage);
        when(livroMapper.toDTO(any(Livro.class))).thenReturn(mockLivroDTO());

        Page<LivroDTO> resultado = livroService.buscarLivros(null, null, ANO_PUBLICACAO, pageable);

        assertNotNull(resultado);
        verify(livroRepository).findAllByAnoPublicacao(anyInt(), any(Pageable.class));
        verify(livroMapper).toDTO(any(Livro.class));
    }

    @Test
    public void testBuscarTodosOsLivros() {
        Page<Livro> livroPage = new PageImpl<>(Collections.singletonList(mockLivro()));
        when(livroRepository.findAll(any(Pageable.class))).thenReturn(livroPage);
        when(livroMapper.toDTO(any(Livro.class))).thenReturn(mockLivroDTO());

        Page<LivroDTO> resultado = livroService.buscarLivros(null, null, null, pageable);

        assertNotNull(resultado);
        verify(livroRepository).findAll(any(Pageable.class));
        verify(livroMapper).toDTO(any(Livro.class));
    }


    @Test
    public void testInserirLivro() {
        when(livroMapper.toEntity(any(LivroDTO.class))).thenReturn(mockLivro());
        when(livroMapper.toDTO(any(Livro.class))).thenReturn(mockLivroDTO());
        when(livroRepository.save(any(Livro.class))).thenReturn(mockLivro());

        LivroDTO resultado = livroService.inserirLivro(mockLivroDTO());

        assertNotNull(resultado);
        verify(livroMapper, times(1)).toEntity(any(LivroDTO.class));
        verify(livroMapper, times(1)).toDTO(any(Livro.class));
        verify(livroRepository, times(1)).save(any(Livro.class));
    }

    @Test
    public void testAtualizarLivro() {
        when(livroRepository.findById(anyString())).thenReturn(Optional.of(mockLivro()));
        when(livroMapper.toDTO(any(Livro.class))).thenReturn(mockLivroDTO());
        when(livroRepository.save(any(Livro.class))).thenReturn(mockLivro());

        LivroDTO resultado = livroService.atualizarLivro(ID_LIVRO, mockLivroDTO());
        assertNotNull(resultado);
        verify(livroRepository, times(1)).findById(anyString());
        verify(livroMapper, times(1)).toDTO(any(Livro.class));
        verify(livroRepository, times(1)).save(any(Livro.class));
    }

    @Test
    public void testAtualizarLivroNotFoundException() {
        when(livroRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> livroService.atualizarLivro(anyString(), mockLivroDTO()));

        verify(livroRepository, times(1)).findById(anyString());
        verify(livroRepository, never()).save(any(Livro.class));
    }

    @Test
    public void testDeletarLivro() {
        when(livroRepository.findById(anyString())).thenReturn(Optional.of(mockLivro()));

        livroService.deletarLivro(ID_LIVRO);

        verify(livroRepository, times(1)).findById(anyString());
    }

    @Test
    public void testDeletarLivroNotFoundException() {
        when(livroRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> livroService.deletarLivro(ID_LIVRO));

        verify(livroRepository, times(1)).findById(anyString());
    }

    private static Livro mockLivro() {
        return Livro.builder()
                .id(ID_LIVRO)
                .titulo(TITULO)
                .autor(AUTOR)
                .anoPublicacao(ANO_PUBLICACAO)
                .isbn(ISBN)
                .build();
    }

    private static LivroDTO mockLivroDTO() {
        return LivroDTO.builder()
                .id(ID_LIVRO)
                .titulo(TITULO)
                .autor(AUTOR)
                .anoPublicacao(ANO_PUBLICACAO)
                .isbn(ISBN)
                .build();
    }
}