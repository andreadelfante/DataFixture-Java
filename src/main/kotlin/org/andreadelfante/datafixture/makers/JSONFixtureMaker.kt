package org.andreadelfante.datafixture.makers

import org.andreadelfante.datafixture.misc.FixturePair

/**
 * This interface define a JSON fixture maker.
 */
interface JSONFixtureMaker<Model> : FixtureMaker<Model> {

    /**
     * Create a JSON Array of models.
     * @param number the number of JSON Object to make.
     * @return the JSON Array.
     */
    fun makeJSON(number: Int): List<Map<String, Any?>>

    /**
     * Create a JSON Array from a list of models.
     * @param objects the list to use.
     * @return the JSON Array.
     */
    fun makeJSON(objects: List<Model>): List<Map<String, Any?>>

    /**
     * Create an array of both model and its relative JSON Object.
     * @param number the number of JSON Object/Model to make.
     * @return a list of fixture pair.
     */
    fun makeWithJSON(number: Int): List<FixturePair<Model>>

    /**
     * Create a JSON Object.
     * @return a JSON Object.
     */
    fun makeJSON() = makeJSON(1).first()

    /**
     * Create a JSON Object from a model.
     * @param obj the model to use.
     * @return the JSON Array.
     */
    fun makeJSON(obj: Model): Map<String, Any?> = makeJSON(listOf(obj)).first()

    /**
     * Create a tuple of model and its relative JSON Object.
     * @return the tuple of model + JSON Object.
     */
    fun makeWithJSON(): FixturePair<Model> = makeWithJSON(1).first();
}