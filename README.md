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
1. Create a class to define a fixture. In Kotlin:
    ```kotlin
   class TodoFixture : JSONFixture<Todo> { // `Fixture` to define only a fixture model. For fixtured JSONObject you must use `JSONFixture`.
   
       // Define here a class Attributes to override fields during generation.
       // If you need to generate fake data you must not use it.
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
   
       override fun jsonFixture(obj: Todo, resolver: FixtureResolver): Map<String, Any> {
           return mapOf(
                   "text" to obj.text,
                   "isChecked" to obj.isChecked
           )
       }
   }
    ```
   In Java:
   ```java
   public class TodoFixture implements JSONFixture<Todo> { // `Fixture` to define only a fixture model. For fixtured JSONObject you must use `JSONFixture`.
   
       // Define here a class Attributes to override fields during generation.
       // If you need to generate fake data you must not use it. 
       public static class Attributes extends FixtureAttributes {
           private static final String TEXT = "TEXT";
           private static final String IS_CHECKED = "IS_CHECKED";
   
           public Attributes(String text, boolean isChecked) {
               super();
   
               set(TEXT, text);
               set(IS_CHECKED, isChecked);
           }
       }
   
       @Override
       public Todo fixture(@NotNull Faker faker, @NotNull FixtureAttributes attributes, @NotNull FixtureResolver resolver) {
           String text = attributes.get(Attributes.TEXT, faker.lorem().paragraph());
           boolean isChecked = attributes.get(Attributes.IS_CHECKED, faker.bool().bool());
   
           return new Todo(text, isChecked);
       }
   
       @NotNull
       public Map<String, Object> jsonFixture(Todo obj, @NotNull FixtureResolver resolver) {
           Map<String, Object> JSON = new HashMap<String, Object>();
           JSON.put("text", obj.getText());
           JSON.put("isChecked", obj.isChecked());
           return JSON;
       }
   }
   ```

2. Override FixtureFactory and define associations in the constructor. In Kotlin:
    ```kotlin
    class ExampleFixtureFactory : org.andreadelfante.datafixture.resolvers.FixtureFactory() {
        init {
            define(clazz = Todo::class, fixture = TodoFixture())
        }
    }
    ```
   In Java.
   ```java
   public class ExampleFixtureFactory extends org.andreadelfante.datafixture.resolvers.FixtureFactory {
       FixtureFactory() {
           super();
   
           define(Todo.class, new TodoFixture());
       }
   }
   ```

3. Call the fixture with `factory`. In Kotlin:
    ```kotlin
    val factory = ExampleFixtureFactory() // my FixtureFactory, not the library one
    
    factory[Todo::class].create()
    factory[Todo::class].create(2)
    factory[Todo::class].create(TodoFixture.Attributes(text = "Fixed text"))
   ```
    In Java:
    ```java
    ExampleFixtureFactory factory = ExampleFixtureFactory()
       
    factory.get(Todo.class).create()
    factory.get(Todo.class).create(2)
    factory.get(Todo.class).create(TodoFixture.Attributes("Fixed text")) 
   ```

## Contributing
DataFixture-Java is an open source project, so feel free to contribute.
You can open an issue for problems or suggestions, and you can propose your own fixes by opening a pull request with the changes.
