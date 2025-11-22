# Sistema de Adoção de Pets 🐾

Sistema de cadastro via CLI (Command Line Interface) para gerenciamento de pets disponíveis para adoção em abrigos de animais.

## 📋 Sobre o Projeto

Este projeto foi desenvolvido como parte de um desafio de programação em Java, aplicando conceitos de Orientação a Objetos, manipulação de arquivos, tratamento de exceções e boas práticas de desenvolvimento.

O sistema permite que gestores de abrigos de animais possam cadastrar, buscar, alterar e deletar informações sobre pets disponíveis para adoção.

**Desafio criado por Lucas Carrilho - @devmagro**

## 🚀 Funcionalidades

- **Cadastrar novo pet**: Registro completo de informações do animal através de formulário
- **Buscar pets**: Pesquisa por múltiplos critérios (nome, raça, idade, peso, sexo, endereço)
- **Alterar dados**: Edição de informações de pets já cadastrados
- **Deletar cadastro**: Remoção de pets do sistema
- **Listar pets**: Visualização de todos os animais cadastrados com filtros combinados

## 🛠️ Tecnologias Utilizadas

- Java
- Java IO (manipulação de arquivos)
- Sistema de arquivos (File Systems)
- Programação Orientada a Objetos
- Tratamento de Exceções

## 📁 Estrutura do Projeto

```
desafioCadastro/
├── src/
│   ├── controller/           # Controladores da aplicação
│   │   ├── MenuInicialController.java
│   │   └── PetController.java
│   ├── main/                 # Classe principal
│   │   └── Main.java
│   ├── model/                # Classes de modelo
│   │   ├── Endereco.java
│   │   ├── Pet.java
│   │   ├── Sexo.java
│   │   └── Tipo.java
│   ├── services/             # Serviços de negócio
│   │   └── PetService.java
│   ├── utils/                # Classes utilitárias
│   │   └── Utils.java
│   ├── view/                 # Camada de visualização
│   │   ├── FormularioView.java
│   │   └── MenuInicialView.java
│   └── formulario.txt        # Perguntas do cadastro
├── petsCadastrados/          # Arquivos de pets cadastrados
└── README.md
```

## 🔧 Como Executar

### Pré-requisitos

- Java JDK 11 ou superior instalado
- Terminal/Console

### Passos

1. Clone o repositório:
```bash
git clone https://github.com/emanugalindo/desafioCadastro.git
cd desafioCadastro
```

2. Compile o projeto:
```bash
javac -d bin src/**/*.java
```

3. Execute a aplicação:
```bash
java -cp bin main.Main
```

## 📝 Como Usar

### Menu Principal

1. **Cadastrar um novo pet**: Responda ao formulário com as informações do animal
2. **Alterar dados**: Busque o pet e modifique suas informações
3. **Deletar cadastro**: Busque e remova um pet do sistema
4. **Listar pets**: Visualize todos os animais cadastrados
5. **Listar pets por algum critério (idade, nome, raça)**: : Visualize todos os animais cadastrados com filtros
6. **Sair**: Encerra o programa

### Cadastro de Pets

- Responda às 7 perguntas obrigatórias do formulário
- Informações não fornecidas serão marcadas como "NÃO INFORMADO"
- O pet será salvo automaticamente em arquivo .txt

### Busca de Pets

- Selecione o tipo do animal (obrigatório)
- Combine até 2 critérios adicionais de busca
- Visualize a lista de resultados encontrados
- Buscas são case-insensitive (ignoram maiúsculas/minúsculas)

## ⚠️ Regras de Validação

### Nome
- Obrigatório nome e sobrenome
- Apenas letras A-Z (sem caracteres especiais)

### Peso
- Entre 0.5kg e 60kg
- Aceita valores decimais

### Idade
- Máximo de 20 anos
- Idades menores que 1 ano são convertidas para formato decimal (ex: 6 meses = 0.5 anos)

### Raça
- Não permite números ou caracteres especiais

### Endereço
- Informações: Rua, Número, Cidade/Bairro
- Apenas o número é opcional

### Tipo e Sexo
- Utilizam enumerações (ENUM)
- **Tipo**: Cachorro, Gato
- **Sexo**: Macho, Fêmea

## 📄 Formato dos Arquivos

### Nome do arquivo
```
AAAAMMDDTHHMM-NOMESOBRENOME.txt
```
Exemplo: `20251027T2233-FLORZINHADASILVA.txt`

### Conteúdo do arquivo
```
1 - Florzinha da Silva
2 - Gato
3 - Femea
4 - Rua 2, 456, Seilandia
5 - 6 anos
6 - 5kg
7 - Siames
```

## 👨‍💻 Autor

**Desenvolvido por Emanuel Galindo (@emanugalindo)**

---

⭐ Se este projeto foi útil, deixe uma estrela no repositório!
