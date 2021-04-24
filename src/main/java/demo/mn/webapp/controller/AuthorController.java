package demo.mn.webapp.controller;

import demo.mn.webapp.AppConfiguration;
import demo.mn.webapp.client.AuthorClient;
import demo.mn.webapp.model.Author;
import demo.mn.webapp.repository.AuthorRepository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Slice;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.List;
import java.util.Optional;

import static demo.mn.webapp.client.BookClient.PATH;

@Controller(PATH)
@ExecuteOn(TaskExecutors.IO)
public record AuthorController(AuthorRepository authorRepository,
                               List<AppConfiguration.BookConfiguration> bookConfigurations)
        implements AuthorClient {

    @Override
    public Slice<Author> list(Pageable pageable){
        return authorRepository.findAll(pageable);
    }

    @Override
    public Optional<Author> findById(@PathVariable Long id){
        return authorRepository.findById(id);
    }

}
