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


## 🔄 Dependency Inversion Principle (Princípio da Inversão de Dependência)

Uma classe deve **depender de abstrações e não de implementações concretas**.  
Isso significa que módulos de alto nível (como **Controllers**) não devem conhecer diretamente as classes de baixo nível (como repositórios específicos), mas sim trabalhar com **interfaces**.

No **Spring**, isso é aplicado com **injeção de dependência** (`@Autowired`):  

- O **controller** declara uma variável do tipo da interface.  
- O **Spring** identifica automaticamente a implementação correta e a injeta.  

➡️ Assim, o controller **não depende da implementação diretamente**, mas sim da **abstração**.

⚠️ Importante: usar `new` em uma interface cria uma **classe anônima** — isso só deve ser usado em **códigos pequenos e específicos**, pois gera alto acoplamento e reduz a clareza.

---

## 🏗️ Prefira Composição a Herança

No início da orientação a objetos, acreditava-se que a **herança resolveria todos os problemas** como uma “bala de prata”.  
Com o tempo, percebeu-se que **herança excessiva é perigosa**, pois:

- Viola o **encapsulamento** (o filho herda tudo do pai, mesmo o que não deveria).  
- Aumenta o **acoplamento** e a complexidade do código.  
- Não é bem representada em **bancos relacionais**, já que não existe herança nativa (a solução seria criar um “tabelão” misturando pai e filhos, o que não é ideal).  

➡️ Por isso, recomenda-se usar **composição e associações** sempre que possível.

### ✅ Quando usar herança?
- Apenas quando os filhos **sempre serão do tipo pai** e **nunca poderão ser convertidos entre si**.  

**Exemplo correto:**  
- `Animal` → `Cachorro` e `Gato` (um cachorro nunca será gato).  

**Exemplo incorreto:**  
- `Pessoa` → `Cliente` e `Funcionario`. (um funcionário também pode ser cliente).  

⚠️ É mais fácil migrar de **composição para herança** do que o contrário.

---

## 📏 Law of Demeter (Princípio do Menor Conhecimento)

Esse princípio defende que um método deve depender **apenas do necessário**.  
Ou seja, cada método só pode interagir com:

- A própria classe (`this`);  
- Parâmetros recebidos;  
- Objetos criados dentro do método;  
- Atributos diretos da classe.  

### 🚫 Exemplo ruim (alto acoplamento):
```java
pedido.getCliente().getEndereco().getCidade().getNome();
```

### ✅ Exemplo melhor (encapsulando a lógica):
```java
pedido.getCidadeDoCliente();
```

➡️ Assim, reduzimos dependências externas e evitamos **quebras futuras** caso a estrutura interna de `Cliente` ou `Endereco` mude.

---

## 🔒 Open/Closed Principle (Princípio Aberto/Fechado)

Uma classe deve estar:  
- **Fechada para modificação** (evitar alterações diretas que podem gerar novos bugs).  
- **Aberta para extensão** (permitir novos comportamentos sem mexer no código já testado).  

Isso pode ser feito com **interfaces, classes abstratas e padrões de projeto** como **Strategy** ou **Template Method**.

### Exemplos de abstrações em Java:
- **Classe** → contém atributos e métodos.  
- **Interface** → define um contrato (métodos sem implementação).  
- **Classe Abstrata** → mistura classe e interface; já traz parte do código pronto, mas exige que os métodos abstratos sejam implementados pelos filhos.  

➡️ Geralmente, utiliza-se interfaces e classes abstratas para aplicar este princípio.

---

## 🔁 Liskov Substitution Principle (Princípio da Substituição de Liskov)

Se uma classe `S` é subtipo de `T`, então deve ser possível substituir `T` por `S` **sem quebrar o código**.  
Ou seja: **qualquer filho deve poder substituir o pai de forma transparente**.

### Exemplo (Swing):
```java
JPanel panel = new JPanel();

Border line   = new javax.swing.border.LineBorder(java.awt.Color.BLACK);
Border titled = new javax.swing.border.TitledBorder("Detalhes");

// Ambos funcionam pois são subtipos de Border
panel.setBorder(line);
panel.setBorder(titled);
```

➡️ Tanto `LineBorder` quanto `TitledBorder` são subtipos de `Border`.  
Isso mostra que qualquer componente que espera um `Border` pode aceitar **qualquer subtipo** sem problema.

---

## ✅ Conclusão

Seguir esses princípios garante sistemas:  
- Mais **flexíveis**  
- Mais **modulares**  
- Mais **fáceis de manter**  

> ✨ Prefira **interfaces, composição e encapsulamento** em vez de herança desnecessária e acoplamento forte.

---

# 🎨 Padrões de Projeto (Design Patterns)

Os **Padrões de Projeto** são soluções reutilizáveis para problemas recorrentes no desenvolvimento de software.  
Foram popularizados pelos "Gang of Four" (Erich Gamma, Richard Helm, Ralph Johnson e John Vlissides) em 1994.  

---

## 🤔 Por que aprender Padrões de Projeto?

Um desenvolvedor pode se beneficiar do domínio de padrões de projeto em dois cenários principais:

1. **Ao implementar seu próprio sistema** → permite adotar soluções de projeto já testadas e validadas.  
2. **Ao usar sistemas de terceiros** → facilita entender a estrutura e o comportamento de classes prontas, como `DocumentBuilderFactory` em Java.

Os autores defendem que devemos projetar sistemas pensando nas mudanças inevitáveis — chamam isso de **design for change**.  
Caso contrário, corremos o risco de precisar reprojetar todo o sistema no futuro.

---

## 📚 Categorias de Padrões

No livro existem **23 padrões**, divididos em três categorias:

- **Criacionais**: soluções para criação de objetos.  
  Exemplos: `Abstract Factory`, `Factory Method`, `Singleton`, `Builder`, `Prototype`.  

- **Estruturais**: soluções para composição de classes e objetos.  
  Exemplos: `Proxy`, `Adapter`, `Facade`, `Decorator`, `Bridge`, `Composite`, `Flyweight`.  

- **Comportamentais**: soluções para interação e divisão de responsabilidades.  
  Exemplos: `Strategy`, `Observer`, `Template Method`, `Visitor`, `Chain of Responsibility`,  
  `Command`, `Interpreter`, `Iterator`, `Mediator`, `Memento`, `State`.  

**Tradução adotada:** alguns padrões serão traduzidos → *Fábrica Abstrata, Método Fábrica, Adaptador, Fachada, Decorador, Observador e Iterador*.  
Os demais permanecerão em inglês.

---

## 🏭 Factory Method (Método Fábrica)

### Contexto
Suponha um sistema distribuído baseado em TCP/IP.  
Três funções (`f`, `g`, `h`) criam objetos `TCPChannel` para comunicação:

```java
void f() {
  TCPChannel c = new TCPChannel();
  ...
}

void g() {
  TCPChannel c = new TCPChannel();
  ...
}

void h() {
  TCPChannel c = new TCPChannel();
  ...
}
```

### Problema
Se for necessário usar `UDP`, o sistema quebra o **Princípio Aberto/Fechado**.  
O código não está preparado para extensões sem modificações.

### Solução
Criar um **método fábrica estático** que centralize a criação dos objetos:

```java
class ChannelFactory {
  public static Channel create() { // método fábrica estático
    return new TCPChannel();
  }
}

void f() {
  Channel c = ChannelFactory.create();
  ...
}

void g() {
  Channel c = ChannelFactory.create();
  ...
}

void h() {
  Channel c = ChannelFactory.create();
  ...
}
```

➡️ Agora, se o canal mudar para UDP, apenas o método `create` da `ChannelFactory` precisa ser alterado.  

### Fábrica Abstrata
Uma variação utiliza uma **classe abstrata** para definir vários métodos fábrica:

```java
abstract class ProtocolFactory { // Fábrica Abstrata
  abstract Channel createChannel();
  abstract Port createPort();
  ...
}

void f(ProtocolFactory pf) {
  Channel c = pf.createChannel();
  Port p = pf.createPort();
  ...
}
```

➡️ Subclasses como `TCPProtocolFactory` e `UDPProtocolFactory` implementam os métodos concretos.

---

## 🔒 Singleton

### Contexto
Suponha uma classe `Logger` usada para registrar operações do sistema:

```java
void f() {
  Logger log = new Logger();
  log.println("Executando f");
}

void g() {
  Logger log = new Logger();
  log.println("Executando g");
}

void h() {
  Logger log = new Logger();
  log.println("Executando h");
}
```

O problema é que criamos **múltiplas instâncias** do `Logger`, o que não é eficiente nem desejável.

### Solução
Aplicar o padrão **Singleton**, garantindo que a classe possua apenas **uma única instância global**:

```java
public class Logger {
    private static Logger instance;

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void println(String msg) {
        System.out.println(msg);
    }
}

// Uso
Logger log = Logger.getInstance();
log.println("Executando f");
```

---

## 👀 Observer (Observador)

### Contexto
O **Observer** define uma relação de dependência **um-para-muitos** entre objetos.  
Quando um objeto muda de estado, todos os dependentes são notificados automaticamente.

Exemplo prático: sistemas de **eventos e notificações**.

### Estrutura
- **Subject** → objeto observado.  
- **Observer** → interessados que “escutam” mudanças no subject.  

### Exemplo em Java
```java
// Interface Observer
public interface Observer {
    void update(String message);
}

// Subject
import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }
}

// Concrete Observer
public class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name + " recebeu: " + message);
    }
}

// Uso
public class Main {
    public static void main(String[] args) {
        Publisher publisher = new Publisher();

        Subscriber s1 = new Subscriber("Alice");
        Subscriber s2 = new Subscriber("Bob");

        publisher.addObserver(s1);
        publisher.addObserver(s2);

        publisher.notifyObservers("Novo artigo publicado!");
    }
}
```

➡️ Saída:
```
Alice recebeu: Novo artigo publicado!
Bob recebeu: Novo artigo publicado!
```

Esse padrão é amplamente usado em GUIs, sistemas de eventos e até no **Spring** com `ApplicationEventPublisher`.

---

## ✅ Conclusão

- **Padrões de Projeto** são essenciais para criar sistemas **flexíveis e reutilizáveis**.  
- Eles ajudam tanto no **desenvolvimento próprio** quanto na **compreensão de sistemas de terceiros**.  
- Os principais grupos são: **Criacionais, Estruturais e Comportamentais**.  
- Exemplos práticos: `Factory`, `Singleton`, `Observer`.

> ✨ Dominar esses padrões significa escrever código mais **organizado**, **escalável** e **fácil de manter**.





