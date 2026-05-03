# 💼 Sistema de Precificação e Comissão — SST

> Sistema web para gestão de vendas, precificação de serviços e cálculo de comissão de consultores, desenvolvido com domínio real do setor de **Saúde e Segurança do Trabalho (SST)**.

---

## 📋 Sumário

- [Visão Geral](#visão-geral)
- [Stack Tecnológica](#stack-tecnológica)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Modelagem de Dados](#modelagem-de-dados)
- [Regras de Negócio](#regras-de-negócio)
- [Como Rodar o Projeto](#como-rodar-o-projeto)
- [Decisões de Design](#decisões-de-design)
- [Contexto Profissional](#contexto-profissional)

---

## Visão Geral

O **Sistema de Precificação e Comissão SST** é uma aplicação web desenvolvida para simular o fluxo comercial de empresa que trabalho atualmente do setor de Saúde e Segurança do Trabalho. O sistema permite:

- Cadastrar empresas clientes com segmento (construção, indústria, comércio)
- Cadastrar serviços de SST (treinamentos NR, laudos, PCMSO, exames ocupacionais, consultoria)
- Registrar vendas com múltiplos serviços por pedido
- Calcular preço final com descontos por volume, tipo de cliente e prazo de pagamento
- Calcular comissão dos consultores com base no percentual da meta atingida

O domínio foi escolhido por refletir a realidade do setor comercial de empresas de SST, tornando as regras de negócio autênticas e aplicáveis ao dia a dia.

---

## Stack Tecnológica

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 17 |
| Framework | Spring Boot 3.2 |
| ORM | Hibernate via Spring Data JPA |
| Banco (prod) | PostgreSQL |
| Frontend | HTML + CSS + JavaScript puro |
| Testes | JUnit 5 + Mockito |
| Build | Maven |
| Deploy | Railway |

---

## Estrutura do Projeto

```
calculadora-precificacao/
├── src/
│   ├── main/
│   │   ├── java/com/vendas/principal/
│   │   │   ├── model/
│   │   │   │   ├── EmpresaModel.java
│   │   │   │   ├── Vendedor.java
│   │   │   │   ├── Venda.java
│   │   │   │   ├── Servico.java
│   │   │   │   └── ItemVenda.java
│   │   │   ├── repository/        ← próxima etapa
│   │   │   ├── service/           ← próxima etapa
│   │   │   └── controller/        ← próxima etapa
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/            ← HTML, CSS, JS
│   └── test/
│       └── java/com/vendas/principal/
│           └── service/           ← próxima etapa
└── pom.xml
```

---

## Modelagem de Dados

O sistema é composto por 5 entidades com os seguintes relacionamentos:

```
EmpresaModel (1) ──────────── (N) Venda
Vendedor     (1) ──────────── (N) Venda
Venda        (1) ──────────── (N) ItemVenda
Servico      (1) ──────────── (N) ItemVenda
```

### Entidades

**EmpresaModel** — empresa cliente contratante
- `id` (UUID), `nomeEmpresa`, `cnpj` (único), `telefone`
- `qtdFuncionario`, `setorEmpresa`, `dataCadastro`
- Relacionamento: uma empresa possui muitas vendas

**Vendedor** — consultor de vendas
- `id` (UUID), `nomeConsultor`, `telefoneConsultor`, `dataContratacao`
- `meta` (BigDecimal), `vendasAcumuladasMes` (BigDecimal)
- Relacionamento: um vendedor realiza muitas vendas

**Venda** — registro de uma negociação fechada
- `id` (UUID), `dataVenda`, `valorTotal`, `desconto`
- Chaves estrangeiras: `empresa_id`, `vendedor_id`
- Relacionamento: uma venda contém muitos itens

**Servico** — serviço de SST oferecido
- `id` (UUID), `nome`, `categoriaServico`, `valorBase`
- Relacionamento: um serviço aparece em muitos itens de venda

**ItemVenda** — item individual dentro de uma venda
- `id` (UUID), `valorUnitario`, `quantidade`, `valorTotal`
- Chaves estrangeiras: `venda_id`, `servico_id`
- Método `calcularTotal()` encapsula a lógica de cálculo

### Serviços disponíveis (exemplos fictícios)

| Serviço | Categoria | Valor Base |
|---|---|---|
| Treinamento NR-35 (Trabalho em Altura) | TREINAMENTO | R$ 180,00/pessoa |
| Treinamento NR-10 (Elétrica) | TREINAMENTO | R$ 220,00/pessoa |
| Laudo de Insalubridade | LAUDO_TECNICO | R$ 1.200,00 |
| PGR (Prog. Gerenciamento de Riscos) | LAUDO_TECNICO | R$ 2.500,00 |
| PCMSO Anual | PROGRAMA | R$ 1.800,00 |
| Exame Admissional | EXAME_OCUPACIONAL | R$ 95,00/funcionário |
| Consultoria — Visita Técnica | CONSULTORIA | R$ 350,00/visita |

---

## Regras de Negócio

### Desconto por volume (quantidade de pessoas/itens)

| Quantidade | Desconto |
|---|---|
| 1 a 9 | 0% |
| 10 a 49 | 5% |
| 50 ou mais | 10% |

### Desconto por prazo de pagamento

| Prazo | Ajuste |
|---|---|
| À vista | −5% |
| 30 dias | sem alteração |
| 60 dias ou mais | +2% |

### Desconto por tipo de cliente

| Perfil | Desconto |
|---|---|
| Indústria / Construção + Contrato Mensal | 8% fixo |
| Cliente recorrente (qualquer segmento) | +3% adicional |
| Novo cliente | sem desconto extra |

### Comissão do consultor

| % da meta atingida | Comissão | Bônus |
|---|---|---|
| Até 50% | 2% sobre a venda | — |
| 50% a 100% | 4% sobre a venda | — |
| 100% ou mais | 6% sobre a venda | + R$ 500,00 fixo |

---

## Como Rodar o Projeto

### Pré-requisitos
- Java 17+
- Maven 3.8+

### Passos

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/calculadora-precificacao.git

# 2. Acesse a pasta do projeto
cd calculadora-precificacao

# 3. Rode a aplicação
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`. O banco H2 é criado automaticamente em memória — nenhuma instalação adicional necessária.

---

## Decisões de Design

**Por que Spring Boot?**
A vaga alvo exige experiência com Spring. Usar Spring Boot desde o primeiro projeto garante que as tecnologias do portfólio estejam alinhadas com o que o mercado pede.

**Por que UUID como chave primária?**
UUIDs são a prática adotada em sistemas empresariais modernos por não exporem sequência numérica, facilitarem a integração entre sistemas e serem mais seguros em APIs públicas.

**Por que BigDecimal para valores monetários?**
`double` e `float` têm problemas de precisão em ponto flutuante que causam erros em cálculos financeiros. `BigDecimal` é o tipo correto para qualquer valor monetário em Java.

**Por que `ItemVenda` como entidade separada?**
O relacionamento entre `Venda` e `Servico` é muitos-para-muitos com dados próprios (quantidade, valor unitário). Uma entidade de junção simples não comportaria esses dados. `ItemVenda` resolve isso de forma limpa e extensível.

---

## Contexto Profissional

Este projeto faz parte de um portfólio estruturado para transição de carreira para Engenharia de Software, com foco em vagas Java Júnior no segmento de ERP (TOTVS e similares).

O domínio de SST foi escolhido por ser o meu setor de atuação atual, o que garante autenticidade nas regras de negócio e profundidade nas decisões de modelagem — diferenciando o projeto de soluções genéricas como to-do lists ou e-commerces fictícios.

O portfólio segue uma progressão planejada de complexidade:

| # | Projeto | Status |
|---|---|---|
| 1 | Calculadora de Precificação e Comissão | 🔧 Em andamento |
| 2 | Sistema de Onboard de novo colaborador | 📋 Backlog |
| 3 | CRM com Pipeline de Vendas | 📋 Backlog |
| 4 | Sistema de Metas e OKRs | 📋 Backlog |
| 5 | Dashboard de Rastreamento Logístico | 📋 Backlog |
| 6 | Simulador de Roteirização de Entregas | 📋 Backlog |

