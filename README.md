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


# ☁️ Taller: Infrastructure as Code con AWS-CLI

Este taller enseña a crear y administrar instancias EC2 en AWS usando AWS Command Line Interface (AWS-CLI).
El objetivo es aplicar el concepto de Infraestructura como Código (IaC) para automatizar el despliegue y gestión de recursos en la nube.

## 📌 Objetivos del Taller

Configurar AWS CLI con credenciales de acceso.

Crear un Key Pair para conexión segura.

Definir un Security Group con reglas de seguridad.

Lanzar una instancia EC2 (t2.micro).

Conectarse vía SSH a la instancia.

Listar instancias en ejecución.

Eliminar los recursos creados (Key Pair, Security Group, Instancia).

## ⚙️ Requisitos

Cuenta AWS activa

AWS CLI instalado

Permisos para EC2

Configuración inicial:

aws configure

🛠️ Pasos Resumidos

Crear Key Pair:

```
aws ec2 create-key-pair --key-name MyKeyPair --query 'KeyMaterial' --output text > MyKeyPair.pem
chmod 400 MyKeyPair.pem
```

Crear Security Group y agregar reglas (SSH y RDP):

```
aws ec2 create-security-group --group-name my-sg-cli --description "My security group" --vpc-id vpc-xxxxxxxx
aws ec2 authorize-security-group-ingress --group-id sg-xxxx --protocol tcp --port 22 --cidr 0.0.0.0/0
```

Lanzar Instancia EC2:

```
aws ec2 run-instances --image-id ami-032930428bf1abbff --count 1 --instance-type t2.micro --key-name MyKeyPair --security-group-ids sg-xxxx --subnet-id subnet-xxxx
```

Conexión por SSH:

```
ssh -i "MyKeyPair.pem" ec2-user@ec2-xx-xxx-xxx-xx.compute-1.amazonaws.com
```

Listar Instancias:

```
aws ec2 describe-instances --filters "Name=instance-type,Values=t2.micro"
```

Eliminar Recursos:

```
aws ec2 terminate-instances --instance-ids i-xxxx
aws ec2 delete-key-pair --key-name MyKeyPair
aws ec2 delete-security-group --group-id sg-xxxx
```





