package demo.mn.webapp.model;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import io.micronaut.data.annotation.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BaseEntity {
    @Id
    @GeneratedValue
    @Nullable
    Long id;

    @DateCreated
    LocalDateTime dateCreated;
    @Nullable
    Long createdBy;

    @DateUpdated
    @Nullable
    LocalDateTime dateUpdated;
    @Nullable
    Long updatedBy;
}
