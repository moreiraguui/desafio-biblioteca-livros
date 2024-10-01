package br.com.bibliotecalivros.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @NotBlank(message = "O título não pode estar em branco.")
    private String titulo;

    @NotBlank(message = "O autor não pode estar em branco.")
    private String autor;

    @NotNull(message = "O ano não pode ser nulo.")
    private Integer anoPublicacao;

    @NotBlank(message = "O ISBN não pode estar em branco.")
    private String isbn;
}