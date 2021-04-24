package demo.mn.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.MappedEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@MappedEntity
public record Book (
        @Id @GeneratedValue @Nullable
        Long id,
        String title,
        int pages,
        @Nullable @JsonIgnore @OneToMany//(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
        Set<Author> authors,
        @DateCreated @Nullable LocalDate dateCreated){
    public Book(String title, int pages, Set<Author> authors, LocalDate dateCreated) {
        this(null, title, pages, authors, dateCreated);
    }
}
