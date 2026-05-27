# 📦 Logistics Tracker

Serviço de rastreamento logístico responsável por orquestrar e registrar o ciclo de entrega de produtos, desde a saída da loja até a entrega ao cliente final.

> Projeto de estudo com foco em fundamentos do Java, Clean Architecture, Event-Driven Architecture e boas práticas de desenvolvimento.

---

## 🎯 Objetivo

Rastrear o fluxo de entrega de um produto através de eventos de scan em cada etapa do processo:

```
Loja → [scan] → Distribuidora → [scan] → Entregador → [scan] → Cliente → [scan]
```

Cada scan gera um `TrackingEvent` que registra o status, localização, operador e momento do ocorrido.

---

## 🏗️ Arquitetura

O projeto segue os princípios da **Clean Architecture** com **Event-Driven Architecture** interna e externa.

```
presentation/        → entrada HTTP (controllers REST)
application/         → casos de uso, DTOs, listeners de eventos
domain/              → entidades, enums, contratos de repositório, eventos
infrastructure/      → JPA, RabbitMQ, implementações de repositório
```

### Fluxo de um scan

```
POST /api/v1/scans
        ↓
TrackingController
        ↓
RegisterScanUseCase
        ├── persiste TrackingEvent
        ├── atualiza status do Order
        └── publica ScanRegistradoEvent
                ↓
        ScanRegistradoListener
                ├── LogUseCase
                └── NotificationUseCase (via RabbitMQ)
```

### Integração com outros serviços

Este serviço não gerencia cadastro de usuários, produtos ou pedidos. Esses dados chegam via RabbitMQ de serviços externos, seguindo o padrão **Anti-Corruption Layer (ACL)**:

```
[user-service]    → fila: user.created    → UserCreatedConsumer
[product-service] → fila: product.created → ProductCreatedConsumer
[order-service]   → fila: order.created   → OrderCreatedConsumer
```

---

## 🛠️ Tecnologias

| Tecnologia              | Uso                        |
|-------------------------|----------------------------|
| Java 21                 | Linguagem principal        |
| Spring Boot 3.3         | Framework                  |
| Hibernate / JPA         | Persistência               |
| PostgreSQL              | Banco de dados             |
| RabbitMQ                | Mensageria entre serviços  |
| Prometheus              | Coleta de métricas         |
| Grafana                 | Visualização de métricas   |
| Swagger / OpenAPI       | Documentação da API        |
| Docker / Docker Compose | Containerização            |
| Redis                   | Cache                      |
| Spring Security + JWT   | Auth                       |
| JUnit                   | Testes unitários           |
| Spring Cloud            | Ecossistema para microsserviços|

---

## 📐 Conceitos praticados

- Clean Architecture
- SOLID
- Clean Code
- Event-Driven Architecture (Pub-Sub)
- API First e REST
- Anti-Corruption Layer (ACL)
- Dependency Inversion (ports & adapters)
- Outbox Pattern (PENDENTE)
- Circuit Breaker (PENDENTE)
- Retry Pattern (PENDENTE)
- Rate Limiting (PENDENTE)
- CI/CD: pipeline, lint, testes automáticos e deploy automatizado (PENDENTE)

---

## 📁 Estrutura de pacotes

```
src/main/java/com/seuNome/logisticstracker/
│
├── domain/
│   ├── model/               # POJOs puros — sem anotações de framework
│   ├── enums/               # TrackingStatus, UserRole
│   ├── event/               # ScanRegistradoEvent
│   └── repository/          # Interfaces (contratos de saída)
│
├── application/
│   ├── usecase/             # Casos de uso
│   ├── dto/                 # Request e Response DTOs
│   └── event/
│       └── listener/        # Listeners de eventos internos
│
├── presentation/
│   └── rest/                # Controllers REST
│
└── infrastructure/
    ├── persistence/
    │   ├── entity/          # @Entity JPA
    │   ├── mapper/          # Conversão domain ↔ entity
    │   └── repository/      # Implementações JPA
    └── messaging/
        ├── consumer/        # Consumidores RabbitMQ
        ├── dto/             # Contratos externos (ACL)
        └── mapper/          # Conversão mensagem → domínio
```

---

## 🚀 Como rodar

### Pré-requisitos

- Docker e Docker Compose instalados
- Java 21
- Maven

### Subindo a infraestrutura

```bash
# Clone o repositório
git clone https://github.com/seuUsuario/logistics-tracker.git
cd logistics-tracker

# Suba apenas a infraestrutura (recomendado durante desenvolvimento)
docker compose up -d postgres rabbitmq prometheus grafana

# Rode a aplicação pela IDE ou via Maven
./mvnw spring-boot:run
```

### Subindo tudo junto

```bash
docker compose up -d
```

### Derrubando

```bash
# Apenas os containers
docker compose down

# Containers + volumes (limpa os dados)
docker compose down -v
```

---

## ⚙️ Variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto baseado no exemplo abaixo:

```env
# Database
DB_NAME=logistics
DB_USER=postgres
DB_PASS=postgres123

# RabbitMQ
RABBITMQ_USER=guest
RABBITMQ_PASS=guest
RABBITMQ_VHOST=/

# Grafana
GRAFANA_USER=admin
GRAFANA_PASS=admin123

# App
SERVER_PORT=8080
SWAGGER_ENABLED=true
DDL_AUTO=update
SHOW_SQL=false
```

> ⚠️ Nunca suba o `.env` para o repositório. Ele já está no `.gitignore`.

---

## 🌐 URLs disponíveis

| Serviço | URL |
|---|---|
| API | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui |
| API Docs (JSON) | http://localhost:8080/api-docs |
| RabbitMQ Management | http://localhost:15672 |
| Prometheus | http://localhost:9090 |
| Grafana | http://localhost:3000 |

---

## 📬 Endpoints

### Tracking

| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/v1/scans` | Registra um novo scan de rastreamento |

### Exemplo de request

```json
POST /api/v1/scans
{
  "orderId": 1,
  "scannedById": 2,
  "locationId": 3,
  "status": "AT_DISTRIBUTOR",
  "notes": "Produto recebido na distribuidora SP"
}
```

### Exemplo de response

```json
{
  "trackingEventId": 10,
  "orderId": 1,
  "status": "AT_DISTRIBUTOR",
  "occurredAt": "2026-05-18T14:30:00"
}
```

---

## 📊 Status de rastreamento

| Status | Descrição |
|---|---|
| `DISPATCHED` | Saiu da loja |
| `AT_DISTRIBUTOR` | Chegou na distribuidora |
| `COLLECTED` | Entregador coletou |
| `OUT_FOR_DELIVERY` | Saiu para entrega |
| `DELIVERED` | Entregue ao cliente |
| `FAILED` | Falha na entrega |

---

## 🧪 Filas RabbitMQ

| Fila | Origem | Descrição |
|---|---|---|
| `user.created` | user-service | Sincroniza dados de usuário |
| `product.created` | product-service | Sincroniza dados de produto |
| `order.created` | order-service | Sincroniza dados de pedido |

---

## 📝 Licença

Projeto de estudo — sem licença comercial.