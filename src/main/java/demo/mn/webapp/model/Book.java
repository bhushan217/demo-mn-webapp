package demo.mn.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.jdbc.annotation.JoinTable;
import lombok.*;

import java.util.Collections;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@MappedEntity
public class Book extends BaseEntity {

    @Creator
    public Book(String title, int pages){
        this(title, pages, null);
    }

    String title;
    int pages;

    @JsonIgnore
    @Nullable
    @Relation(value = Relation.Kind.MANY_TO_MANY, cascade = {Relation.Cascade.ALL})
    @JoinTable(name = "Book_Author", joinColumns = {@MappedProperty(value = "book_id"), @MappedProperty("author_id")})
    @EqualsAndHashCode.Exclude @ToString.Exclude
    Set<Author> authors;

    public void addAuthor(Author author){
        if(authors==null) authors= Collections.emptySet();
        authors.add(author);
    }

}
