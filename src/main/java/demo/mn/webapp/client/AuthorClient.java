package demo.mn.webapp.client;

import demo.mn.webapp.model.Author;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Slice;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;

import java.util.Optional;

import static demo.mn.webapp.client.AuthorClient.PATH;

@Client(PATH)
public interface AuthorClient {

    String PATH = "/author";

    @Get("/{?pageable*}")
    Slice<Author> list(Pageable pageable);

    @Get("/{id}")
    Optional<Author> findById(@PathVariable Long id);
}
