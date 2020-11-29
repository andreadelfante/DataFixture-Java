package org.andreadelfante.datafixture.makers

/**
 * This interface define a fixture maker.
 */
interface FixtureMaker<Model> {

    /**
     * Create a list of models.
     * @param number the number of models to make.
     * @return the list of models.
     */
    fun make(number: Int): List<Model>

    /**
     * Create a model.
     * @return the model.
     */
    fun make(): Model = make(1).first()
}