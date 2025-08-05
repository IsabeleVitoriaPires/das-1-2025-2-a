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

### 📚 Princípios SOLID

Os princípios **SOLID** foram propostos por **Robert C. Martin** (também conhecido como *Uncle Bob*), um dos principais nomes da engenharia de software moderna. Esses princípios têm como objetivo guiar o uso correto da **Programação Orientada a Objetos (POO)**, promovendo um design mais limpo, modular e de fácil manutenção.

Em resumo, aplicar **SOLID** significa usar a orientação a objetos de forma adequada, estruturando o código de forma coesa, desacoplada e flexível.

#### 🔸 S — Single Responsibility Principle (Princípio da Responsabilidade Única)
Cada classe deve ter **uma única responsabilidade** bem definida, ou seja, deve ser responsável por **apenas uma parte específica do comportamento do sistema**. Esse princípio está diretamente ligado ao conceito de **coesão**, pois classes com uma única responsabilidade tendem a ser mais fáceis de entender, manter e testar.

---

### 🧱 Arquitetura MVC (Model-View-Controller)

A arquitetura **MVC** é um padrão de projeto amplamente utilizado para organizar aplicações web e desktop, promovendo a separação de responsabilidades:

- **Model**: Representa os **dados da aplicação** e as regras de negócio. É importante destacar que o Model **não é a mesma coisa que a entidade** (embora em muitos frameworks estejam relacionados).
- **View**: Responsável pela **interface com o usuário** (geralmente arquivos HTML, CSS, JS).
- **Controller**: Atua como **intermediário** entre a View e o Model. Recebe as requisições da interface, processa as informações (possivelmente consultando ou manipulando o Model) e retorna uma resposta para a View.

### 🧩 I — Interface Segregation Principle (Princípio da Segregação de Interfaces)

O **Princípio da Segregação de Interfaces** afirma que **nenhuma classe deve ser forçada a depender de métodos que não utiliza**. Ou seja, é preferível ter **interfaces pequenas e específicas**, ao invés de uma única interface grande e genérica.

Esse princípio promove **baixo acoplamento** entre as classes, evitando ligações diretas entre módulos. Em vez disso, a comunicação entre classes deve ocorrer por meio de **interfaces bem definidas**, que atuam como contratos claros entre as partes do sistema.

#### ✅ Boas práticas:
- Criar **interfaces específicas** para cada responsabilidade.
  - Exemplo: uma `ClickListener` deve conter apenas métodos relacionados ao clique.
  - Outra interface, como `MouseListener`, deve cuidar apenas de eventos do mouse.
- Evitar a criação de interfaces genéricas demais que forçam as classes a implementar métodos desnecessários.

---

### 🖼️ Exemplo: Interfaces no Java com Swing

A biblioteca **Swing** é usada para construir interfaces gráficas em Java, e é um ótimo exemplo de aplicação do princípio de segregação de interfaces.

Enquanto o **AWT** depende do sistema operacional para renderizar os elementos gráficos (o que exige códigos diferentes para Windows, Linux, etc.), o **Swing** funciona de forma mais **portável**, sendo capaz de rodar em diferentes sistemas com o mesmo código.

Uma **interface**, nesse contexto, atua como um **contrato** entre o botão (componente visual) e a lógica de controle. Um bom exemplo é a interface `ActionListener`, que define o método `actionPerformed` para reagir a eventos de clique:

```java
package br.univille;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janelinha extends JFrame {

    private JButton botaozinho;
    private Controlador controlador;

    public Janelinha() {
        setTitle("Eu não acredito");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        botaozinho = new JButton("ME CLICA");
        controlador = new Controlador();
        botaozinho.addActionListener(controlador);

        add(botaozinho);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Janelinha();
    }
}




