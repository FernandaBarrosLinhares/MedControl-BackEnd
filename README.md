<center># Med Control

Desenvolvido pela LabMedication, o **MedControl**, é um software de gestão para uso em hospitais, clinicas e similares da área da saúde.
Com ele, o **administrador**, pode gerenciar quais usuários realizaram o atendimento, através de logs com mensagens e horários que foram realizados.
**Médicos** e **enfermeiros**, podem realizar: **cadastros**, **edições**,**listagem** e **edição** das **Consultas**, **Dietas**, **Exercícios**, **Medicamentos**, **Exames** atrelados aos **Pacientes**.
O modelo segue padrão **white-label** consiste em um software que pode ser personalizado com as cores, tipografias, logotipos e demais elementos visuais da identidade do cliente, proporcionando um resultado personalizado, podendo ser comercializado em todo o país.


## Tecnologia ultilizadas

<img alt="SpringBoot" src="	https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" width="50px">
<img alt="Java" src="	https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" width="50px">
<img alt="PgAdmin" src="https://user-images.githubusercontent.com/25181517/117208740-bfb78400-adf5-11eb-97bb-09072b6bedfc.png" width="50px">
<img alt="Maven" src="https://user-images.githubusercontent.com/25181517/117207242-07d5a700-adf4-11eb-975e-be04e62b984b.png" width="50px">
<img alt="Logomarca" src="https://user-images.githubusercontent.com/25181517/192109061-e138ca71-337c-4019-8d42-4792fdaa7128.png" width="50px">
<img alt="Logomarca" src="https://user-images.githubusercontent.com/25181517/121401671-49102800-c959-11eb-9f6f-74d49a5e1774.png" width="50px">
<img alt="Logomarca" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" width="50px">
<img alt="Logomarca" src="https://user-images.githubusercontent.com/25181517/192108374-8da61ba1-99ec-41d7-80b8-fb2f7c0a4948.png" width="50px">



## 
|[Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)|
|[Java](https://docs.oracle.com/en/java/)|
|[PgAdmin](https://www.pgadmin.org/docs/)|
|[Maven](https://maven.apache.org/guides/index.html)|
|[Postman](https://learning.postman.com/docs/publishing-your-api/documenting-your-api/)|
|[Git](https://github.com/FullStack-Itacorubi/M3P-BackEnd-Squad1)|

## Dependências

-SpringData JPA

-SpringWeb

-PostgressSql Driver

-Lombok

-Validation

## Para realizar a execução do projeto, siga os seguintes passos

**Clonar o projeto** 

https://github.com/FullStack-Itacorubi/M3P-BackEnd-Squad1

**Instalar as dependências**

*npm install*

**Rodar servidor de desenvolvimento local**

*ng serve*

## Usuarios

**Login do Usuário: HTTP POST no path /api/usuarios/login**

`{
"email":"joao1.silva@email.com",
"senha":"12345678"
}
`

**Resetar Senha do Usuário: HTTP PATCH no path /api/usuarios/res**

`
{
"id":3,
"email":"joao.silva@email.com",
"senha":"000002"
}
`

**Cadastro de um novo Usuário: HTTP POST no path /api/usuarios**

`
{
"nomeCompleto": "Igor algusto",
"genero": "MASCULINO",
"cpf": "25993185058",
"telefone": "11999999999",
"email": "igor@email.com",
"status": false,
"senha": "12345678",
"tipoUsuario": "MEDICO"
}
`

**Alteração de um Usuário por Identificador: HTTP PUT no path /api/usuarios/{id}**

`
{
"nomeCompleto": "Joana da mata",
"genero": "FEMININO",
"telefone": "11999999998",
"senha": "0000010",
"tipoUsuario": "ADMINISTRADOR",
"status": false
}
`

**Busca de todos os Usuários: HTTP GET no path /api/usuarios**

**Buscar Usuário por Identificador: HTTP GET no path /api/usuarios/{id}**

**Deletar Usuário por Identificador: HTTP DELETE no path /api/usuarios/{id}**

## Pacientes

Cadastro de um novo Paciente: HTTP POST no path /api/pacientes

`
{
"nomeCompleto": "Ricardo Tavares",
"genero": "MASCULINO",
"cpf": "74980121039",
"telefone": "48000000000",
"email": "ricardo@example.com",
"status": false,
"dataNascimento": "10/10/2023",
"rg": "123456789",
"estadoCivil": "CASADO",
"naturalidade": "Naturalidade",
"contatoEmergencia": "11223344556",
"alergias": "Alergias específicas",
"cuidadosEspecificos": "Cuidados específicos",
"convenio": "Convênio Médico",
"numeroConvenio": "12345",
"validadeConvenio":"10/202",
"endereco": {
"cep": "12345000",
"cidade": "Rio negro",
"estado":"Rrio grande do sul",
"logradouro": "Rua Exemplo",
"numero": 10,
"complemento": "Complemento",
"bairro": "Bairro Exemplo",
"referencia": "Referência"
}
}
`

Atualização de um paciente por Identificador: HTTP PUT no path /api/pacientes/{id}

`
{
"nomeCompleto": "Felisberto",
"genero": "MASCULINO",
"cpf": "07861051943",
"telefone": "12345678901",
"email": "felisberto@example.com",
"status": false,
"dataNascimento": "01/01/2000",
"rg": "123456789",
"estadoCivil": "CASADO",
"naturalidade": "Naturalidade",
"contatoEmergencia": "11223344556",
"alergias": "Alergias específicas",
"cuidadosEspecificos": "Cuidados específicos",
"convenio": "Convênio Médico",
"numeroConvenio": "12345",
"validadeConvenio": "12/2023",
"endereco": {
"id":5,
"cep": "12.345-000",
"cidade": "Rio negro",
"estado":"Santa Catarina",
"logradouro": "Rua Exemplo",
"numero": 10,
"complemento": "Complemento",
"bairro": "Bairro Exemplo",
"referencia": "Referência"
}
}
`

Retorna todos os Pacientes: HTTP GET no path /api/pacientes

Retorna Paciente por Identificador: HTTP GET no path /api/pacientes/{id}

Deleta Paciente por Identificador: HTTP DELETE no path /api/pacientes/{id}


## Consultas

Cria uma nova Consulta: HTTP POST no path /api/consultas

`
{
"motivo": "Motivo da consulta deve ser informado",
"data": "10/10/2023",
"horario": "10:10:10",
"descricao": "Campo descrição deve conter de 16 a 1024 caracteres",
"indicadorMedicacao": "Campo indicador de medicação deve ser obrigatório",
"dosagensPrecaucoes": "Campo dosagens e precauçoes deve ser obrigatório e deve conter de 16 a 256 caracteres",
"status": true,
"paciente": {
"id":2
},
"usuario": {
"id":2
},
"medicamento":{
"id":2
}
}
`

Atualiza uma Consulta: HTTP PUT no path /api/consultas/{id}

`
{
"motivo": "Motivo da consulta deve ser informado",
"data": "10/10/2023",
"horario": "10:10:10",
"descricao": "Campo descrição deve conter de 16 a 1024 caracteres",
"indicadorMedicacao": "Campo indicador de medicação deve ser obrigatório",
"dosagensPrecaucoes": "Campo dosagens e precauçoes deve ser obrigatório e deve conter de 16 a 256 caracteres",
"status": false,
"medicamento":{
"id":1
}
}
`


Retorna as Consultas pelo Paciente: HTTP GET no path /api/consultas

Retorna Consulta por Identificador: HTTP GET no path /api/consultas/{id}

Deleta Consulta por Identificador: HTTP DELETE no path /api/consultas/{id}

## Exames

Cadastro de um novo Exame: HTTP POST no path /api/exames

`
{
"nome": "Exame de Sangue",
"data": "21/10/2023",
"horario": "08:30:00",
"tipo": "Hemograma",
"laboratorio": "Laboratório ABC",
"url_documento": "https://www.exemplo.com/documento",
"resultado": "Os resultados do exame estão dentro dos valores normais.",
"status": false,
"paciente": {
"id": 3
}
}
`

Atualiza um Exame pelo Identificador: HTTP PUT no path /api/exames/{id}

`
{
"nome": "Exame de Sangue",
"data": "21/10/2023",
"horario": "08:30:00",
"tipo": "Hemograma",
"laboratorio": "Laboratório ABC",
"url_documento": "https://www.exemplo.com/documento",
"resultado": "Os resultados do exame estão dentro dos valores normais.",
"status": false
}
`

Retorna Exames por Paciente: HTTP GET no path /api/exames

Retorna Exame por Identificador: HTTP GET no path /api/exames/{id}



## Medicamentos

Cadastra um novo Medicamento: HTTP POST no path /api/medicamentos

`
{
"nome": "Paracetamol",
"data": "21/10/2023",
"horario": "08:30:00",
"tipo": "COMPRIMIDO",
"quantidade": 20.5,
"unidade": "MG",
"observacao": "Tomar um comprimido a cada 6 horas para reduzir a febre.",
"status": false
}
`

Atualiza um Medicado pelo Identificador: HTTP PUT no path /api/medicamentos/{id}

`
{
"nome": "Paracetamol",
"tipo": "COMPRIMIDO",
"quantidade": 20.5,
"unidade": "MG",
"observacao": "Tomar um comprimido a cada 6 horas para reduzir a febre.",
"status": false
}
`

Retorna Medicamentos por Usuário: HTTP GET no path /api/medicamentos

Retorna Medicamento por Identificador: HTTP GET no path /api/medicamentos/{id}

Deleta Medicamento por Identificador: HTTP DELETE no path /api/medicamentos/{id}

## Dietas

Cadastro de uma nova Dieta: HTTP POST no path /api/dietas

`
{
"nome": "Paracetamol",
"tipo": "COMPRIMIDO",
"quantidade": 20.5,
"unidade": "MG",
"observacao": "Tomar um comprimido a cada 6 horas para reduzir a febre.",
"status": false
}
`

Atualiza Dieta pelo Identificador: HTTP PUT no path /api/dietas/{id}

`
{
"nome": "Dieta proteica",
"tipoDieta": "DUKAN",
"descricao": "Uma dieta rica folha.",
"status":false
}
`
Busca de Dietas por Paciente: HTTP GET no path /api/dietas

Retorna Dieta por Identificador: HTTP GET no path /api/dietas/{id}

Deleta Dietas por Identificador: HTTP DELETE no path /api/dietas/{id}

## Exercicios

Cadastro de um novo Exercício: HTTP POST no path /api/exercicios

`
{
"nome": "Exercicio para perda de peso",
"data": "20/10/2023",
"horario": "14:30:00",
"tipoExercicioEnum": "RESISTENCIA_MUSCULAR",
"quantidadePorSemana": 3,
"descricao": "Paciente com alto IMC.",
"status": false,
"paciente": {
"id": 1
}
}
`

Atualiza Exercício pelo Identificador: HTTP PUT no path /api/exercicios/{id}

`
{
"nome": "Exemplo de Exercício",
"tipoExercicioEnum": "RESISTENCIA_AEROBICA",
"descricao": "Esta é uma descrição de exemplo para um exercício. Deve conter pelo menos 10 caracteres.",
"status": false,
"quantidadePorSemana": 3
}
`

Busca de Exercícios por Paciente: HTTP GET no path /api/exercicios

Retorna Exercício por Identificador: HTTP GET no path /api/exercicios/{id}

Deleta o Exercício por Identificador: HTTP DELETE no path /api/exercicios/{id}



## Prontuário

Lista todos os Prontuários: HTTP GET no path /api/prontuarios

`
{
"motivo": "Consulta de Rotina",
"data": "21/10/2023",
"horario": "15:30:00",
"descricao": "Este é um exemplo de descrição da consulta.",
"indicadorMedicacao": "Sim",
"dosagensPrecaucoes": "Tomar 1 comprimido de 500mg a cada 6 horas.",
"status": false,
"paciente": {
"id": 1
},
"usuario": {
"id":2
},
"medicamento": {
"id": 2
}
}
`

Busca todos os Prontuários por Paciente: HTTP GET no path /api/prontuarios

## Logs

Endpoint de Listagem de Registros(logs): HTTP GET no path /api/logs

## Estatísticas

Endpoint de Listagem de Estatísticas: HTTP GET no path /api/estatisticas


## Melhoria a serem aplicadas

*Implementar Security*

*Implementar o sistema de roles*

*Realizar teste unitário e teste de integração*

*Uso do Docker*

*Desenvolver uma tabela única de usuário, incluindo paciente*

## Desenvolvedores


- Eduardo Veras (https://github.com/CesarVeras)

- Fernanda Linhares (https://github.com/FernandaBarrosLinhares)

- Geonani Andrade de Oliveira (https://github.com/G3ovani-Andrade)

- Isaque Scheidt (https://github.com/isaque-sch)

- Jose Francisco Dupuy Patella(https://github.com/josepatella)
