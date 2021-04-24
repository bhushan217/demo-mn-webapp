package demo.mn.webapp.repository;

import demo.mn.webapp.model.Author;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


@JdbcRepository(dialect = Dialect.H2)
public
interface AuthorRepository extends CrudRepository<Author, Long> {
    @Executable
    Author find(String name);

    Optional<Author> findByIdForUpdate(Long id);

    List<Author> findByNameForUpdate(String title);
}
