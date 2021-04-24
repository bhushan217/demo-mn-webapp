package demo.mn.webapp.controller;

import demo.mn.webapp.model.Author;
import demo.mn.webapp.model.Book;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Slice;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class BookControllerTest {

    @Inject
    BookController bookController;
    @Inject
    AuthorController authorController;

    @DisplayName("Find All")
    @Test
    public void testFindAll() throws Exception {
        Slice<Book> pages = bookController.list(Pageable.from(0,2));
        assertEquals(0, pages.getPageNumber());
    }

    @DisplayName("Find One Author")
    @Test
    public void testFindOneAuthor() throws Exception {
        final Author author = authorController.findById(1L).get();
        assertNotNull(author.getName());
    }

    @DisplayName("Find One Book")
    @Test
    public void testFindOneBook() throws Exception {
        final Book book = bookController.findById(1L).get();
        assertNotNull(book.getTitle());
    }

    @DisplayName("list Books : Pageable")
    @Test
    void callsWithPageable() {
        Slice<Book> pages = bookController.list(Pageable.from(0,2));

        assertEquals(0, pages.getPageNumber());
        assertEquals(2, pages.getSize());
        assertEquals(0, pages.getContent().size());
    }
}