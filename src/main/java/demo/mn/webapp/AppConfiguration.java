package demo.mn.webapp;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.EachProperty;
import io.micronaut.core.bind.annotation.Bindable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@ConfigurationProperties("store")
public record AppConfiguration(String name) {
    @EachProperty("books")
    public static record BookConfiguration(
            @NotBlank
            String name,
            @Max(1550)
            int pages,
            String author,
            @Bindable(defaultValue = "true")
            boolean isNew
    ){}
}
