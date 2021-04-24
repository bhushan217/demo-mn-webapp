package demo.mn.webapp;

import demo.mn.webapp.model.Author;
import demo.mn.webapp.model.Book;
import demo.mn.webapp.repository.BookRepository;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import org.h2.tools.Server;

import javax.inject.Singleton;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
record Application(BookRepository bookRepository, List<AppConfiguration.BookConfiguration> bookConfigurations) {

    public static void main(String[] args) throws Exception {
        Server.createWebServer().start();
        Micronaut.run(Application.class, args);
    }

    @EventListener
    void startUp(StartupEvent startupEvent) {
        bookRepository.saveAll(
                bookConfigurations.stream()
                        .map(Application::getBook)
                        .collect(Collectors.toList())
        );
    }

    static Book getBook(AppConfiguration.BookConfiguration config) {
        final Author author = new Author(config.author());
        final Book book = new Book(config.name(), config.pages(), Set.of(author), null);
        return book;
    }
}
