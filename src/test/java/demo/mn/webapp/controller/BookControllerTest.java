package demo.mn.webapp.controller;

import demo.mn.webapp.model.Book;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Slice;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static demo.mn.webapp.client.BookClient.PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class BookControllerTest {

//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

//    @Inject
//    EmbeddedServer server;
//
//    @Inject
//    @Client("/")
//    HttpClient client;
    @Inject
    BookController client;
/*
    @BeforeAll
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class, Collections.singletonMap(
                "consul.client.enabled", false
        ));
        client = server
                .getApplicationContext()
                .createBean(HttpClient.class, server.getURL());
    }

    @AfterAll
    public static void stopServer() {
        if(server != null) {
            server.stop();
        }
        if(client != null) {
            client.stop();
        }
    }*/

    @DisplayName("Find All")
    @Test
    public void testFindAll() throws Exception {
        //HttpResponse rsp = client.toBlocking().exchange(HttpRequest.GET(PATH));
        //assertEquals(200, rsp.getStatus().getCode());
    }

    @DisplayName("Find One")
    @Test
    public void testFindOne() throws Exception {
        //Optional<Book> rsp = client.toBlocking().retrieve(HttpRequest.GET(PATH+"/1"), Optional.class);
        //assertEquals(200, rsp.getStatus().getCode());
        final Book book = client.findById(1L).get();
        assertNotNull(book.getTitle());
    }

    @DisplayName("Calls With Pageable")
	@Test
    void callsWithPageable() {
        String uri = PATH + "/?page=0&size=2";// "/{\"page\":1,\"size\":2}";

        //Page<String> pages = client.toBlocking().retrieve(HttpRequest.GET(uri), Page.class);//, pageOf(String.class)
        Slice<Book> pages = client.list(Pageable.from(0,2));

        assertEquals(0, pages.getPageNumber());
//        assertEquals(1, pages.getTotalPages());
        assertEquals(2, pages.getSize());
        assertEquals(0, pages.getContent().size());
    }
}