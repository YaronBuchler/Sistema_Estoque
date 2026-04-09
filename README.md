# 📦 Sistema de Controle de Estoque

Sistema desenvolvido para gerenciamento de produtos, fornecedores, clientes e movimentações de estoque.

---

# 📄 Requisitos do Sistema — Loja de Estoque

---

## 📋 Requisitos Funcionais

### Legenda de permissões

| Badge | Nível de acesso |
|-------|----------------|
| `Funcionário+` | Funcionários, Gerentes e Chefe (permissão base) |
| `Gerente+` | Gerentes e Chefe |
| `Chefe` | Somente o Chefe |

---

| Código | Requisito | Tipo | Descrição | Permissão | Prioridade |
|--------|-----------|------|-----------|-----------|------------|
| RF01 | Cadastro de funcionários | Cadastro | O sistema deve permitir o cadastro de funcionários | `Gerente+` | 🔴 Alta |
| RF02 | Controle de funcionários | Controle | O sistema deve permitir a alteração dos dados dos funcionários | `Gerente+` | 🔴 Alta |
| RF03 | Cadastro de produto | Cadastro | O sistema deve permitir o cadastro de novos produtos | `Funcionário+` | 🔴 Alta |
| RF04 | Controle de produtos | Controle | O sistema deve permitir a alteração dos dados dos produtos. A alteração de preço é restrita a Gerentes e Chefe. | `Funcionário+` (dados) / `Gerente+` (preço) | 🔴 Alta |
| RF05 | Registro de compras | Operação | O sistema deve registrar as compras realizadas | `Gerente+` | 🔴 Alta |
| RF06 | Registro de vendas | Operação | O sistema deve registrar as vendas realizadas | `Funcionário+` | 🔴 Alta |
| RF07 | Consulta de inventário | Consulta | O sistema deve permitir a consulta do inventário | `Funcionário+` | 🔴 Alta |
| RF08 | Controle de compras | Controle | O sistema deve vincular compras a funcionários e fornecedores | `Funcionário+` | 🔴 Alta |
| RF09 | Controle de vendas | Controle | O sistema deve vincular vendas a funcionários e clientes | `Funcionário+` | 🔴 Alta |
| RF10 | Cadastro de categoria | Cadastro | O sistema deve permitir o cadastro de categorias de produtos | `Funcionário+` | 🔴 Alta |
| RF11 | Controle de categoria | Controle | O sistema deve permitir a alteração das categorias de produtos | `Funcionário+` | 🔴 Alta |
| RF12 | Cadastro de fornecedor | Cadastro | O sistema deve permitir o cadastro de fornecedores | `Funcionário+` | 🔴 Alta |
| RF13 | Controle de fornecedor | Controle | O sistema deve permitir a alteração dos dados de fornecedores | `Funcionário+` | 🔴 Alta |
| RF14 | Cadastro de cliente | Cadastro | O sistema deve permitir o cadastro de clientes | `Funcionário+` | 🟡 Média |
| RF15 | Controle de cliente | Controle | O sistema deve permitir a alteração dos dados de clientes | `Funcionário+` | 🟡 Média |
| RF16 | Login | Autenticação | O sistema deve permitir autenticação de usuários com controle de acesso por nível (Funcionário, Gerente, Chefe) | `Funcionário+` | 🔴 Alta |
| RF17 | Alerta de baixo estoque | Alerta | O sistema deve alertar quando o estoque estiver abaixo do mínimo definido | `Funcionário+` | 🔴 Alta |
| RF18 | Controle de permissões | Segurança | O sistema deve diferenciar os níveis de acesso: Funcionário, Gerente e Chefe, restringindo funcionalidades conforme o papel | `Chefe` | 🔴 Alta |

---

## 📌 Requisitos Não Funcionais

| Código | Requisito | Tipo | Descrição | Prioridade |
|--------|-----------|------|-----------|------------|
| RNF01 | Intuitividade | Interface | O sistema deve possuir interface intuitiva e de uso simples | 🔴 Alta |
| RNF02 | Segurança de acesso | Segurança | O sistema deve exigir autenticação por login e senha, com controle de permissões por papel (role) | 🔴 Alta |
| RNF03 | Persistência de dados | Confiabilidade | O sistema deve armazenar os dados em banco MySQL | 🔴 Alta |
| RNF04 | Integridade dos dados | Confiabilidade | O sistema deve garantir que os dados não sejam corrompidos | 🔴 Alta |
| RNF05 | Manutenibilidade | Manutenção | O código deve ser organizado e documentado | 🔴 Alta |
| RNF06 | Escalabilidade | Performance | O sistema deve suportar aumento de usuários e produtos | 🔴 Alta |
| RNF07 | Disponibilidade | Confiabilidade | O sistema deve estar disponível sempre que necessário | 🔴 Alta |
| RNF08 | Desempenho | Performance | O sistema deve responder às operações em até 10 segundos | 🔴 Alta |
| RNF09 | Compatibilidade | Tecnologia | O sistema deve ser compatível com Java e MySQL | 🔴 Alta |
| RNF10 | Atualização em tempo real | Performance | O estoque deve ser atualizado imediatamente após operações de compra e venda | 🔴 Alta |
| RNF11 | Controle de acesso por papel | Segurança | O sistema deve restringir funcionalidades sensíveis (cadastro/edição de funcionários e alteração de preços) conforme o papel do usuário | 🔴 Alta |

---
## Diagrama de Caso de Uso
<img width="579" height="922" alt="image" src="https://github.com/user-attachments/assets/4177082d-fa07-4bd3-b867-596580200371" />

---
## Diagrama de Classes 
<img width="370" height="734" alt="Captura de tela 2026-04-09 174822" src="https://github.com/user-attachments/assets/28ddc8ce-bda5-4e14-9ba4-4faa45f2079a" />


