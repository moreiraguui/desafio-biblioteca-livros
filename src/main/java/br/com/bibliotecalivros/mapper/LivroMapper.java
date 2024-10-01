package br.com.bibliotecalivros.mapper;

import br.com.bibliotecalivros.dto.LivroDTO;
import br.com.bibliotecalivros.model.Livro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    public abstract Livro toEntity(LivroDTO livroDTO);

    public abstract LivroDTO toDTO(Livro livro);
}