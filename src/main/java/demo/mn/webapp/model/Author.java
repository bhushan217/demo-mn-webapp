package demo.mn.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@MappedEntity
public class Author extends BaseEntity {

    @Creator
    public Author(String name){
        this(name, null);
    }

    @NotBlank
    String name;

    @JsonIgnore
    @Nullable
    @Relation(value = Relation.Kind.MANY_TO_MANY, mappedBy = "authors", cascade = {Relation.Cascade.NONE})
    @EqualsAndHashCode.Exclude @ToString.Exclude
    Set<Book> books;

    public void addBook(Book book){
        if(books==null) books = new HashSet<>();
        books.add(book);
    }

}
