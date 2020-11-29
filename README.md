# DataFixture-Java

[![](https://jitpack.io/v/andreadelfante/DataFixture-Java.svg)](https://jitpack.io/#andreadelfante/DataFixture-Java)

Create data models easily, with no headache. DataFixture is a convenient way to generate new data for testing / seeding your Database.

This library is a porting of [DataFixture](https://github.com/andreadelfante/DataFixture).

## Installation
### Gradle
1. Add the JitPack repository to your build file.
Add it in your root build.gradle at the end of repositories:
    ```
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    ```
2. Add the dependency.
    ```
    dependencies {
        implementation 'com.github.andreadelfante:DataFixture-Java:<version>'
        // or testImplementation for only use in test.
    }
    ```

## Usage
### Basic

1. Create a new file to define the fixture factory for a model.
<details>
    <summary>In Kotlin</summary>
    
    data class Company(
           val name: String,
           val employees: List<Person>
    ) {
       // This is required!
       companion object
    }
    
    fun Company.Companion.factory() = CompanyFixtureFactory()
    
    class CompanyFixtureFactory : FixtureFactory<Company>() {
    
       override fun definition(): FixtureDefinition<Company> = define { faker ->
           Company(
                   name = faker.company().name(),
                   employees = Person.factory().make(5)
           )
       }
    
       fun empty(name: String): FixtureDefinition<Company> = define { faker ->
           Company(name = name, employees = listOf())
       }
    }
</details>

<details>
    <summary>In Java</summary>
    
    public class Company {
        final String name;
        final List<Person> employees;
   
        public Company(String name, List<Person> employees) {
           this.name = name;
           this.employees = employees;
        }
        
        static Factory factory() {
           return new Factory();
        }
        
        static class Factory extends FixtureFactory<Company> {
        
           @NotNull
           public FixtureDefinition<Company> definition() {
               return define(Locale.getDefault(), new Function1<Faker, Company>() {
                   public Company invoke(Faker faker) {
                       return Company(faker.company().name(), Person.factory().make(5));
                   }
               });
           }
           
           public FixtureDefinition<Company> empty(final String name) {
               return redefine(Locale.getDefault(), new Function1<Company, Company>() {
                   public Company invoke(Company company) {
                       return Company(name, new ArrayList<Person>());
                   }
               });
           }
        }
    }
</details>

2. Then you can build the model by using its factory.
<details>
    <summary>In Kotlin</summary>
    
    // Create a single object of type Company.
    Company.factory().make()
    // Create a single object of type Company with no employees.
    Company.factory().empty(name = "EmptyCompany").make()
    
    // Create 10 objects of type Company.
    Company.factory().make(10)
    // Create 10 objects of type Company with no employees.
    Company.factory().empty(name = "EmptyCompany").make(10)
</details>

<details>
    <summary>In Java</summary>
    
    // Create a single object of type Company.
    Company.factory().make()
    // Create a single object of type Company with no employees.
    Company.factory().empty("EmptyCompany").make()
    
    // Create 10 objects of type Company.
    Company.factory().make(10)
    // Create 10 objects of type Company with no employees.
    Company.factory().empty("EmptyCompany").make(10)
</details>

## JSON Fixtures
A factory can create a JSON Object from a generated model.

1. First, you have to extend `JSONFixtureFactory` object to the model factory.
<details>
    <summary>In Kotlin</summary>
    
    class CompanyFixtureFactory : JSONFixtureFactory<Company>() {
    
        override fun definition(): FixtureDefinition<Company> = define { faker ->
            Company(
                    name = faker.company().name(),
                    employees = Person.factory().make(5)
            )
        }
    
        // This function define the json definition, using the default definition (function `definition()`).
        override fun jsonDefinition(): JSONFixtureDefinition<Company> = defineJSON { company ->
            mapOf(
                    "name" to company.name,
                    "employees" to Person.factory().makeJSON(company.employees)
            )
        }
    
        // If you need to generate the JSON Object of an empty company, change the return type to `JSONFixtureDefinition`
        // Previously the return was `FixtureDefinition`.
        fun empty(name: String): JSONFixtureDefinition<Company> = redefineJSON { company ->
            Company(
                name = name,
                employees = listOf()
            )
        }
    }
</details>

<details>
    <summary>In Java</summary>
    
    static class Factory extends JSONFixtureFactory<Company> {
            
    @NotNull
    public FixtureDefinition<Company> definition() {
       return define(new Function1<Faker, Company>() {
           public Company invoke(Faker faker) {
               return Company(faker.company().name(), Person.factory().make(5));
           }
       });
    }
    
    // This function define the json definition, using the default definition (function `definition()`).
    @NotNull
    public JSONFixtureDefinition<Company> jsonDefinition() {
        return defineJSON(definition(), new Function1<Company, Map<String, Object>>() {
            public Map<String, Object> invoke(Company company) {
                Map<String, Object> json = new HashMap<String, Object>();
                json.put("name", company.name);
                json.put("employees", Person.factory().makeJSON(company.employees));
                return json;
            }
        });
    }
    
    // If you need to generate the JSON Object of an empty company, change the return type to `JSONFixtureDefinition`
    // Previously the return was `FixtureDefinition`.
    public JSONFixtureDefinition<Company> empty(final String name) {
       return redefineJSON(Locale.getDefault(), new Function1<Company, Company>() {
           public Company invoke(Company company) {
               return Company(name, new ArrayList<Person>());
           }
       });
    }
</details>

2. Now you can generate the JSON Object of the model.
<details>
    <summary>In Kotlin</summary>
    
    // Create a single JSON object of type Company.
    Company.factory().makeJSON()
    // Create a single JSON object of type Company with no employees.
    Company.factory().empty(name = "EmptyCompany").makeJSON()
    
    // Create a JSON Array of 10 objects of type Company.
    Company.factory().makeJSON(10)
    // Create a JSON Array of 10 objects of type Company with no employees.
    Company.factory().empty(name = "EmptyCompany").makeJSON(10)
    
    // Create a Company object with its relative JSON object.
    Company.factory().makeWithJSON()
    // Create 10 Company object with its relative JSON objects.
    Company.factory().makeWithJSON(10)
</details>

<details>
    <summary>In Java</summary>
    
    // Create a single JSON object of type Company.
    Company.factory().makeJSON()
    // Create a single JSON object of type Company with no employees.
    Company.factory().empty("EmptyCompany").makeJSON()
    
    // Create a JSON Array of 10 objects of type Company.
    Company.factory().makeJSON(10)
    // Create a JSON Array of 10 objects of type Company with no employees.
    Company.factory().empty("EmptyCompany").makeJSON(10)
    
    // Create a Company object with its relative JSON object.
    Company.factory().makeWithJSON()
    // Create 10 Company object with its relative JSON objects.
    Company.factory().makeWithJSON(10)
</details>

3. With `JSONFixtureFactory` you can create a JSON from an external model object.
<details>
    <summary>In Kotlin</summary>
    
    val company = Company.factory().make()
    val JSONObject = Company.factory().makeJSON(company)
    
    val companies = Company.factory().make(3)
    val JSONArray = Company.factory().makeJSON(companies)
</details>

<details>
    <summary>In Java</summary>
    
    Company company = Company.factory().make()
    Map<String, Object> JSONObject = Company.factory().makeJSON(company)
    
    List<Company> companies = Company.factory().make(3)
    List<Map<String, Object>> JSONArray = Company.factory().makeJSON(companies)
</details>

## Contributing
**DataFixture-Java** is an open source project, so feel free to contribute.
You can open an issue for problems or suggestions, and you can propose your own fixes by opening a pull request with the changes.
