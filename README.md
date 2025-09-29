# O que é abstração:

Abstração é o processo de representar elementos do mundo real no software. Por exemplo, em Java, utilizamos classes no pacote entity para representar entidades que refletem os dados reais armazenados no banco de dados.

# Princípios de projeto:

Projetar bem significa reduzir complexidade com **padrões e consistência**. Use convenções previsíveis, divida responsabilidades e mantenha o código limpo para evitar a “bola de lama”. Boas decisões no início reduzem custo de manutenção e facilitam colaboração.

# Seguir um padrão:

Padrão é o “acordo” que mantém o sistema compreensível. Nomes, camadas e estilos de código consistentes permitem que qualquer pessoa navegue e evolua o projeto com segurança. Sem padrão, o código degrada rapidamente.

# Arquitetura em camadas (ENTITY, REPOSITORY, SERVICE, CONTROLLER, CONFIG):

Separar por camadas organiza responsabilidades e facilita troca de implementações.

* **ENTITY**: modelos do domínio (dados).
* **REPOSITORY**: acesso ao banco.
* **SERVICE**: regra de negócio.
* **CONTROLLER**: expõe API (REST).
* **CONFIG**: configurações do framework/aplicação.
  **Dica:** testar serviços sem depender de controller/banco acelera feedback.

# Boas práticas de código:

Código legível é ativo estratégico. Em Java, **classes** iniciam com **maiúscula** (ex.: `UserService`) e variáveis/métodos em **camelCase** (ex.: `totalAmount`, `findAll()`). Reaproveite frameworks maduros em vez de “reinventar a roda”.

# Encapsulamento (ocultamento de informação):

Encapsular é **esconder detalhes internos** e expor apenas o essencial. Em Java, atributos `private` + getters/setters controlam acesso; quem usa a classe não precisa conhecer sua implementação. Em frameworks, interagimos via contratos públicos e abstrações.

# Colaboração e organização de código:

Quando o software é bem modularizado, **mais pessoas** conseguem trabalhar sem conflitos. Separar regra em arquivos/coisas coesas melhora manutenção, testes e leitura.

# Débito técnico:

É a “conta” por soluções rápidas/ruins. Funciona hoje, custa caro amanhã: manutenção difícil, efeito dominó e risco de regressões. Administrar e pagar essa dívida cedo evita paralisia do sistema.

# Facilidade de entendimento (onboarding):

Um código claro permite que um novo dev entenda rápido **sem estudar o sistema inteiro**. Manutenção diária é escola: construir “casca” na base do código real acelera a evolução profissional.

# Coesão:

Coesão é **fazer uma coisa bem feita** por unidade (classe/método). Ao olhar um `Controller`, deve haver apenas lógica de controle/API; ao olhar um `Service`, apenas regra de negócio. Coesão alta facilita testes e mudanças locais.

# Classe anêmica:

É uma classe “só dados”: atributos + getters/setters. Não é “certo” ou “errado”, mas desloca regra de negócio para outros lugares. Use conscientemente: às vezes serve como **DTO/Entity**; evite esvaziar o domínio quando precisa de comportamento.

# Acoplamento:

Acoplamento é **o quanto uma parte depende de outra**. Se `A` cria/usa diretamente `B`, mudanças em `B` podem quebrar `A`. Busque **baixo acoplamento** com interfaces, injeção de dependência e composição.

```java
// Exemplo simples (alto acoplamento)
class A {
    private B b;
    public A() {
        this.b = new B();
        b.doWork();
    }
}
```

# Injeção de dependência (@Autowired):

No Spring, o controlador depende de **abstrações** (interfaces), não de classes concretas. O container encontra quem implementa e injeta (ex.: `@Autowired`). Isso reduz acoplamento, melhora testabilidade e permite trocar implementações sem quebrar a interface.

```java
public interface UserService {
    List<User> getAll();
}

@RestController
class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }
    @GetMapping("/users")
    public List<User> list() { return service.getAll(); }
}
```

# Notação UML essencial:

* **Herança**: setinha com **ponta vazia**, linha contínua.
* **Implementação (interface)**: setinha com **ponta vazia**, linha **pontilhada**.
* **Associação**: setinha **preenchida** (usar mais quando modela relações).
  **Dica:** use herança somente quando o filho **é um** tipo do pai e **não vira** outro filho.

# Visão geral de SOLID:

Criado por Robert C. Martin, SOLID resume **boas práticas de OO**. Aplicar SOLID é usar orientação a objetos do jeito certo, favorecendo coesão alta, acoplamento baixo e extensibilidade sem quebrar o que funciona.

# S — Princípio da Responsabilidade Única:

Cada classe deve ter **uma responsabilidade clara**. Isso conversa com coesão: separar coisas diferentes em lugares diferentes reduz bugs e efeitos colaterais.

# O — Aberto/Fechado:

Módulos **abertos para extensão** e **fechados para modificação**. Adicione comportamentos via novas classes/implementações em vez de editar código estável (menos bugs).

# L — Substituição de Liskov:

Se há herança, **qualquer filho** deve poder substituir o pai **sem quebrar** o código cliente. Em UI (ex.: `Border` no Swing), qualquer implementação concreta deve funcionar onde o tipo pai é esperado.

# I — Segregação de Interfaces:

Classes não devem depender de métodos que **não usam**. Prefira **interfaces menores e específicas** (ex.: `Clickable`, `Hoverable`) a interfaces “deus” com responsabilidades demais.

# D — Inversão de Dependência:

Dependa de **abstrações** (interfaces), não de implementações concretas. O Spring injeta a implementação, preservando o contrato. Isso torna módulos **plugáveis** e testáveis.

# MVC (Model–View–Controller):

* **View**: HTML/UI.
* **Model**: representação dos dados (não confundir com Entity do banco).
* **Controller**: orquestra requisições entre View e Model.
  **Dica:** mantenha regra de negócio no **Service**, não no Controller.

# Swing, eventos e contratos:

Em UI, “interface” é contrato de eventos. Quem quiser “ouvir” cliques implementa o método esperado.

```java
package br.univille;

import javax.swing.*;
import java.awt.event.*;

public class Janelinha extends JFrame {
    private JButton button;
    private Controlador controlador;

    public Janelinha() {
        setTitle("Exemplo");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button = new JButton("Clique");
        controlador = new Controlador();
        button.addActionListener(controlador);

        add(button);
        setVisible(true);
    }

    public static void main(String[] args) { new Janelinha(); }
}
```

**Dica:** classes anônimas são úteis para handlers pequenos; para regras maiores, prefira classes nomeadas.

# Composição vs. herança:

Prefira **composição**: você “monta” objetos a partir de outros. Herança é forte, “vaza” detalhes do pai e pode violar encapsulamento. Use herança quando **sempre** fizer sentido (ex.: `Animal` → `Cachorro`/`Gato`, um **não vira** o outro).

# Princípio de Demeter (menor conhecimento):

Um método deve conversar só com:

* a própria classe,
* objetos passados por parâmetro,
* objetos criados nele,
* atributos diretos.
  Evite “correntes de chamadas” longas (acoplamento ao mundo externo).

# Classe, interface e classe abstrata:

* **Classe**: possui atributos e métodos (pode ser instanciada).
* **Interface**: contrato de métodos (sem implementação).
* **Classe abstrata**: mistura esqueleto pronto com partes a completar (útil para compartilhar comportamento + forçar contratos).

# Por que padrões de projeto ajudam:

Padrões dão **linguagem comum** e **soluções testadas** para problemas recorrentes:

* Ao **implantar seu sistema**, você reaproveita soluções maduras.
* Ao **usar libs de terceiros**, você entende melhor sua estrutura/comportamento por reconhecer os padrões aplicados.
  *Design for change*: projete antecipando mudanças; sem isso, virá um reprojeto caro.

# Catálogo GoF (resumo):

* **Criacionais**: criação flexível de objetos (Abstract Factory, Factory Method, **Singleton**, **Builder**, Prototype).
* **Estruturais**: composição flexível (Proxy, Adapter, Facade, Decorator, Bridge, Composite, Flyweight).
* **Comportamentais**: interação/divisão de responsabilidades (Strategy, Observer, Template Method, Visitor, Chain of Responsibility, Command, Interpreter, Iterator, Mediator, Memento, State).

# Padrão Fábrica (exemplo prático):

Parametrize a instanciação para trocar o protocolo sem espalhar `new`.

```java
interface Channel {}
class TCPChannel implements Channel {}
class UDPChannel implements Channel {}

class ChannelFactory {
    public static Channel create() { // método fábrica estático
        return new TCPChannel();
    }
}

void f() { Channel c = ChannelFactory.create(); /* ... */ }
void g() { Channel c = ChannelFactory.create(); /* ... */ }
void h() { Channel c = ChannelFactory.create(); /* ... */ }
```

**Dica:** um “aspirador de `new`”: concentra a criação num ponto único.

# Fábrica Abstrata:

Agrupe **métodos fábrica relacionados** numa abstração.

```java
abstract class ProtocolFactory {
    abstract Channel createChannel();
    abstract Port createPort();
}

class TCPProtocolFactory extends ProtocolFactory {
    Channel createChannel() { return new TCPChannel(); }
    Port createPort() { return new TCPPort(); }
}

void f(ProtocolFactory pf) {
    Channel c = pf.createChannel();
    Port p = pf.createPort();
}
```

# Singleton (contexto):

Quando um recurso deve ter **uma única instância** (ex.: `Logger`), centralizar o acesso evita múltiplas criações dispersas.
**Dica:** em apps modernos/Spring, prefira **escopo de bean singleton** e injeção de dependência a implementar Singleton manual.

# Características arquiteturais (não-funcionais):

Disponibilidade, escalabilidade, segurança, desempenho etc. É impossível otimizar **tudo** ao mesmo tempo; priorize conforme o contexto (ex.: redes sociais priorizam escala, às vezes abrindo mão de consistência forte).

# Segurança e bibliotecas abertas:

Dependências OSS podem estar **desatualizadas ou comprometidas**. Prática saudável: **DevSecOps** com scanners de vulnerabilidade em pipeline para evitar importar riscos.

# Decisões arquiteturais:

São **regras guardrails** para manter organização (ex.: camadas, mensageria, estilo). Dá para combinar estilos, mas **sempre tenha motivo** e documente trade-offs.

# Monolito em camadas:

Um único deploy com separação interna por camadas. **Vantagens**: simplicidade, menos latência entre módulos, desenvolvimento mais direto. **Cuidados**: modularidade interna forte para evitar “bola de lama”.

# Microserviços e mensageria:

Cada serviço com **banco próprio**, comunicação preferencialmente **assíncrona** via broker (ex.: RabbitMQ, SNS/SQS, Service Bus). Ganho de isolamento e escalabilidade por serviço; custo em observabilidade, coordenação e consistência.

# Tomar decisões de arquitetura:

O arquiteto **orienta escolhas** de stack (ex.: Angular vs React) com experiência e consultas técnicas. A decisão impacta o produto a longo prazo; busque evidências e envolva quem implementa.

# Analisar continuamente a arquitetura:

Sistemas mudam sempre. Vitalidade arquitetural depende de **revisões constantes**: remover entraves, reduzir dívida técnica e ajustar padrões conforme aprendizado.

# Manter-se atualizado (tendências):

Linguagens e plataformas evoluem rápido. Nem sempre dá para “ir no último” (ex.: legado amplo), mas o arquiteto precisa **mapear impactos** e planejar atualizações graduais.

# Assegurar conformidade (políticas e estática de código):

Padronize e **verifique automaticamente** (linters, análise estática, políticas de PR). Isso evita violações como acesso a banco no `Controller` ou quebras de camadas.

# Exposição e experiência diversificadas:

Avaliar arquitetura exige visão de **frontend, backend, dados, infraestrutura** e domínio de negócio. Quanto mais repertório, melhores os trade-offs.

# Habilidades interpessoais e políticas:

Arquitetura é **tanto técnica quanto humana**: liderar, negociar escopo e prazos, alinhar expectativas e manter todos na mesma direção.

# DevOps (cultura e prática):

Objetivo: **entregar valor mais rápido** com qualidade.

* Planejar → Criar → **Integração Contínua** → **Entrega Contínua** → Monitorar → Feedback.
* Em cultura DevOps, todos se responsabilizam por incidentes e melhoria contínua.

# Pensamento arquitetônico:

Arquitetos pensam diferente: conectam **negócio, tecnologia e trade-offs**. Entendem onde a **arquitetura** termina e o **design** começa — na prática, eles coexistem e evoluem juntos.

# Arquitetura vs. design:

Separar papéis não pode virar **muro** entre arquitetos e devs. Precisa haver **mão dupla**: decisões arquiteturais informam o código e o código realimenta a arquitetura.

# Amplitude técnica (pirâmide de conhecimento):

Para arquitetos, **amplitude > profundidade**. É melhor conhecer várias soluções possíveis do que dominar apenas uma. A especialização selecionada permanece; o restante amplia repertório para decisões melhores.

# Antipadrão “Homem das Cavernas Congelado”:

Focar demais num trauma passado leva a decisões **desproporcionais**. Avalie riscos reais vs. percebidos e evite paralisar escolhas por medos antigos.

# Trade-offs (compensações):

Toda arquitetura envolve **trocas**. Não há resposta universal; há opções com custos/benefícios diferentes conforme o problema.

# Exemplo de leilão (database-centric vs eventos):

Banco central único (insert/select) funciona para **volume baixo**. Em larga escala (estilo marketplace), tende a **saturar**: considerar eventos, processamento assíncrono e desacoplamento.

# Arquitetura baseada em tópicos (pub/sub):

**1 → muitos**: um publisher publica em um **tópico**; todos os subscribers recebem.

* **Prós**: desacoplamento, fácil adicionar consumidores, difusão rápida (parecido com Observer).
* **Contras**: mensagens “genéricas” (podem carregar dados desnecessários/sensíveis), risco de perda em brokers simples, custo por payload.
  **Exemplos**: RabbitMQ (tópicos), SNS, Service Bus, Redis; protocolos AMQP/MQTT/WebSocket/HTTP.

# Arquitetura de filas (enqueue/dequeue):

**1 → 1** com **ordem garantida** e buffer durável.

* **Prós**: resiliência (consumidor caiu, volta e lê), observabilidade por fila, mensagens **específicas** por consumidor.
* **Contras**: mais impacto ao adicionar novos consumidores (nova fila), mais coordenação.
  **Exemplos**: RabbitMQ (filas), AWS SQS; padrão FIFO e polling comuns.

# Fan-out (tópico + cópia em filas):

Combina **desacoplamento do tópico** com **durabilidade/isolamento da fila**: publica uma vez, replica para múltiplas filas, cada consumidor lê no seu ritmo.

# Factory Method aplicado a protocolos (exemplo):

Trocar TCP por UDP sem varrer o código inteiro.

```java
interface Channel {}
class TCPChannel implements Channel {}
class UDPChannel implements Channel {}

class ChannelFactory {
    public static Channel create(String kind) {
        return "UDP".equalsIgnoreCase(kind) ? new UDPChannel() : new TCPChannel();
    }
}
```

# Observações finais sobre assíncrono:

Assíncrono **não bloqueia** a aplicação: processa eventos, escala horizontalmente e resiste a picos. Em mobile (Android), evitar bloqueio de UI é requisito básico para boa UX.
