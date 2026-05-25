Markdown
# UCanStudy - Sistema de Gestão de Estudos

O UCanStudy é uma aplicação Fullstack desenvolvida para ajudar estudantes a gerenciarem suas matérias, sessões de estudo e metas, gamificando o processo de aprendizado através de um sistema de XP e níveis.

## Tecnologias Utilizadas

### Backend
- Java 17
- Spring Boot 4.0.6
- Spring Data JPA (Hibernate)
- MySQL (Banco de dados)
- Maven (Gerenciador de dependências)

### Frontend
- React com Vite
- Axios (Integração com a API)

## 📁 Estrutura do Projeto

```text
ProjetoUCanStudy/
├── ucanstudy/           # Backend (Spring Boot)
└── ucanstudy-front/     # Frontend (React)
⚙️ Como rodar o projeto
Pré-requisitos
JDK 17+ instalado.

MySQL instalado e rodando.

Node.js instalado.

1. Configurando o Banco de Dados
Crie um banco de dados chamado ucanstudy no seu MySQL.

No arquivo ucanstudy/src/main/resources/application.properties, ajuste o usuário e senha do seu banco:

Properties
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
2. Rodando o Backend
Navegue até a pasta ucanstudy.

Execute a aplicação:

Bash
./mvnw spring-boot:run
A API estará disponível em: http://localhost:8080

3. Rodando o Frontend
Navegue até a pasta ucanstudy-front.

Instale as dependências:

Bash
npm install
Inicie o projeto:

Bash
npm run dev
O sistema estará disponível em: http://localhost:5173

Funcionalidades Implementadas
CRUD de Usuários: Cadastro, Listagem, Edição, Deleção e Consulta detalhada.

Gerenciamento de Matérias, Metas e Sessões: Controle completo de estudos.

Gamificação: Cálculo automático de XP e Nível.

Desenvolvido por Karine Cavalcanti Donato
