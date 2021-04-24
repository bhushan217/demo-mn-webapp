package demo.mn.webapp;

import demo.mn.webapp.controller.BookController;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class DemoMnWebappTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Inject
    BookController bookClient;

    @Inject
    AppConfiguration configuration;
    @Inject
    List<AppConfiguration.BookConfiguration> bookConfiguration;

    @Test
    @DisplayName("Test nested properties")
    void testIfNestedProperties(){
        assertEquals("Shah Book Center", configuration.name());
        assertEquals(2, bookConfiguration.size());
        assertTrue(bookConfiguration.stream().anyMatch(config->config.name().equals("you can win")));
    }

    @Test
    @DisplayName("Test nested properties using rest")
    void testNestedPropertiesUsingRest() {
        assertTrue(bookClient.names().contains("you can win"));
    }
}
