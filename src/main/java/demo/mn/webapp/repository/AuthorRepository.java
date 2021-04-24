package demo.mn.webapp.repository;

import demo.mn.webapp.model.Author;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.repository.PageableRepository;


@JdbcRepository(dialect = Dialect.H2)
public interface AuthorRepository extends CrudRepository<Author, Long>, PageableRepository<Author, Long> {

    @Executable
    @Join(value = "books", alias = "b_")
    Page<Author> findAll(Pageable pageable);

}
