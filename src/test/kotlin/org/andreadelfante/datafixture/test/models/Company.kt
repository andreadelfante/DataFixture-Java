package org.andreadelfante.datafixture.test.models

import org.andreadelfante.datafixture.definitions.FixtureDefinition
import org.andreadelfante.datafixture.definitions.JSONFixtureDefinition
import org.andreadelfante.datafixture.factories.JSONFixtureFactory

data class Company(
        val name: String,
        val employees: List<Person>
) {
    companion object
}

fun Company.Companion.factory() = CompanyFixtureFactory()

class CompanyFixtureFactory : JSONFixtureFactory<Company>() {

    override fun definition(): FixtureDefinition<Company> = define { faker ->
        Company(
                name = faker.company().name(),
                employees = Person.factory().make(5)
        )
    }

    override fun jsonDefinition(): JSONFixtureDefinition<Company> = defineJSON { company ->
        mapOf(
                "name" to company.name,
                "employees" to Person.factory().makeJSON(company.employees)
        )
    }

    fun empty(name: String): FixtureDefinition<Company> = define { faker ->
        Company(name = name, employees = listOf())
    }
}
