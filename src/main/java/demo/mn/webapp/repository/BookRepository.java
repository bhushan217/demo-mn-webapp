package demo.mn.webapp.repository;

import demo.mn.webapp.model.Book;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;
import java.util.Optional;


@JdbcRepository(dialect = Dialect.H2)
public interface BookRepository extends CrudRepository<Book, Long>, PageableRepository<Book, Long> {
    @Executable
    List<Book> find(String title);

    @Executable
    @Join(value = "authors", alias = "a_")
    Page<Book> findAll(Pageable pageable);

    @Join("authors")
    Optional<Book> findByIdForUpdate(Long id);

    List<Book> findAllOrderByTitleForUpdate();

    List<Book> findByTitleForUpdate(String title);
}
