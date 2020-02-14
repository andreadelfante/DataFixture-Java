package org.andreadelfante.datafixture.models

import java.util.*

data class Person(val firstName: String,
                  val lastName: String,
                  val birthday: Date?,
                  val dogs: List<Dog>)