package org.andreadelfante.datafixture.factories

import com.github.javafaker.Faker
import org.andreadelfante.datafixture.definitions.FixtureDefinition
import org.andreadelfante.datafixture.makers.FixtureMaker
import java.util.*

/**
 * This class specifies the definitions to create an object.
 */
abstract class FixtureFactory<Model> : FixtureMaker<Model> {

    /**
     * The default model definition.
     */
    abstract fun definition(): FixtureDefinition<Model>

    override fun make(number: Int): List<Model> = definition().make(number)

    /**
     * Create a new model fixture definition.
     * @param locale the locale of the faker.
     * @param definition the definition closure.
     * @return a new model fixture definition.
     */
    protected fun define(
            locale: Locale = Locale.getDefault(),
            definition: (Faker) -> Model
    ): FixtureDefinition<Model> = FixtureDefinition(
            locale = locale,
            definition = definition
    )

    /**
     * Edit the default fixture definition.
     * @param locale the locale of the faker.
     * @param redefinition the redefinition closure.
     * @return a new model fixture definition with the specified edits.
     */
    protected fun redefine(
            locale: Locale = Locale.getDefault(),
            redefinition: (Model) -> Model
    ): FixtureDefinition<Model> = FixtureDefinition(
            locale = locale,
            definition = {
                val model = definition().definition(it)
                redefinition(model)
            }
    )
}