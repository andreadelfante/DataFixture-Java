package org.andreadelfante.datafixture.definitions

import com.github.javafaker.Faker
import org.andreadelfante.datafixture.makers.FixtureMaker
import java.util.*

/**
 * It defines a fixture to generate the model.
 */
class FixtureDefinition<Model> internal constructor(
        internal val definition: (Faker) -> Model,
        locale: Locale
) : FixtureMaker<Model> {
    private val faker = Faker(locale)

    override fun make(number: Int): List<Model> = (0 until number).map { definition(faker) }
}