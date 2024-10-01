package br.com.bibliotecalivros.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "livros")
public class Livro {

    @Id
    private String id;

    @Field(name = "titulo")
    private String titulo;

    @Field(name = "autor")
    private String autor;

    @Field(name = "ano_publicacao")
    private Integer anoPublicacao;

    @Field(name = "isbn")
    private String isbn;
}