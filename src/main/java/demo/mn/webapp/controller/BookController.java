package demo.mn.webapp.controller;

import demo.mn.webapp.AppConfiguration;
import demo.mn.webapp.client.BookClient;
import demo.mn.webapp.model.Book;
import demo.mn.webapp.repository.BookRepository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Slice;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.List;
import java.util.Optional;

import static demo.mn.webapp.client.BookClient.PATH;
import static java.util.stream.Collectors.toList;

@Controller(PATH)
@ExecuteOn(TaskExecutors.IO)
public record BookController (BookRepository bookRepository,
         List<AppConfiguration.BookConfiguration> bookConfigurations)
        implements BookClient {

    @Override
    public Slice<Book> list(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(@PathVariable Long id){
        return bookRepository.findById(id);
    }

    @Override
    public List<String> names(){
        return bookConfigurations.stream()
                .filter(AppConfiguration.BookConfiguration::isNew)
                .map(AppConfiguration.BookConfiguration::name)
                .collect(toList());
    };
}
