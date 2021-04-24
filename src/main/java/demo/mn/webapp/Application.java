package demo.mn.webapp;

import demo.mn.webapp.model.Author;
import demo.mn.webapp.model.Book;
import demo.mn.webapp.repository.AuthorRepository;
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
record Application(BookRepository bookRepository, AuthorRepository authorRepository, List<AppConfiguration.BookConfiguration> bookConfigurations) {

    public static void main(String[] args) throws Exception {
        Server.createWebServer().start();
        Micronaut.run(Application.class, args);
    }

    @EventListener
    void startUp(StartupEvent startupEvent) {
        final List<Author> authorList = bookConfigurations.stream()
                .map(Application::getAuthor)
                .collect(Collectors.toList());
        authorRepository.saveAll(authorList);
        final Iterable<Author> authorList2 = authorRepository.findAll();
        final List<Book> bookList = bookConfigurations.stream()
                .map(config -> getBook(config, (List<Author>) authorList2))
                .collect(Collectors.toList());
        bookRepository.saveAll(bookList);
    }

    static Author getAuthor(AppConfiguration.BookConfiguration config) {
        final Author author = new Author();
        author.setName(config.author());
        return author;
    }

    static Book getBook(AppConfiguration.BookConfiguration config, List<Author> authorList) {
        final Author author = authorList.stream().filter(a -> a.getName().equals(config.author())).findFirst().get();
        final Book book = new Book(config.name(), config.pages(), Set.of(author));
        author.addBook(book);
        return book;
    }
}
