<img width="100%" bottom=50px src ="https://capsule-render.vercel.app/api?type=waving&height=100&color=FF78CB&section=header&reversal=false&descAlign=22&descAlignY=42"/>

<div align = "center" id="english">
<img width="150" height="150" alt="Image" src="https://github.com/user-attachments/assets/3725d5d1-d50e-41f6-9364-b3ccdcb09452" />

<a href="https://github.com/albiecr"><img src="https://readme-typing-svg.herokuapp.com?font=Sour+Gummy&size=40&pause=100&color=EF82F7&width=450&height=60&lines=Smart+Energy+Manager" alt="Typing SVG" /></a></div>



<p align="center">
  <strong>English</strong> | <a href="#português">Português</a>
</p>

<p align="center">
Smart Energy Manager is a business intelligence solution designed for large-scale energy consumers (such as Hotels and Industries).
</p>


<p align="center">
  <img src="https://img.shields.io/badge/status-in%20development-yellow" alt="Project Status">
</p>

# ⚡ About the project
### Tariff Simulation & Energy Optimization System (Group A)

The project operates based on a technical simulation scenario: **"What if a large European consumer operated under Brazilian tariff regulations?"** We utilize a real-world IoT dataset from a hotel and apply the **CELESC (Florianópolis/SC)** tariff rules for "Group A" (High Voltage) consumers.

The goal is to allow facility managers to simulate different scenarios (Green vs. Blue Tariff, Contracted Demand adjustments) to avoid fines and reduce operational costs.

---

## 💼 The Business Case

Large energy consumers in Brazil do not simply pay for what they consume. The billing structure involves complex rules:

1.  **Contracted Demand:** A fixed cost paid for the availability of the power grid (kW), regardless of usage.
2.  **Time-of-Use (TOU) Tariffs:** Energy consumed during **Peak Hours (18:30 - 21:30)** is significantly more expensive than during Off-Peak Hours.
3.  **Overshoot Fines:** If instantaneous consumption exceeds the contracted demand, a heavy penalty is applied.

**The Challenge:** The manager needs to answer: *"Should I contract 400kW or 500kW? Is the 'Green Tariff' better than the 'Blue Tariff' for my seasonal consumption profile?"*

---

## 🚀 Key Features

### 1. Data Ingestion & Processing (Python ETL)
* **Automated Pipeline:** Python script that reads raw CSV files (15-minute interval readings from sector meters).
* **Temporal Synchronization:** Unification of consumption data (kW) with meteorological data (Temperature/Irradiance).
* **Data Cleaning:** Handling sensor failures (interpolation) and loading data into a relational database.

### 2. Tariff Calculation Engine (Java Spring Boot Backend)
* **Local Business Logic:** Implementation of ANEEL/CELESC regulatory rules.
* **Temporal Classification:** Algorithm that automatically identifies if a reading occurred during Peak or Off-Peak hours, considering weekends and holidays.
* **Financial Calculation:** Application of tariffs (R$/kWh and R$/kW) onto the processed data.

### 3. Scenario Simulator (In Development)
An interface where the user defines parameters to test hypotheses:
* *"How much would I save if I reduced my peak consumption by 10%?"*
* *"What is the financial impact of switching from the Blue Tariff to the Green Tariff?"*

### 4. Demand Forecasting with AI (Next Phase)
* Use of Machine Learning (Python/Scikit-Learn) to predict next-day demand based on weather forecasts, generating alerts for potential contract overshoots.

---

## 🛠️ Tech Stack

### Data & Intelligence
* **Python 3.12+**
* **Pandas:** Data manipulation and cleaning (ETL).
* **SQLAlchemy:** Database connection.
* **PostgreSQL:** Relational Database (Time Series).

### Backend & API
* **Java 17**
* **Spring Boot 3:** Main framework.
* **Spring Data JPA:** Data persistence.
* **Maven:** Dependency management.

### Tools
* **VS Code**
* **Git / GitHub**
* **PgAdmin 4**

---

## 📂 Project Structure

The project adopts a hybrid architecture, separating data engineering from application logic:

```text
smart-energy-manager/
│
├── etl/                  # Data Engineering Module (Python)
│   ├── data/             # Raw CSV files (Input)
│   └── etl_pipeline.py   # Ingestion and Loading Script
│
├── smart-energy-api/     # Backend and Business Logic (Java)
│   ├── src/main/java...  # REST API, Entities, and Celesc Logic
│   └── resources/        # Database Configuration
│
└── frontend/             # Simulator Interface (Future)
```

## 🚦 Roadmap e Status
* [x] **Environment Setup:** PostgreSQL database and tools installed.

* [x] **ETL Pipeline:** Ingestion of real-world data (46k+ records) completed.

* [x] **Data Modeling:** `hotel_readings` table structured.

* [ ] **Backend API (Java):** Database connection and Entity creation. (In progress)

* [ ] **Tariff Logic:** Implement Peak/Off-Peak algorithm (Celesc rules).

* [ ] **REST Endpoints:** Create routes for simulation (`POST /api/simulate`).

* [ ] **Frontend:** Cost visualization dashboard.

## 🚧 Project under active development. Updates coming soon.

---
<div align = "center" id="português">
<img width="150" height="150" alt="Image" src="https://github.com/user-attachments/assets/3725d5d1-d50e-41f6-9364-b3ccdcb09452" />
  
<a href="https://github.com/albiecr"><img src="https://readme-typing-svg.herokuapp.com?font=Sour+Gummy&size=40&pause=100&color=EF82F7&width=650&height=60&lines=Gerenciador+Inteligente+de+Energia" alt="Typing SVG" /></a></div>
<p align="center">
  <a href="#english">English</a> | <strong>Português</strong>
</p>

<p align="center"> O Smart Energy Manager é uma solução de inteligência de negócios voltada para grandes consumidores de energia (Hotéis e Indústrias). 
</p>

<p align="center">
  <img src="https://img.shields.io/badge/status-em%20desenvolvimento-yellow" alt="Project Status">
</p>

# ⚡ Sobre o projeto
### Sistema de Simulação Tarifária e Otimização Energética (Grupo A)

O projeto opera sobre um cenário de simulação técnica: **"E se um grande consumidor operasse sob as regras tarifárias brasileiras?"**. Utilizamos um dataset real de sensores IoT de um hotel e aplicamos sobre ele as regras tarifárias da **CELESC (Florianópolis/SC)** para o Grupo A (Alta Tensão).

O objetivo é permitir que gestores simulem diferentes cenários (Tarifa Verde vs. Azul, ajustes de Demanda Contratada) para evitar multas e reduzir custos operacionais.

---

## 💼 O Problema de Negócio (Business Case)

Grandes consumidores de energia no Brasil não pagam apenas pelo que consomem. A fatura é composta por regras complexas:

1.  **Demanda Contratada:** Um valor fixo pago pela disponibilidade da rede (kW), independente do uso.
2.  **Postos Tarifários:** A energia consumida no **Horário de Ponta (18h30 - 21h30)** custa muito mais caro que no Horário Fora de Ponta.
3.  **Multas de Ultrapassagem:** Se o consumo instantâneo superar a demanda contratada, paga-se uma multa elevada.

**O Desafio:** O gestor precisa responder: *"Devo contratar 400kW ou 500kW? A Tarifa Verde é melhor que a Azul para o meu perfil de consumo sazonal?"*

---

## 🚀 Funcionalidades do Projeto

### 1. Ingestão e Tratamento de Dados (ETL Python)
* **Pipeline Automatizado:** Script Python que lê arquivos CSV brutos (leituras de 15 em 15 minutos de medidores setoriais).
* **Sincronização Temporal:** Unificação de dados de consumo (kW) com dados meteorológicos (Temperatura/Irradiação).
* **Data Cleaning:** Tratamento de falhas de sensores (interpolação) e carga em banco de dados relacional.

### 2. Engine de Cálculo Tarifário (Backend Java Spring Boot)
* **Regras de Negócio Locais:** Implementação da lógica da ANEEL/CELESC.
* **Classificação Temporal:** Algoritmo que identifica automaticamente se uma leitura ocorreu em Horário de Ponta ou Fora de Ponta, considerando finais de semana e feriados.
* **Cálculo Financeiro:** Aplicação das tarifas (R$/kWh e R$/kW) sobre os dados processados.

### 3. Simulador de Cenários (Em desenvolvimento)
Uma interface onde o usuário define parâmetros para testar hipóteses:
* *"Quanto eu economizaria se reduzisse meu pico de consumo no horário nobre em 10%?"*
* *"Qual o impacto financeiro de migrar da Tarifa Azul para a Verde?"*

### 4. Previsão de Demanda com IA (Próxima Fase)
* Uso de Machine Learning (Python/Scikit-Learn) para prever a demanda do dia seguinte baseada na previsão do tempo, gerando alertas de risco de ultrapassagem.

---

## 🛠️ Tecnologias Utilizadas

### Dados & Inteligência
* **Python 3.12+**
* **Pandas:** Manipulação e limpeza de dados (ETL).
* **SQLAlchemy:** Conexão com banco de dados.
* **PostgreSQL:** Banco de dados relacional (Séries Temporais).

### Backend & API
* **Java 17**
* **Spring Boot 3:** Framework principal.
* **Spring Data JPA:** Persistência de dados.
* **Maven:** Gerenciamento de dependências.

### Ferramentas
* **VS Code**
* **Git / GitHub**
* **PgAdmin 4**

---

## 📂 Estrutura do Projeto

O projeto adota uma arquitetura híbrida, separando a engenharia de dados da lógica de aplicação:

```text
smart-energy-manager/
│
├── etl/                  # Módulo de Engenharia de Dados (Python)
│   ├── data/             # Arquivos CSV brutos (Input)
│   └── etl_pipeline.py   # Script de Ingestão e Carga
│
├── smart-energy-api/     # Backend e Regras de Negócio (Java)
│   ├── src/main/java...  # API REST, Entidades e Lógica Celesc
│   └── resources/        # Configurações do Banco de Dados
│
└── frontend/             # Interface do Simulador (Futuro)
```

## 🚦 Roadmap e Status
* [x] **Configuração de Ambiente:** Banco PostgreSQL e ferramentas instaladas.

* [x] **Pipeline ETL:** Ingestão de dados reais (46k+ registros) concluída.

* [x] **Modelagem de Dados:** Tabela `hotel_readings` estruturada.

* [ ] **Backend API (Java):** Conexão com banco e criação das Entidades. (Em andamento)

* [ ] **Lógica Tarifária:** Implementar algoritmo de Ponta/Fora de Ponta (Celesc).

* [ ] **Endpoints REST:** Criar rotas para simulação (`POST /api/simular`).

* [ ] **Frontend:** Painel de visualização de custos.


## 🚧 Projeto em desenvolvimento ativo. Atualizações em breve.

<img width="100%" bottom=50px src ="https://capsule-render.vercel.app/api?type=waving&height=100&color=FF78CB&section=footer&reversal=false&descAlign=22&descAlignY=42"/>
