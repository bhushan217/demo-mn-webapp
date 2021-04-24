package demo.mn.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.*;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@MappedEntity
public class Book extends BaseEntity {
    String title;
    int pages;

    @JsonIgnore
    @Relation(value = Relation.Kind.MANY_TO_MANY, mappedBy = "books", cascade = Relation.Cascade.PERSIST)
    @Nullable @EqualsAndHashCode.Exclude @ToString.Exclude
    Set<Author> authors ;//= Collections.emptySet();

    public void addAuthor(Author author){
        if(authors==null) authors= Collections.emptySet();
        authors.add(author);
    }

}
