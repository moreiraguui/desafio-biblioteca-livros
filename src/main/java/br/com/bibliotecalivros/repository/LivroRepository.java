package br.com.bibliotecalivros.repository;

import br.com.bibliotecalivros.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends MongoRepository<Livro, String> {

    @Query(value = "{ $and: [ { 'titulo': { $regex: ?0, $options: 'i' } }, { 'autor': { $regex: ?1, $options: 'i' } }, { 'anoPublicacao': ?2 } ] }")
    Page<Livro> findByTituloAndAutorAndAnoPublicacao(String titulo, String autor, Integer anoPublicacao, Pageable pageable);

    @Query(value = "{ titulo: { $regex: ?0, $options: 'i' } }")
    Page<Livro> findAllByTitulo(String titulo, Pageable pageable);

    @Query(value = "{ autor : { $regex: ?0, $options: 'i' } }")
    Page<Livro> findAllByAutor(String autor, Pageable pageable);

    @Query(value = "{ ano_publicacao: ?0 }")
    Page<Livro> findAllByAnoPublicacao(Integer anoPublicacao, Pageable pageable);
}