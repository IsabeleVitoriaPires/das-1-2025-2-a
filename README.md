# O que é abstração:
Abstração é o processo de representar elementos do mundo real no software. Por exemplo, em Java, utilizamos classes no pacote entity para representar entidades que refletem os dados reais armazenados no banco de dados.

## 📌 Princípios de Projeto de Software

Este documento apresenta anotações sobre boas práticas de projeto de software, com foco em organização, padronização, ocultamento de informações, coesão e acoplamento. Esses conceitos são fundamentais para manter a qualidade, legibilidade e manutenibilidade do código ao longo do tempo.

---

### 📐 Princípios Fundamentais de Projeto

- **Padronização**: Adotar um padrão de escrita de código é essencial para controlar a complexidade e facilitar o entendimento por toda a equipe. Em Java, por exemplo:
  - Nomes de classes devem começar com letra **maiúscula**.
  - Variáveis e métodos devem utilizar o padrão **camelCase**.

- **Organização por Camadas**:
  - `Entity`: representa os dados da aplicação (normalmente mapeados com o banco de dados).
  - `Repository`: responsável por conectar com o banco de dados.
  - `Service`: contém a lógica de negócio.
  - `Controller`: expõe endpoints, geralmente via API REST.
  - `Config`: centraliza as configurações da aplicação.

- **Reutilização de Frameworks**: Utilize frameworks consolidados ao invés de reinventar a roda. Isso economiza tempo e garante robustez.

- **Orientação a Objetos**: Essencial para modularizar o sistema, permitindo que diferentes partes sejam desenvolvidas e mantidas por diferentes pessoas de forma eficiente.

---

### 🔒 Ocultamento de Informação (Encapsulamento)

O encapsulamento é um dos pilares da programação orientada a objetos e consiste em restringir o acesso direto a determinados atributos ou comportamentos de uma classe:

- Em Java, atributos devem ser `private` e acessados por meio de métodos `getters` e `setters`.
- Esse conceito é amplamente utilizado em frameworks: mesmo sem conhecer a implementação interna, conseguimos utilizá-los corretamente.

---

### 🧠 Facilidade de Entendimento

Um código bem estruturado e padronizado facilita a compreensão por parte de novos desenvolvedores, permitindo que compreendam o sistema sem a necessidade de estudar cada detalhe da implementação.

> 💡 Uma boa prática para se tornar um desenvolvedor mais experiente é trabalhar com **manutenção de software**, pois ensina a lidar com sistemas legados e a lidar com código de terceiros.

---

### 🧾 Dívida Técnica

Dívida técnica ocorre quando uma solução é implementada de maneira rápida ou incorreta, funcionando momentaneamente, mas dificultando manutenções futuras. Exemplo:

- Código com baixo padrão de coesão e alto acoplamento.
- Soluções que “funcionam”, mas não seguem boas práticas.

---

### ⚙️ Coesão

Coesão refere-se à **clareza e foco** de uma classe ou módulo. Um código coeso realiza bem uma única tarefa.

- **Boa coesão**: cada classe possui responsabilidade única e bem definida.
  - Exemplo: um `Controller` deve conter apenas a lógica de controle (endpoints), e não regras de negócio.
- **Classe Anêmica**: contém apenas atributos e métodos de acesso (`getters`/`setters`). Embora não seja necessariamente um erro, pode indicar baixo nível de coesão dependendo do contexto.

> 🎯 Boas práticas de coesão envolvem bom senso: os métodos devem estar relacionados e cumprir um propósito único.

---

### 🔗 Acoplamento

Acoplamento é o grau de dependência entre diferentes partes do sistema. Deve-se buscar **baixo acoplamento**, ou seja, classes independentes entre si.

- **Alto acoplamento**: uma classe depende diretamente da implementação de outra.

  ```java
  class A {
      private B b;

      public A() {
          b = new B();
          b.fazAlgo();
      }
  }

