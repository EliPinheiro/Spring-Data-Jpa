# Projeto de Repositórios com Spring Data JPA

Este projeto demonstra diversas técnicas e práticas de consultas com Spring Data JPA, incluindo JPQL, Query Methods, Specifications e Criteria API. Além disso, cobre métodos de externalização de queries e personalização do repositório base.

## Índice

1. [Consultas JPQL](#consultas-jpql)
2. [Query Methods com Keywords](#query-methods-com-keywords)
3. [Prefixos de Query](#prefixos-de-query)
4. [Consultas Personalizadas com @Query](#consultas-personalizadas-com-query)
5. [Externalizando Queries com Arquivo XML](#externalizando-queries-com-arquivo-xml)
6. [Consultas Dinâmicas com Criteria API e JPQL](#consultas-dinâmicas-com-criteria-api-e-jpql)
7. [Implementação de Specifications](#implementação-de-specifications)
8. [Customizando o Repositório Base](#customizando-o-repositório-base)

---

### 1. Consultas JPQL

**JPQL** (Java Persistence Query Language) permite realizar consultas sobre entidades JPA de maneira similar ao SQL, mas utilizando as entidades em vez das tabelas diretamente.

Exemplo básico de uma consulta JPQL:

```java
@Query("SELECT u FROM User u WHERE u.email = :email")
User findByEmail(@Param("email") String email);
```
2. Query Methods com Keywords
O Spring Data JPA oferece suporte a Query Methods, que permitem construir consultas com base em convenções de nomenclatura de métodos.

Exemplo:
```
List<User> findByLastName(String lastName);
List<User> findByAgeGreaterThan(int age);
```

Principais Keywords:

And, Or
Between
LessThan, GreaterThan
Like, NotLike
IsNull, IsNotNull
In, NotIn
OrderBy
3. Prefixos de Query
Prefixos permitem que você defina o tipo de operação que será executada:

find...By: Recupera dados.
count...By: Conta o número de registros.
delete...By: Remove registros.
exists...By: Verifica se existe algum registro.
4. Consultas Personalizadas com @Query
É possível usar a anotação @Query para criar consultas JPQL personalizadas diretamente dentro do repositório.


```
@Query("SELECT p FROM Product p WHERE p.price > :price")
List<Product> findProductsByPriceGreaterThan(@Param("price") BigDecimal price);
```
5. Externalizando Queries com Arquivo XML
Além de definir consultas dentro das classes de repositório, você pode externalizar suas queries em arquivos XML.

Crie um arquivo META-INF/orm.xml no diretório de recursos.
Defina suas consultas no formato XML.
Exemplo:



```
<entity-mappings>
    <named-query name="User.findByEmail">
        <query>SELECT u FROM User u WHERE u.email = :email</query>
    </named-query>
</entity-mappings>
```
No repositório, você pode usar a query nomeada:

```
@Query(name = "User.findByEmail")
User findByEmail(@Param("email") String email);
```
6. Consultas Dinâmicas com Criteria API e JPQL
Criteria API é usada para construir consultas dinâmicas programaticamente em vez de usar JPQL estático.

Exemplo básico com Criteria API:
```
CriteriaBuilder cb = entityManager.getCriteriaBuilder();
CriteriaQuery<User> query = cb.createQuery(User.class);
Root<User> root = query.from(User.class);

Predicate agePredicate = cb.greaterThan(root.get("age"), 30);
query.where(agePredicate);

List<User> users = entityManager.createQuery(query).getResultList();
```
7. Implementação de Specifications
Specifications são usadas para criar filtros dinâmicos e reutilizáveis em consultas complexas.

Exemplo de uma Specification:
```
public class ProductWithFreeShippingSpec implements Specification<Product> {

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("shippingCost"), BigDecimal.ZERO);
    }
}
```
Essa Specification pode ser usada no repositório:

```
List<Product> findAll(Specification<Product> spec);
```
8. Customizando o Repositório Base
Você pode estender o JpaRepository para adicionar funcionalidades personalizadas ao seu repositório base.

Crie uma interface para o repositório customizado:

```
public interface CustomJpaRepository<T, ID> {
    Optional<T> buscarPrimeiro();
}
```
Implemente a interface:

```
public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> 
    implements CustomJpaRepository<T, ID> {

    private EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {
        String jpql = "from " + getDomainClass().getName();
        T entity = entityManager.createQuery(jpql, getDomainClass())
                                .setMaxResults(1)
                                .getSingleResult();
        return Optional.ofNullable(entity);
    }
}
```

Configure o Spring Data para usar seu repositório customizado:
java

```
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class JpaConfig {
}
```
```


