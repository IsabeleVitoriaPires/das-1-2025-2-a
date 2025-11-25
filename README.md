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
 
# 2 bimestre

## Circuit Breaker (Disjuntor)

O Circuit Breaker é basicamente um "fusível inteligente" para aplicações distribuídas. Quando você tem um serviço que está falhando constantemente, não faz sentido ficar tentando se conectar nele toda hora, né? É aí que entra esse padrão.

### Como funciona na prática

O Circuit Breaker atua como um proxy que fica monitorando as requisições e tem três estados principais:

**Closed (Fechado)**: Tudo funcionando normal. As requisições passam direto pro serviço. Se começar a dar erro, ele conta quantas falhas estão acontecendo. Quando ultrapassa um limite configurado, ele muda pro estado Open.

**Open (Aberto)**: Aqui o circuit breaker bloqueia completamente as requisições e retorna erro imediatamente, sem nem tentar chamar o serviço. Isso evita sobrecarregar ainda mais um serviço que já tá com problema. Fica assim por um tempo configurado (timeout).

**Half-Open (Meio Aberto)**: Depois que o timeout expira, ele deixa passar algumas poucas requisições de teste. Se essas requisições funcionarem, ele assume que o problema foi resolvido e volta pro estado Closed. Se continuar falhando, volta pro Open e reinicia o timer.

### Por que usar?

A grande vantagem é **estabilidade**. Imagina que você tem um e-commerce e o serviço de pagamento caiu. Sem circuit breaker, todas as requisições ficariam travadas esperando timeout, consumindo threads, memória, conexões de banco... Isso pode derrubar o sistema inteiro (falha em cascata). Com o circuit breaker, você falha rápido, libera recursos e pode até retornar uma resposta alternativa pro usuário tipo "tente novamente em alguns minutos".

### Exemplo

No exemplo da Microsoft, eles usam Circuit Breaker com Azure Cosmos DB no tier gratuito (que tem limite de requisições). Quando começa a retornar erro 429 (muitas requisições), o circuit breaker abre e a aplicação passa a servir dados em cache ou respostas default, mantendo a experiência do usuário sem derrubar tudo.

---

## Implementação de Filas: Producer / Consumer

O padrão Producer/Consumer com filas é sobre **desacoplar quem produz trabalho de quem processa**. É tipo uma esteira de fábrica: quem produz joga na esteira e segue sua vida, quem consome pega da esteira quando pode.

### Arquitetura básica

**Producer (Produtor)**: Quem gera as mensagens/tarefas e coloca na fila. Pode ser uma API que recebe upload de arquivos, um sistema que processa pedidos, etc.

**Fila**: O buffer durável que armazena as mensagens. Garante que nada se perde se o consumer cair.

**Consumer (Consumidor)**: Quem processa as mensagens. Fica fazendo polling (consultando) a fila, pega uma mensagem, processa e remove ela da fila.

### Características principais

**1 → 1**: Uma mensagem é processada por **um único** consumer. Se você tem múltiplos consumers, eles dividem o trabalho (não duplicam).

**Ordem garantida**: Geralmente FIFO (First In, First Out). A primeira mensagem que entrou é a primeira a sair.

**Durabilidade**: Se o consumer cair no meio do processamento, a mensagem volta pra fila e outro consumer pode pegar.

**Resiliência**: Producer e consumer podem estar em ritmos completamente diferentes. O producer pode estar enviando milhares de mensagens por segundo enquanto o consumer processa devagar. A fila absorve esse "pico".

### Quando usar

- **Processamento assíncrono**: Envio de emails, geração de relatórios, processamento de imagens.
- **Sistemas com cargas variáveis**: Black Friday de e-commerce - a fila segura o tranco.
- **Desacoplamento**: O producer nem precisa saber quem vai processar, só joga na fila.
- **Retry automático**: Se falhar, a mensagem volta pra fila e tenta de novo.

## Definições das Características Arquiteturais

Quando a gente pensa em desenvolver um sistema, geralmente foca nos **requisitos funcionais** - o que o sistema deve fazer. Mas tem toda uma outra camada de preocupações que são igualmente (ou mais) importantes: as **características arquiteturais**.

### O que são características arquiteturais?

São todos aqueles aspectos do sistema que **não estão diretamente relacionados à funcionalidade**, mas são essenciais pro sucesso da aplicação. Antigamente chamavam isso de "requisitos não funcionais", mas é um nome péssimo - parece que não é importante, quando na verdade é crítico.

Pra ser considerada uma característica arquitetural, precisa atender três critérios:

1. **Especifica algo fora do domínio**: Não é sobre "o sistema deve cadastrar usuários", mas sim sobre "o sistema deve responder em menos de 200ms".

2. **Influencia a estrutura do design**: Precisa de uma consideração estrutural especial. Por exemplo, se você vai processar pagamentos na própria aplicação, provavelmente vai precisar de um módulo/serviço isolado pra isso. Se for terceirizado, talvez não precise.

3. **É essencial pro sucesso**: Nem toda característica precisa ser suportada. Cada uma adiciona complexidade, então o arquiteto deve escolher **as menos possíveis**, não o máximo.

### Implícitas vs. Explícitas

**Implícitas**: Raramente aparecem nos requisitos, mas são óbvias. Tipo disponibilidade, confiabilidade, segurança. Todo mundo assume que o sistema precisa ser seguro, mas ninguém escreve isso explicitamente.

**Explícitas**: Vêm descritas nos documentos de requisitos. "O sistema deve suportar 10.000 usuários simultâneos" é explícito.

### Principais categorias

**Características Operacionais**: São sobre como o sistema roda no dia a dia.

- **Disponibilidade**: O sistema precisa estar no ar 24/7? Se cair, quanto tempo pode ficar fora?
- **Escalabilidade**: Consegue lidar com mais usuários/requisições conforme cresce?
- **Desempenho**: Tempos de resposta, throughput, capacidade sob carga.
- **Confiabilidade**: Se falhar, o impacto é crítico (tipo sistema hospitalar)?
- **Recuperabilidade**: Se der ruim, quanto tempo pra voltar ao normal?

**Características Estruturais**: Sobre a qualidade do código e manutenibilidade.

- **Manutenção**: Quão fácil é fazer mudanças e melhorias?
- **Extensibilidade**: Consegue adicionar novas funcionalidades sem quebrar tudo?
- **Modularidade/Reusabilidade**: O código é bem organizado e reutilizável?
- **Testabilidade**: É fácil testar? Tem boa cobertura?

**Características Transversais**: Questões que atravessam o sistema todo.

- **Segurança**: Criptografia, autenticação, autorização, proteção de dados.
- **Acessibilidade**: Sistema funciona pra pessoas com deficiências?
- **Legalidade**: LGPD, GDPR, SOX - conformidade com regulações.
- **Usabilidade**: Quão fácil é pro usuário aprender a usar?

### O problema das definições

Infelizmente, não existe um padrão universal pra essas características. Cada empresa define os termos do seu jeito, e muitos conceitos se sobrepõem ou são ambíguos. O que pode ser melhorado é estabelecer uma **linguagem universal** no time (conceito do DDD) pra todo mundo estar na mesma página.

---

## CQRS (Command Query Responsibility Segregation)

CQRS é um padrão que **separa as operações de leitura (queries) das operações de escrita (commands)** em modelos de dados distintos. A ideia é: otimizar cada lado independentemente.

### O problema que resolve

Na arquitetura tradicional (CRUD), você usa o mesmo modelo de dados pra ler e escrever. Isso funciona bem pra aplicações simples, mas conforme o sistema cresce, aparecem problemas:

**Requisitos diferentes**: Leitura e escrita têm necessidades completamente distintas. Queries geralmente precisam de dados denormalizados, joins complexos, agregações. Já commands precisam de validações de negócio, transações, consistência.

**Contenção de locks**: Quando você tem muitas operações simultâneas no mesmo modelo, começa a rolar disputa por locks no banco, travando tudo.

**Performance**: Queries complexas impactam a performance de writes e vice-versa. Um relatório pesado pode travar operações críticas de atualização.

**Segurança**: Fica mais difícil controlar permissões quando tudo tá misturado. Quem pode ler nem sempre pode escrever, mas no modelo unificado isso vira acaba não sendo o ideal.

### A solução: separe

CQRS divide tudo em dois lados:

**Lado de Command (Escrita)**:
- Recebe **comandos** que representam intenções de negócio: "ReservarQuartoHotel", "AprovarPedido", "CancelarAssinatura"
- Comandos não retornam dados, só executam ações
- Possui toda a lógica de negócio, validações e regras de domínio
- Otimizado pra transações e consistência
- Pode processar comandos de forma assíncrona (via fila)

**Lado de Query (Leitura)**:
- Retorna **DTOs** (Data Transfer Objects) - objetos simples sem lógica de negócio
- Otimizado pra performance de leitura
- Pode usar denormalização, views materializadas, cache
- Não modifica dados, nunca

### Níveis de implementação

**Nível 1: Modelos separados, mesmo banco**

Você mantém um único banco de dados, mas usa modelos distintos pra read e write. É o CQRS mais simples:
- Lado de escrita usa entidades de domínio ricas com validações
- Lado de leitura usa queries diretas que retornam DTOs
- Não precisa sincronizar nada, porque tá tudo no mesmo banco

**Nível 2: Bancos de dados separados**

A implementação mais avançada usa bancos diferentes:
- **Write DB**: Relacional normalizado (PostgreSQL, SQL Server) pra garantir consistência
- **Read DB**: Pode ser um banco otimizado pra leitura (MongoDB, Elasticsearch, Redis)
- Escala cada lado independentemente
- **Trade-off**: Precisa sincronizar os bancos (geralmente via eventos)

### Combinando com Event Sourcing

CQRS casa muito bem com Event Sourcing. Ao invés de salvar o estado atual, você salva **eventos** que representam mudanças:

```
PedidoCriado → ItemAdicionado → ItemRemovido → PedidoFinalizado
```

- **Write side**: Gera eventos e salva no event store
- **Read side**: Consome eventos e constrói views materializadas otimizadas
- Você pode recriar qualquer view a qualquer momento só reprocessando os eventos

### Benefícios na prática

**Escalabilidade independente**: Se você tem 90% de leituras e 10% de escritas (cenário comum), pode escalar só o lado de leitura com múltiplas réplicas.

**Performance otimizada**: O lado de leitura pode ter índices agressivos, cache, denormalização total. O lado de escrita foca em consistência e transações.

**Segurança granular**: É trivial garantir que apenas domínios específicos possam escrever dados, enquanto leituras são mais abertas.

**Queries simples**: Ao invés de joins complexos com 5 tabelas, você pode ter uma view materializada com tudo pronto.

**Evolução independente**: Times diferentes podem trabalhar em cada lado sem conflito.

### Quando usar CQRS

**Use quando**:
- Você tem **alta carga de leitura** comparada com escrita (tipo 10:1 ou mais)
- **Ambientes colaborativos** onde vários usuários modificam dados simultaneamente
- **Interfaces task-based** (comandos de negócio) ao invés de CRUD simples
- Sistemas com **regras de negócio complexas** no lado de escrita
- Você precisa de **performance de leitura extrema**
- Times separados trabalhando em diferentes partes do sistema

**Não use quando**:
- Seu domínio é simples (CRUD básico resolve)
- Regras de negócio são triviais
- Você não tem problema de performance
- A complexidade adicional não se justifica

### Cuidados e desafios

**Consistência eventual**: Se você usa bancos separados, o lado de leitura pode ficar "atrasado". O usuário faz uma compra e pode não ver ela imediatamente na lista de pedidos. Precisa lidar com isso na UI.

**Complexidade**: CQRS adiciona camadas, sincronização, mensageria. Só vale a pena se os benefícios compensarem.

**Mensageria**: Geralmente você usa filas pra processar comandos e eventos. Precisa lidar com falhas, duplicatas, retries.

**Sincronização**: Manter read e write DBs sincronizados é desafiador. Eventos podem chegar fora de ordem, conexões podem cair, precisa de idempotência.

## Retry Pattern (Padrão de Retentativa)

O Retry Pattern é sobre **tentar de novo automaticamente** quando uma operação falha devido a problemas temporários. É um dos padrões mais importantes quando você trabalha com sistemas distribuídos e serviços na nuvem.

### O problema: falhas transitórias

Em sistemas distribuídos, falhas temporárias são **esperadas e normais**. Não são bugs, são características do ambiente:

- **Perda momentânea de conexão**: Rede oscilou por 2 segundos
- **Serviço temporariamente indisponível**: Está reiniciando ou fazendo deploy
- **Timeouts**: Serviço tá ocupado processando muitas requisições
- **Throttling**: Você ultrapassou o rate limit e foi temporariamente bloqueado

A característica dessas falhas é que elas **se corrigem sozinhas**. Se você tentar de novo depois de alguns segundos, provavelmente vai funcionar.

### A solução: retry inteligente

Ao invés de falhar na primeira tentativa e jogar a responsabilidade pro usuário, você implementa uma lógica que automaticamente tenta novamente seguindo estratégias específicas.

### Estratégias de retry

**1. Cancel (Cancelar imediatamente)**

Se a falha claramente não é transitória, cancela e reporta erro. Exemplos:
- Credenciais inválidas (401)
- Recurso não encontrado (404)
- Bad request (400)
- Erro de lógica de negócio

Não adianta tentar de novo, só vai dar erro de novo.

**2. Retry Immediately (Tentar imediatamente)**

Pra falhas muito raras e incomuns, tipo um pacote de rede que corrompeu. Você tenta de novo na hora, sem delay.

Raramente usado porque a maioria das falhas precisa de tempo pra se resolver.

**3. Retry After Delay (Tentar após um delay)**

A estratégia mais comum. Você espera um tempo antes de tentar novamente. Existem várias abordagens pro delay:

**Linear**: Delay fixo entre tentativas (ex: sempre 2 segundos)
```
Tentativa 1 → falha → espera 2s
Tentativa 2 → falha → espera 2s
Tentativa 3 → falha → espera 2s
```

**Exponential Backoff**: Delay aumenta exponencialmente (muito usado!)
```
Tentativa 1 → falha → espera 1s
Tentativa 2 → falha → espera 2s
Tentativa 3 → falha → espera 4s
Tentativa 4 → falha → espera 8s
```

**Exponential Backoff + Jitter**: Adiciona aleatoriedade pra evitar que múltiplos clientes tentem ao mesmo tempo (thundering herd)
```
Tentativa 1 → falha → espera 1s + random(0-500ms)
Tentativa 2 → falha → espera 2s + random(0-1s)
Tentativa 3 → falha → espera 4s + random(0-2s)
```

## Fundamentos dos Padrões de Arquitetura

Os estilos de arquitetura (ou padrões de arquitetura) são como "receitas nomeadas" que descrevem como organizar os componentes de um sistema. Quando um arquiteto fala "vamos usar um monolito em camadas" ou "isso é microsserviços", todo mundo que conhece esses padrões já entende um monte de coisas: estrutura, trade-offs, problemas comuns, estratégias de dados, etc.

### O Anti-Padrão: Grande Bola de Lama

Antes de falar dos padrões bons, tem que conhecer o pior de todos: a **Grande Bola de Lama** (Big Ball of Mud).

É exatamente o que parece: código espaguete, sem estrutura nenhuma, onde tudo tá acoplado com tudo. Características:
- Sem separação de responsabilidades
- Informação global espalhada por todo lado
- Mudanças imprevisíveis (mexe aqui, quebra lá)
- Difícil de entender, manter, testar e escalar

**Como acontece**: Geralmente começa com um script simples que vai crescendo sem planejamento. Sem governança de código e arquitetura, vira uma bagunça que ninguém quer mexer.

### Evolução Histórica: De Unitário a Distribuído

**Arquitetura Unitária**: Tudo roda em uma única máquina. Comum em sistemas embarcados. Raro hoje em sistemas comerciais porque sistemas tendem a crescer e precisam separar preocupações.

**Cliente/Servidor (2 camadas)**: A primeira grande separação.

*Desktop + Banco de Dados*: Anos 90, aplicações Windows pesadas no desktop, dados no servidor de banco.

*Browser + Web Server*: Com a web, browsers magros + servidor web (que conecta no banco). Ainda é 2 camadas porque web server e banco geralmente rodavam no mesmo datacenter.

**Três Camadas**: Virou moda no final dos anos 90.
- **Camada de Apresentação**: Browser com HTML/JavaScript
- **Camada de Aplicação**: Servidor de aplicação (Java EE, .NET)
- **Camada de Dados**: Banco de dados

Tecnologias como CORBA e DCOM facilitavam essa distribuição. Essa arquitetura moldou até o design de linguagens - Java tem serialização embutida porque os designers achavam que 3 camadas seria pra sempre!

### Classificação Principal: Monolítico vs Distribuído

**Monolítico**: Uma única unidade de implementação. Todo o código deployado junto.
- Arquitetura em Camadas
- Arquitetura de Pipeline
- Arquitetura de Microkernel

**Distribuído**: Múltiplas unidades de implementação conectadas por rede.
- Arquitetura Baseada em Serviços
- Arquitetura Orientada a Eventos
- Arquitetura Baseada em Espaços
- Arquitetura Orientada a Serviços (SOA)
- Arquitetura de Microsserviços

Arquiteturas distribuídas têm vantagens (performance, escalabilidade, disponibilidade), mas vêm com **trade-offs significativos** descritos nas 8 Falácias da Computação Distribuída.

### As 8 Falácias da Computação Distribuída

Criadas por L. Peter Deutsch na Sun Microsystems. São coisas que desenvolvedores **pressupõem** serem verdade, mas **não são**.

**Falácia 1: A rede é confiável**

 **Realidade**: A rede **não** é confiável. Mesmo com melhorias, falhas acontecem.

**Impacto**: Serviços podem estar saudáveis mas inacessíveis. Requisições podem se perder. Por isso existem timeouts e circuit breakers.

Quanto mais distribuído o sistema (tipo microsserviços), mais você depende da rede, logo menos confiável fica.

**Falácia 2: Latência é zero**

 **Realidade**: Latência **nunca** é zero.

**Impacto**: 
- Chamada local (método Java): nanossegundos
- Chamada remota (REST, gRPC): milissegundos

Se você encadeia 10 chamadas de serviço com 100ms cada = **1 segundo** adicional!

**Falácia 3: Largura de banda é infinita**

 **Realidade**: Largura de banda é limitada e cara.

**Impacto - Exemplo prático**:
- Serviço de Lista de Desejos chama Serviço de Perfil pra pegar nome do cliente
- Perfil retorna 500KB (45 atributos), mas só precisa do nome (200 bytes)
- Isso acontece 2000 vezes por segundo

**Falácia 4: A rede é segura**

 **Realidade**: A rede **não** é segura.

**Impacto**: Em monolito, você protege a borda. Em distribuído, **cada endpoint** precisa ser protegido.

A superfície de ataque aumenta exponencialmente. Cada comunicação entre serviços precisa autenticação/autorização, o que impacta performance.

**Falácia 5: A topologia nunca muda**

 **Realidade**: A topologia muda constantemente.

**Impacto - Cenário real**:
- Segunda de manhã, todos os serviços estão com timeout na produção
- Nada foi deployado no fim de semana
- Descoberta: upgrade "simples" na rede às 2h da manhã
- Resultado: Invalidou todas as suposições de latência, disparou circuit breakers

**Falácia 6: Existe apenas um administrador**

 **Realidade**: Existem **dezenas** de admins de rede.

**Impacto**: Com quem você fala sobre latência? Mudanças de topologia? Aumentar largura de banda?

Arquiteturas distribuídas exigem coordenação massiva entre múltiplas equipes. Monolitos não têm esse problema.

**Falácia 7: O custo do transporte é zero**

 **Realidade**: Chamadas remotas **custam dinheiro** real.

**Impacto**: Arquiteturas distribuídas precisam de:
- Mais hardware
- Mais servidores
- Gateways, firewalls, load balancers
- Novas sub-redes
- Proxies reversos
- Monitoramento mais sofisticado

Distribuir uma aplicação monolítica custa **muito mais** do que manter ela monolítica.

**Falácia 8: A rede é homogênea**

 **Realidade**: Redes usam hardware de **vários fornecedores**.

**Impacto**: Equipamento Cisco + Juniper + outros podem não se integrar perfeitamente. Pacotes podem se perder. Isso impacta:
- Confiabilidade (Falácia 1)
- Latência (Falácia 2)
- Largura de banda (Falácia 3)

### Quando Escolher Monolito vs Distribuído?

**Use Monolito quando**:
- Sistema pequeno/médio
- Time pequeno
- Requisitos simples
- Não precisa escalar partes independentemente
- Você quer deploy e debugging simples

**Use Distribuído quando**:
- Precisa escalar partes do sistema independentemente
- Times grandes em diferentes áreas
- Diferentes tecnologias por domínio
- Alta disponibilidade é crítica
- Domínio de negócio naturalmente separado

**Regra**: Comece monolítico. Distribua quando a **dor** de não distribuir for maior que a **complexidade** de distribuir.

---

## Estilo de Arquitetura em Camadas (Layered Architecture)

A arquitetura em camadas é o padrão mais comum e tradicional. É basicamente o "padrão" quando você não sabe por onde começar ou quando tem um projeto pequeno/médio. Também é conhecida como **n-tier architecture**.

### Por que é tão comum?

**Lei de Conway**: Organizações tendem a criar arquiteturas que refletem sua estrutura de comunicação.

Empresas geralmente têm:
- Devs de front-end
- Devs de back-end
- DBAs
- Especialistas em regras de negócio

Naturalmente, o sistema acaba com camadas que espelham esses times. É meio que o caminho de menor resistência.

### Topologia Básica

As camadas são organizadas **horizontalmente**, cada uma com responsabilidade específica:

**As 4 camadas clássicas**:

1. **Apresentação**: Interface do usuário, lógica de exibição (HTML, React, Angular)
2. **Negócio/Business**: Regras de negócio, validações, processamento
3. **Persistência**: Lógica de acesso a dados (DAOs, Repositories)
4. **Banco de Dados**: Armazenamento físico dos dados

Às vezes as camadas de Negócio e Persistência são combinadas (fica 3 camadas). Aplicações pequenas podem ter só 3 camadas; aplicações grandes podem ter 5 ou mais.

### Variantes de Implementação Física

**Variante 1 - Tudo junto menos o banco**:
- Apresentação + Negócio + Persistência = 1 deployment
- Banco de dados = separado

**Variante 2 - Front separado**:
- Apresentação = 1 deployment
- Negócio + Persistência = 1 deployment
- Banco de dados = separado

**Variante 3 - Tudo junto (incluindo banco)**:
- Todas as 4 camadas em 1 deployment
- Útil pra aplicações pequenas com banco embarcado (H2, SQLite)
- Comum em produtos on-premises

### Separação de Responsabilidades

Cada camada tem **uma** preocupação:

- **Apresentação**: Não sabe nem se importa de onde vêm os dados, só exibe
- **Negócio**: Não se importa como dados serão exibidos nem de onde vêm, só processa regras
- **Persistência**: Não se importa com regras ou exibição, só busca/salva dados
- **Banco**: Só armazena

**Trade-off**: Essa separação facilita especialização técnica mas **reduz agilidade**. Qualquer mudança de negócio precisa passar por todas as camadas.

### O Anti-Padrão Sinkhole

Um dos maiores problemas da arquitetura em camadas é o **Sinkhole** (sumidouro).

**O que é**: Requisições que passam por todas as camadas sem fazer **nada útil** em cada uma.

**Soluções**:
1. Considerar outro estilo de arquitetura
2. Abrir todas as camadas (mas perde isolamento)
3. Adicionar cache inteligente

### Quando Usar Arquitetura em Camadas?

**Use quando**:
- Aplicação pequena/média
- Orçamento apertado, prazo curto
- Time pequeno com skills tradicionais
- Você não tem certeza qual arquitetura usar ainda
- Sistema CRUD simples
- Não precisa de alta escalabilidade

**Não use quando**:
- Alta escalabilidade é requisito
- Precisa deployar partes independentemente
- Time grande em múltiplas áreas
- Sistema com muitas sinkholes (80%+)
- Precisa de alta performance
- DDD é importante (domínios complexos)

### Vantagens

**Simplicidade**: Fácil de entender e implementar
**Custo baixo**: Desenvolvimento rápido e barato
**Familiaridade**: Todo dev conhece
**Ponto de partida**: Bom pra quando você não tem certeza
**Separação de responsabilidades**: Clara divisão técnica
**Sem complexidade de rede**: Tudo local (monolito)

### Desvantagens

**Baixa agilidade**: Mudanças tocam várias camadas
**Deploy monolítico**: Qualquer mudança = redeploy completo
**Testabilidade complicada**: Teste de regressão é pesado
**Escalabilidade limitada**: Só escala como um bloco todo
**Performance média**: Camadas fechadas adicionam overhead
**Tolerância a falhas ruim**: Uma parte cai, tudo cai
**DDD não funciona bem**: Domínios espalhados por camadas

## Estilo de Arquitetura Pipeline (Pipes and Filters)

A arquitetura pipeline é um padrão fundamental que existe desde os primórdios da computação. Se você já usou o terminal Unix/Linux com comandos encadeados (tipo `cat arquivo.txt | grep "erro" | sort | uniq`), você já usou esse padrão sem saber!

### Conceito Central

A ideia é dividir o processamento em **etapas independentes** conectadas por **canais unidirecionais**. Cada etapa faz **uma coisa só**, e o resultado passa pro próximo.

É tipo uma linha de montagem de fábrica: cada estação faz uma tarefa específica e passa o produto pra frente.

### Pipeline vs Camadas: Qual a diferença?

**Pipeline**:
- Fluxo **unidirecional** de dados
- Processamento **sequencial** em etapas
- Cada filtro é **autônomo** e **stateless**
- Focado em **transformação de dados**

**Camadas**:
- Fluxo **bidirecional** (requisição desce, resposta sobe)
- Separação por **responsabilidade técnica**
- Camadas podem manter **estado**
- Focado em **organização arquitetural**

### Quando Usar Pipeline?

**Use quando**:
- Processamento de dados em etapas sequenciais
- ETL, EDI, processamento de streams
- Cada etapa faz uma transformação clara
- Você quer alta reutilização de componentes
- Processamento unidirecional
- Batch jobs, processamento de arquivos

**Não use quando**:
- Precisa de processamento bidirecional complexo
- Necessita manter estado entre etapas
- Alta escalabilidade é crítica
- Processamento paralelo intenso é necessário
- Sistema CRUD tradicional (use camadas)

## Estilo de Arquitetura Microkernel (Plugin Architecture)

A arquitetura microkernel, também chamada de **arquitetura de plug-in**, é um padrão antigo mas ainda muito relevante...

## Estilo de Arquitetura Microkernel (Plugin Architecture)

A arquitetura microkernel, também chamada de **arquitetura de plug-in**, é um padrão antigo mas ainda muito relevante. Foi criada há décadas e é usada em produtos como Eclipse, Chrome, Firefox, VS Code, Jenkins, e muitos softwares comerciais.

### Conceito Central

A ideia é separar a aplicação em **duas partes**:

1. **Sistema Central (Core System)**: Funcionalidade mínima e estável
2. **Componentes de Plug-in**: Funcionalidades específicas, customizações, extensões

É tipo ter um motor básico que funciona sozinho, mas você vai adicionando peças (plug-ins) pra fazer coisas específicas.

### Sistema Central (Core)

O sistema central é a **funcionalidade mínima** necessária pra rodar o sistema.

**Definições do que é "mínimo"**:

**1. Definição literal**: O que você **precisa** pra funcionar
- Eclipse: Editor de texto básico (abrir, editar, salvar)
- Chrome: Navegador web básico (mostrar páginas HTML)

**2. Definição pelo "caminho feliz"**: O fluxo principal sem customizações
- Processar pedido padrão (sem regras especiais)
- Calcular imposto básico (sem casos específicos)

**Por que separar**: Remove complexidade ciclomática do core, facilita manutenção e permite extensibilidade.

### Quando Usar

**Use quando**:
- Software baseado em produto
- Alta customização por cliente/localização
- Funcionalidades adicionadas/removidas dinamicamente
- Isolar código volátil

**Não use quando**:
- Sistema CRUD simples
- Regras uniformes sem variação
- Precisa de alta escalabilidade (é monolítico)

### Vantagens vs Desvantagens

**Vantagens**:
- Extensibilidade alta
- Isolamento de mudanças
- Testabilidade (plug-ins isolados)
- Customização fácil
- Simplicidade e custo baixo

**Desvantagens**:
- Escalabilidade limitada (monolítico)
- Elasticidade ruim
- Tolerância a falhas fraca
- Single quantum (tudo passa pelo core)

## Estilo de Arquitetura de Microsserviços

Microsserviços é um dos estilos mais populares dos últimos anos, mas também um dos mais mal compreendidos. Foi nomeado e popularizado por Martin Fowler e James Lewis em 2014.

### Filosofia Central: Contexto Delimitado (DDD)

Microsserviços são fortemente inspirados por **Domain-Driven Design (DDD)**, especialmente o conceito de **Bounded Context** (Contexto Delimitado).

**Contexto Delimitado**: Cada serviço encapsula completamente um domínio/workflow, incluindo código, dados, e tudo que precisa pra operar independentemente.

**Trade-off fundamental**:
- **Reutilização** → gera **acoplamento**
- **Desacoplamento** → requer **duplicação**

Microsserviços escolhem **desacoplamento extremo**, então aceitam duplicação. Se dois serviços precisam da classe `Address`, cada um tem sua própria cópia, não compartilham.

### Características Principais

**1. Distribuído**
- Cada serviço roda em seu próprio processo (container, VM, etc)
- Independência operacional total
- Problemas: latência de rede, segurança em cada endpoint

**2. Altamente Desacoplado**
- Cada serviço = 1 domínio ou 1 workflow
- Serviços não compartilham classes, bancos, infraestrutura
- Comunicação apenas via rede (REST, mensageria)

**3. Banco de Dados Isolado**
- Cada serviço tem seu próprio banco
- Não há banco compartilhado
- Dados podem ser duplicados entre serviços
- Trade-off: complexidade vs desacoplamento

**4. Single Purpose (Finalidade Única)**
- Cada serviço faz UMA coisa
- Serviços muito menores que em SOA

### Granularidade: O Grande Desafio

**O termo "microsserviço" é um rótulo, não uma descrição** - Martin Fowler

Não significa "serviços minúsculos". Significa serviços com **escopo bem definido**.

**Como encontrar granularidade certa**:

**1. Finalidade**: Cada serviço = 1 domínio coeso funcionalmente

**2. Transações**: Se precisa de transação entre serviços, provavelmente granulou demais
- Solução: **Não faça transações entre serviços, corrija a granularidade!**

**3. Coreografia**: Se tem muita comunicação entre serviços, considere reagrupar

**Regra de ouro**: Iteração! Raramente você acerta na primeira vez.

### Comunicação: Síncrona vs Assíncrona

**Síncrona (REST, gRPC)**:
- Requisição espera resposta
- Simples, direto
- Cria acoplamento temporal
- Problemas de latência amplificados

**Assíncrona (Mensageria, Eventos)**:
- Fire-and-forget ou pub/sub
- Melhor desacoplamento
- Complexidade maior
- Eventual consistency

**Protocolo Reconhecido + Heterogêneo + Interoperável**:
- Cada serviço sabe como chamar outros (REST, gRPC, etc)
- Cada serviço pode usar stack diferente (Java, Go, Python)
- Serviços colaboram via rede

### Coreografia vs Orquestração

**Coreografia (preferida)**:
```
Cliente → ServiçoA → ServiçoB → ServiçoC
```
- Sem coordenador central
- Cada serviço chama outros quando necessário
- Máximo desacoplamento
- Problema: coordenação complexa, tratamento de erros difícil

**Orquestração (quando necessário)**:
```
Cliente → Orquestrador → ServiçoA
                       → ServiçoB
                       → ServiçoC
```
- Mediador coordena chamadas
- Mais fácil gerenciar fluxos complexos
- Trade-off: cria ponto de acoplamento

**Pattern: Front Controller**
Quando um serviço acaba virando um orquestrador informal porque coordena muitos outros. Sinal de que talvez precise de orquestração explícita.

### Transações Distribuídas: Pattern Saga

**Problema**: Como fazer transação que envolve múltiplos serviços?

**Resposta curta**: Não faça! Corrija a granularidade.

**Resposta realista**: Às vezes é inevitável. Use Saga Pattern.

**Saga Pattern**:
1. Mediador coordena sequência de operações
2. Cada operação atualiza seu serviço
3. Se uma falha, mediador envia **compensações** pra desfazer as anteriores

**Compensação**:
- Operação `do` + operação `undo`
- Complexidade: undo geralmente é 2x mais complexo que do
- Trade-off: atomicidade vs complexidade

**Importante**: Se você precisa de sagas constantemente, provavelmente escolheu arquitetura errada!

### Reutilização Operacional: Sidecar Pattern

**Problema**: Como evitar duplicação de preocupações operacionais (log, monitoramento, circuit breaker)?

**Solução**: Sidecar Pattern

```
┌─────────────┐  ┌─────────────┐  ┌─────────────┐
│  Serviço A  │  │  Serviço B  │  │  Serviço C  │
├─────────────┤  ├─────────────┤  ├─────────────┤
│   Sidecar   │  │   Sidecar   │  │   Sidecar   │
└─────────────┘  └─────────────┘  └─────────────┘
       ↓                ↓                ↓
    ────────────────────────────────────────
              Service Mesh (plano de controle)
```

**Sidecar**: Componente separado implantado junto com cada serviço que lida com:
- Monitoramento
- Logging
- Circuit breakers
- Service discovery
- Tracing distribuído

**Service Mesh**: Sidecars conectados formam uma malha que permite controle global de aspectos operacionais.

Exemplos: Istio, Linkerd, Consul Connect

### API Gateway / API Layer

**Função**:
- Ponto de entrada único pro sistema
- Roteamento de requisições
- Autenticação/Autorização centralizada
- Rate limiting
- Service discovery

**O que NÃO deve fazer**: Lógica de negócio! Gateway é só roteamento.

### Front-ends: Duas Abordagens

**1. Monolítico (comum)**:
```
[SPA React/Angular] → API Gateway → Microsserviços
```
- UI única chama múltiplos serviços
- Simples, fácil de desenvolver
- Mas cria acoplamento no front

**2. Micro-frontends (avançado)**:
```
[Componente UI A] ↘
[Componente UI B] → Coordenador → Navegador
[Componente UI C] ↗
```
- Cada serviço emite sua própria UI
- Isolamento completo (UI + Backend no mesmo time)
- Complexidade maior

### Descoberta de Serviços (Service Discovery)

**Problema**: Como um serviço sabe onde encontrar outro serviço? IPs mudam, instâncias sobem/descem.

**Solução**: Service Registry

**Client-side discovery**:
```
Serviço A → Registry (Consul/Eureka) → descobre IP → chama Serviço B
```

**Server-side discovery**:
```
Serviço A → Load Balancer → descobre e roteia → Serviço B
```

### Quando Usar Microsserviços?

**Use quando**:
- Alta escalabilidade é crítica
- Times grandes, independentes
- Domínios claramente separados
- Deploy independente é necessário
- Diferentes tecnologias por domínio
- Sistema evolutivo (muda rápido)

**Não use quando**:
- Sistema pequeno/médio
- Time pequeno (< 10 pessoas)
- Domínios muito acoplados
- Transações frequentes entre domínios
- Performance é crítica (latência baixa)
- Complexidade operacional inaceitável

**Regra**: Comece monolítico. Migre pra microsserviços quando a dor do monolito for maior que a complexidade dos microsserviços.

### Vantagens

**Escalabilidade**: Escalar serviços independentemente
**Elasticidade**: Auto-scaling por serviço
**Agilidade**: Deploy independente, times autônomos
**Evolutivo**: Fácil mudar/substituir serviços
**Tecnológico**: Stack diferente por serviço
**Isolamento de falhas**: Serviço cai, outros continuam
**Time-to-market**: Features em paralelo

### Desvantagens

**Complexidade operacional**: Monitoramento, logging, tracing distribuído
**Latência**: Muitas chamadas de rede
**Consistência de dados**: Eventual consistency
**Transações**: Difícil coordenar entre serviços
**Debugging**: Difícil rastrear bugs através de serviços
**Testing**: Testes E2E complexos
**DevOps intensivo**: Requer automação pesada
**Overhead de rede**: Muitas chamadas HTTP/gRPC
**Segurança**: Mais endpoints pra proteger

### Anti-Patterns Comuns

**1. Granularidade Muito Fina**: Serviços minúsculos que só fazem CRUD
**2. Transações Distribuídas**: Tenta fazer ACID entre serviços
**3. Shared Database**: Múltiplos serviços acessando mesmo banco
**4. ESB Disfarçado**: API Gateway com lógica de negócio
**5. Acoplamento Chatty**: Serviço A chama B chama C chama D...