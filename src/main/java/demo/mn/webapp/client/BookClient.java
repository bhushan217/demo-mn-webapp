package demo.mn.webapp.client;

import demo.mn.webapp.model.Book;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Slice;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;

import java.util.List;
import java.util.Optional;

import static demo.mn.webapp.client.BookClient.PATH;

@Client(PATH)
public interface BookClient {

    String PATH = "/book";

    @Get("/{?pageable*}")
    Slice<Book> list(Pageable pageable);

    @Get("/names")
    List<String> names();

    @Get("/{id}")
    Optional<Book> findById(@PathVariable Long id);
}
