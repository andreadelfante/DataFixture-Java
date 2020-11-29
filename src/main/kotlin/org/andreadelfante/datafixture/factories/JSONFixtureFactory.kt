package org.andreadelfante.datafixture.factories

import org.andreadelfante.datafixture.definitions.FixtureDefinition
import org.andreadelfante.datafixture.definitions.JSONFixtureDefinition
import org.andreadelfante.datafixture.makers.JSONFixtureMaker
import org.andreadelfante.datafixture.misc.FixturePair
import java.util.*

/**
 * This class defines the rules to create a JSON Object from a model.
 */
abstract class JSONFixtureFactory<Model> : FixtureFactory<Model>(), JSONFixtureMaker<Model> {

    /**
     * The default JSON model definition.
     */
    abstract fun jsonDefinition(): JSONFixtureDefinition<Model>

    override fun makeJSON(number: Int): List<Map<String, Any?>> = jsonDefinition().makeJSON(number)

    override fun makeJSON(objects: List<Model>): List<Map<String, Any?>> = jsonDefinition().makeJSON(objects)

    override fun makeWithJSON(number: Int): List<FixturePair<Model>> = jsonDefinition().makeWithJSON(number)

    /**
     * Create a new JSON model fixture definition.
     * @param modelDefinition the model definition to use for the JSON definition.
     * @param JSONDefinition the JSON definition closure.
     * @return a new JSON model fixture definition.
     */
    protected fun defineJSON(
            modelDefinition: FixtureDefinition<Model>? = null,
            JSONDefinition: (Model) -> Map<String, Any?>
    ): JSONFixtureDefinition<Model> = JSONFixtureDefinition(
            fixtureDefinition = modelDefinition ?: definition(),
            JSONDefinition = JSONDefinition
    )

    /**
     * Edit the default JSON fixture definition.
     * @param locale the locale of the faker.
     * @param redefinition the redefinition closure.
     * @return a new JSON model fixture definition with the specified edits.
     */
    protected fun redefineJSON(
            locale: Locale = Locale.getDefault(),
            redefinition: (Model) -> Model
    ): JSONFixtureDefinition<Model> = JSONFixtureDefinition(
            fixtureDefinition = redefine(locale, redefinition),
            JSONDefinition = jsonDefinition().JSONDefinition
    )
}