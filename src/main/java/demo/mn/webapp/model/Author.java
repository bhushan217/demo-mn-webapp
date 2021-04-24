package demo.mn.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.*;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@MappedEntity
public class Author extends BaseEntity {

    @NotBlank
    String name;
    @JsonIgnore
    @Relation(value = Relation.Kind.MANY_TO_MANY, mappedBy = "authors", cascade = Relation.Cascade.PERSIST)
    @Nullable @EqualsAndHashCode.Exclude @ToString.Exclude
    Set<Book> books;//= Collections.emptySet();

    public void addBook(Book book){
//        if(book==null) return;
        if(books==null) books = new HashSet<>();
        books.add(book);
    }

}
