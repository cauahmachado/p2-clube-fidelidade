# Clube de Assinaturas e Fidelidade

**Trabalho Prático P2 – Programação Orientada a Objetos**
Fatec Mogi Mirim · Prof. Me. Marcos Roberto de Moraes (Maromo)
Materiais de referência: POO_07 a POO_10

## 1. Identificação

Cauã Henrique D Oliveira Machado

## 2. Tema escolhido

**Tema 07 – Clube de Assinaturas e Fidelidade.** O sistema permite cadastrar membros do clube, registrar compras que geram pontos, resgatar pontos e listar os membros em um ranking. Todos os dados ficam em memória RAM, em `List<MembroClube> = new ArrayList<>()`, sem banco de dados nem arquivos.

Regras de negócio implementadas:

- Cada membro tem ID único, nome, nível (**Comum** ou **Premium**) e saldo de pontos.
- Cada R$ 1,00 inteiro gasto gera 1 ponto base; o nível **Premium** multiplica os pontos por 2.
- O resgate só é permitido se o saldo for suficiente; caso contrário, a exceção customizada é lançada e exibida ao usuário.
- Não é permitido cadastrar dois membros com o mesmo ID.

## 3. Arquitetura das classes

Pacote base: `oop.projectsubscriptionloyaltyclub`

| Pacote | Classe | Responsabilidade |
|---|---|---|
| `model` | `MembroClube` | Entidade principal. Implementa `Comparable<MembroClube>` (ranking por pontos, desempate por ID), `equals()` e `hashCode()` baseados no ID, `toString()` e o `Comparator` `POR_ID`. |
| `model` | `NivelMembro` | Enum com os níveis Comum e Premium e seu multiplicador de pontos. |
| `interfaces` | `Bonificavel` | Contrato de negócio: `acumularPontos`, `resgatarPontos` e o método `default calcularPontosCompra`. Implementada por `MembroClube`. |
| `excecoes` | `PontuacaoInsuficienteException` | Checked Exception (`extends Exception`) lançada quando o resgate excede o saldo. |
| `visao` | `TelaClube` | Interface Swing (`JFrame`) com formulário, botões e `JTextArea` de relatório. Mantém a lista em memória. |
| (raiz) | `Main` | Instancia a tela e chama `setVisible(true)`. |

### Atendimento aos requisitos do enunciado

| Requisito | Onde está |
|---|---|
| Persistência em `List<T>` / `ArrayList` | `TelaClube` (atributo `membros`) |
| Interface gráfica com `javax.swing.*` | `TelaClube` |
| `ActionListener` nos botões e `WindowListener` no fechamento | `TelaClube` (`addActionListener`, `WindowAdapter`) |
| Checked Exception customizada com `JOptionPane` | `PontuacaoInsuficienteException`, tratada em `TelaClube.processarOperacao()` |
| `NumberFormatException` nos campos de texto | `TelaClube` (cadastrar, pesquisar, processar) |
| `Comparable<T>` + `Collections.sort()` | `MembroClube.compareTo()`, usado em `TelaClube.atualizarRelatorio()` |
| `equals()` e `hashCode()` | `MembroClube` |
| Interface de negócio com método `default` | `Bonificavel` |

## 4. Como executar

1. Abrir o projeto no IntelliJ IDEA (JDK _(versão utilizada)_).
2. Executar a classe `Main` (`oop.projectsubscriptionloyaltyclub.Main`).

### Funcionalidades da tela

- **Cadastrar:** valida ID e nome e impede ID repetido.
- **Pesquisar:** busca um membro pelo ID.
- **Processar Operação:** registra uma compra (gera pontos) ou resgata pontos.
- **Ordenar:** alterna entre ranking por pontos (ordem natural) e ordem por ID (`Comparator`).
- **Sair / botão X da janela:** pede confirmação antes de encerrar.

## 5. Testes realizados

Testes manuais executados na tela, todos com o resultado esperado:

- cadastro de membros Comum e Premium e verificação da pontuação diferenciada;
- ranking correto após compras e alternância da ordenação por ID;
- resgate com saldo insuficiente (exceção exibida em `JOptionPane`) e resgate válido;
- ID duplicado, ID não numérico, compra abaixo de R$ 1,00, valor com vírgula e pesquisa de ID inexistente;
- confirmação ao sair pelo botão e pelo X da janela.

## 6. Uso de IA generativa e histórico de prompts

A IA (Claude, da Anthropic) foi utilizada como **apoio**, conforme permitido no enunciado. As decisões de projeto, a integração ao ambiente, os testes e a correção dos erros foram feitos pela dupla.

### Divisão de responsabilidades

| A IA ajudou com | A dupla fez |
|---|---|
| Resumir e organizar os requisitos do enunciado | Leu o PDF original e conferiu o resumo |
| Comparar temas e sugerir opções | Escolheu o tema 07 e decidiu manter os níveis Comum/Premium |
| Gerar o código-base das classes e da tela Swing | Revisou o código, adaptou à estrutura de pacotes do projeto e integrou ao IntelliJ |
| Explicar a diferença entre `Comparable` e `Comparator` | Decidiu como usar cada um (ranking como ordem natural, ID como alternativa) |
| Apontar a causa dos erros de compilação | Identificou os erros no IntelliJ, aplicou as correções e validou |
| Sugerir um roteiro de testes | Executou os testes e conferiu os resultados |
| Auxiliar na base do README.md | Escreveu o README.md

### Histórico de prompts

**Prompt 1 – Compreensão do enunciado**
- **O que pedi:** colei as orientações da P2 e o PDF do trabalho e pedi para a IA ler e absorver.
- **Resposta obtida:** um resumo dos requisitos (dupla, `ArrayList` em memória, Swing, exceção checked, `Comparable`, interface de negócio, README com prompts e entrega via GitHub).
- **Como refinei:** conferi o resumo com o PDF original, o que ajudou a notar também as exigências de `equals()`/`hashCode()` e de `WindowListener`.

**Prompt 2 – Escolha do tema**
- **O que pedi:** "estou em dúvida entre o tema 07 e o 05 qual escolher, afim de testar melhor meus conhecimetos?".
- **Resposta obtida:** a IA sugeriu o tema 07 (lógica baseada em números inteiros, sem datas).
- **Como refinei:** Analisei o tema melhor e percebi que viável no prazo e que cobria bem os conceitos pedidos.

**Prompt 3 – Classes do domínio**
- **O que pedi:** ajuda para saber como começar.
- **Resposta obtida:** estrutura das ideias em texto de como deveria aplicar de `MembroClube`, `NivelMembro`, `Bonificavel` e `PontuacaoInsuficienteException`.
- **Como refinei:** recriei as classes no meu projeto em linguagem JAVA, ajustando de forma que funcionasse e respeitasse os requisitos.

**Prompt 5 – Tela Swing**
- **O que pedi:** ajuda para relembrar como montar a tela Swing, pois não lembrava por completo a estrutura de uma tela com formulário, botões e eventos.
- **Resposta obtida:** instruções de como montar a `TelaClube` com formulário, os cinco botões, `JTextArea` em `JScrollPane`, `JOptionPane` e `WindowListener`, além da classe `Main`.
- **Como refinei:** revisei a tela, adaptei os imports aos meus pacotes e testei cada botão.

**Prompt 8 – README**
- **O que pedi:** ajuda para estruturar este README de forma que ficasse fácil de entender cobrindo os requisitos.
- **Resposta obtida:** a estrutura deste documento.
- **Como refinei:** usei a estrutura para preencher com os informações pedidas pelo professor.
