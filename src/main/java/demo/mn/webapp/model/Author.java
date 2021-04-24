package demo.mn.webapp.model;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.MappedEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@MappedEntity
public record Author (
    @Id @GeneratedValue @Nullable Long id,
    @NotBlank
    String name) {
    public Author(String name) {
        this(null, name);
    }
}
