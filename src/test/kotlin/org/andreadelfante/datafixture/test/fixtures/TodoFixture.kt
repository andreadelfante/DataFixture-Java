package org.andreadelfante.datafixture.test.fixtures

import com.github.javafaker.Faker
import org.andreadelfante.datafixture.interfaces.Fixture
import org.andreadelfante.datafixture.misc.FixtureAttributes
import org.andreadelfante.datafixture.test.models.Todo
import org.andreadelfante.datafixture.resolvers.FixtureResolver

class TodoFixture : Fixture<Todo> {
    class Attributes(text: String? = null, isChecked: Boolean? = null) : FixtureAttributes() {
        companion object {
            const val TEXT = "TEXT"
            const val IS_CHECKED = "IS_CHECKED"
        }

        init {
            this[TEXT] = text
            this[IS_CHECKED] = isChecked
        }
    }

    override fun fixture(faker: Faker, attributes: FixtureAttributes, resolver: FixtureResolver): Todo {
        val text: String = attributes[Attributes.TEXT, faker.lorem().paragraph()]
        val isChecked: Boolean = attributes[Attributes.IS_CHECKED, faker.bool().bool()]

        return Todo(text, isChecked)
    }
}