package org.andreadelfante.datafixture;

public class FixtureFactory extends org.andreadelfante.datafixture.resolvers.FixtureFactory {
    FixtureFactory() {
        super();

        define(Todo.class, new TodoFixture());
    }
}
