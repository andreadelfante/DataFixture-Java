package org.andreadelfante.datafixture.definitions

import org.andreadelfante.datafixture.makers.JSONFixtureMaker
import org.andreadelfante.datafixture.misc.FixturePair

/**
 * It defines a fixture to generate the model and the associated JSON.
 */
class JSONFixtureDefinition<Model> internal constructor(
        internal val fixtureDefinition: FixtureDefinition<Model>,
        internal val JSONDefinition: (Model) -> Map<String, Any?>
) : JSONFixtureMaker<Model> {

    override fun makeJSON(number: Int): List<Map<String, Any?>> =
            (0 until number).map { JSONDefinition(fixtureDefinition.make()) }

    override fun makeJSON(objects: List<Model>): List<Map<String, Any?>> = objects.map(JSONDefinition)

    override fun makeWithJSON(number: Int): List<FixturePair<Model>> {
        return (0 until number).map {
            val model = fixtureDefinition.make();
            val json = JSONDefinition(model)
            FixturePair(model, json)
        }
    }

    override fun make(number: Int): List<Model> = fixtureDefinition.make(number)
}