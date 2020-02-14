package org.andreadelfante.datafixture;

import com.github.javafaker.Faker;
import org.andreadelfante.datafixture.interfaces.Fixture;
import org.andreadelfante.datafixture.misc.FixtureAttributes;
import org.andreadelfante.datafixture.resolvers.FixtureResolver;
import org.jetbrains.annotations.NotNull;

public class TodoFixture implements Fixture<Todo> {

    public static class Attributes extends FixtureAttributes {
        private static final String TEXT = "TEXT";
        private static final String IS_CHECKED = "IS_CHECKED";

        public Attributes(String text, boolean isChecked) {
            super();

            set(TEXT, text);
            set(IS_CHECKED, isChecked);
        }
    }

    public Todo fixture(@NotNull Faker faker, @NotNull FixtureAttributes attributes, @NotNull FixtureResolver resolver) {
        String text = attributes.get(Attributes.TEXT, faker.lorem().paragraph());
        boolean isChecked = attributes.get(Attributes.IS_CHECKED, faker.bool().bool());

        return new Todo(text, isChecked);
    }
}
