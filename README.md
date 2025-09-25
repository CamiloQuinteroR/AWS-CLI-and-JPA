# 🏗️ Taller: Accessing Data with JPA - Spring Boot

Este proyecto implementa una aplicación en **Spring Boot** que utiliza **Spring Data JPA** para almacenar y recuperar información desde una base de datos relacional.  
El taller sigue la guía oficial de Spring [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/) y tiene como objetivo comprender cómo funciona la integración entre **entidades JPA**, **repositorios**, y la persistencia de datos.

---

## 📌 Objetivos del Taller
1. Construir una aplicación Spring Boot que use **Spring Data JPA**.  
2. Implementar una clase de entidad (`Customer`) mapeada a una tabla en la base de datos.  
3. Definir un repositorio (`CustomerRepository`) para realizar operaciones **CRUD** sin escribir implementaciones manuales.  
4. Crear una clase principal (`AccessingDataJpaApplication`) que inicialice la aplicación, inserte datos de prueba y los recupere mediante consultas.  
5. Empaquetar la aplicación en un **JAR ejecutable**.  

---

## ⚙️ Requisitos
- **Java 17** o superior  
- **Maven 3.5+** o **Gradle 7.5+**  
- IDE recomendado: IntelliJ IDEA, Spring Tool Suite (STS), o VS Code  

---

## 🛠️ Dependencias utilizadas
El proyecto incluye las siguientes dependencias principales:

- **Spring Boot Starter Data JPA** → Para manejar entidades y repositorios con JPA.  
- **H2 Database** → Base de datos en memoria utilizada para pruebas.  
- **Spring Boot Starter Logging** → Para el manejo de logs con SLF4J.  

---

## 📂 Estructura del Proyecto

src/
└─ main/java/com/example/accessingdatajpa/
├─ Customer.java # Entidad JPA
├─ CustomerRepository.java # Repositorio JPA
├─ AccessingDataJpaApplication.java # Clase principal con CommandLineRunner

---

## 🧩 Componentes Implementados

### 1. **Entidad Customer**
Clase que representa un cliente.  
Se marca con `@Entity` y define un `id` autogenerado, más atributos `firstName` y `lastName`.

```java
@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  ...
}
```
2. Repositorio CustomerRepository
Interfaz que extiende CrudRepository, proporcionando métodos CRUD automáticos.
También define consultas derivadas por convención.

```java

public interface CustomerRepository extends CrudRepository<Customer, Long> {
  List<Customer> findByLastName(String lastName);
  Customer findById(long id);
}
```
3. Clase Principal AccessingDataJpaApplication
Contiene el método main() y un CommandLineRunner que:

Inserta datos de prueba.

Recupera todos los clientes (findAll()).

Busca un cliente por ID (findById()).

Filtra clientes por apellido (findByLastName()).

```java

@SpringBootApplication
public class AccessingDataJpaApplication {
  public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(CustomerRepository repository) {
    return (args) -> {
      repository.save(new Customer("Jack", "Bauer"));
      repository.save(new Customer("Chloe", "O'Brian"));
      ...
    };
  }
}
```

▶️ Ejecución del Proyecto
1. Ejecutar desde Maven

```
mvn spring-boot:run
```

3. Construir el JAR

```
mvn clean package
```

El archivo se genera en:

```
target/gs-accessing-data-jpa-0.1.0.jar
```

3. Ejecutar el JAR

```
java -jar target/gs-accessing-data-jpa-0.1.0.jar
```

