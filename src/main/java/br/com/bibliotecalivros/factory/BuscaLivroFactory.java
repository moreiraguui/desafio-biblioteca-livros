package br.com.bibliotecalivros.factory;

import br.com.bibliotecalivros.strategy.BuscaLivroStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static br.com.bibliotecalivros.utils.Constants.*;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class BuscaLivroFactory {

    private final Map<String, BuscaLivroStrategy> strategies = new HashMap<>();

    public BuscaLivroFactory(Set<BuscaLivroStrategy> strategySet) {
        strategySet.forEach(s -> strategies.put(s.keyBuscaLivro(), s));
    }

    public BuscaLivroStrategy obtemStrategy(String titulo, String autor, Integer anoPublicacao) {
        if (isNotBlank(titulo) && isNotBlank(autor) && nonNull(anoPublicacao)) {
            return strategies.get(BUSCA_POR_TODOS_FILTROS);
        } else if (isNotBlank(titulo)) {
            return strategies.get(BUSCA_POR_TITULO);
        } else if (isNotBlank(autor)) {
            return strategies.get(BUSCA_POR_AUTOR);
        } else if (nonNull(anoPublicacao)) {
            return strategies.get(BUSCA_POR_ANO_PUBLICACAO);
        }
        return strategies.get(BUSCA_SEM_FILTROS);
    }
}
