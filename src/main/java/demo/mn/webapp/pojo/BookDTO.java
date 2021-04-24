package demo.mn.webapp.pojo;

import io.micronaut.core.annotation.Introspected;

import java.util.Set;

@Introspected
public record BookDTO (Long id, String title, int pages, Set<AuthorDTO> authors){}
