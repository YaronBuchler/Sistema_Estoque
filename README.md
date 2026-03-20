# 📦 Sistema de Controle de Estoque

Sistema desenvolvido para gerenciamento de produtos, fornecedores, clientes e movimentações de estoque.

---

## 📋 Requisitos Funcionais 

| Código | Requisito | Tipo Funcional | Descrição | Prioridade |
|--------|----------|----------------|-----------|------------|
| RF01 | Cadastro de funcionários | Cadastro | O sistema deve permitir o cadastro de funcionários | 🔴 Alta |
| RF02 | Controle de funcionários | Controle | O sistema deve permitir a alteração dos dados dos funcionários | 🔴 Alta |
| RF03 | Cadastro de produto | Cadastro | O sistema deve permitir o cadastro de novos produtos | 🔴 Alta |
| RF04 | Controle de produtos | Controle | O sistema deve permitir a alteração dos dados dos produtos | 🔴 Alta |
| RF05 | Registro de compras | Operação | O sistema deve registrar as compras realizadas | 🔴 Alta |
| RF06 | Registro de vendas | Operação | O sistema deve registrar as vendas realizadas | 🔴 Alta |
| RF07 | Consulta de inventário | Consulta | O sistema deve permitir a consulta do inventário | 🔴 Alta |
| RF08 | Controle de compras | Controle | O sistema deve vincular compras a funcionários e fornecedores | 🔴 Alta |
| RF09 | Controle de vendas | Controle | O sistema deve vincular vendas a funcionários e clientes | 🔴 Alta |
| RF10 | Cadastro de categoria | Cadastro | O sistema deve permitir o cadastro de categorias de produtos | 🔴 Alta |
| RF11 | Controle de categoria | Controle | O sistema deve permitir a alteração das categorias de produtos | 🔴 Alta |
| RF12 | Cadastro de fornecedor | Cadastro | O sistema deve permitir o cadastro de fornecedores | 🔴 Alta |
| RF13 | Controle de fornecedor | Controle | O sistema deve permitir a alteração dos dados de fornecedores | 🔴 Alta |
| RF14 | Cadastro de cliente | Cadastro | O sistema deve permitir o cadastro de clientes | 🟡 Média |
| RF15 | Controle de cliente | Controle | O sistema deve permitir a alteração dos dados de clientes | 🟡 Média |
| RF16 | Login | Autenticação | O sistema deve permitir autenticação de usuários | 🔴 Alta |
| RF17 | Baixo estoque | Alerta | O sistema deve alertar quando o estoque estiver abaixo do mínimo | 🔴 Alta |

---

## 📌 Requisitos Não Funcionais 

| Código | Requisito | Tipo | Descrição | Prioridade |
|--------|----------|------|-----------|------------|
| RNF01 | Intuitividade | Interface | O sistema deve possuir interface intuitiva e de uso simples | 🔴 Alta |
| RNF02 | Segurança de acesso | Segurança | O sistema deve exigir autenticação por login e senha | 🔴 Alta |
| RNF03 | Persistência de dados | Confiabilidade | O sistema deve armazenar os dados em banco MySQL | 🔴 Alta |
| RNF04 | Integridade dos dados | Confiabilidade | O sistema deve garantir que os dados não sejam corrompidos | 🔴 Alta |
| RNF05 | Manutenibilidade | Manutenção | O código deve ser organizado e documentado | 🔴 Alta |
| RNF06 | Escalabilidade | Performance | O sistema deve suportar aumento de usuários e produtos | 🔴 Alta |
| RNF07 | Disponibilidade | Confiabilidade | O sistema deve estar disponível sempre que necessário | 🔴 Alta |
| RNF08 | Desempenho | Performance | O sistema deve responder às operações em até 10 segundos | 🔴 Alta |
| RNF09 | Compatibilidade | Tecnologia | O sistema deve ser compatível com Java e MySQL | 🔴 Alta |
| RNF10 | Atualização em tempo real | Performance | O estoque deve ser atualizado imediatamente após operações | 🔴 Alta |
