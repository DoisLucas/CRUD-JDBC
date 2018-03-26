## CRUD-JDBC

 **SOBRE:**
 
O projeto consiste na criação de um sistema de venda de carros em Java utilizando JDBC para fazer a comunicação com o banco de dados PostgreSQL.

**CRUD:** (Create, Read, Update e Delete) são as quatro operações básicas utilizadas em bases de dados relacionais (RDBMS) ou em interface para utilizadores para criação, consulta, atualização e destruição de dados. 

**JDBC (Java Database Connectivity):** é um conjunto de classes e interfaces (API) escritas em Java que fazem o envio de instruções SQL para qualquer banco de dados relacional

**FUNCIONALIDADES:**
>***Carro***
>- Cadastrar carro
>- Listagem de todos carros
>- Excluir carro
>- Alterar carro
>
>***Pessoa***
>- Cadastrar pessoa
>- Listagem de todas pessoas
>- Listagem dos carros de um determinada pessoa
>- Excluir pessoa
>- Alterar pessoa
>
>***Venda***
>- Realizar venda
>- Listagem de todas vendas


**TABELAS SQL:**

```SQL
CREATE TABLE public.carro
(
  numero_chassi integer NOT NULL,
  nome character varying NOT NULL,
  cor character varying NOT NULL,
  ano integer NOT NULL,
  potencia integer NOT NULL,
  valor double precision NOT NULL,
  CONSTRAINT carro_pkey PRIMARY KEY (numero_chassi)
)
```
```SQL
CREATE TABLE public.pessoa
(
  cpf integer NOT NULL,
  rg integer NOT NULL,
  idade integer NOT NULL,
  nome character varying NOT NULL,
  CONSTRAINT pessoa_pkey PRIMARY KEY (cpf),
  CONSTRAINT pessoa_rg_key UNIQUE (rg)
)
```

```SQL
CREATE TABLE public.venda
(
  id_venda integer NOT NULL,
  data_venda character varying NOT NULL,
  id_pessoa_fk integer NOT NULL,
  id_carro_fk integer NOT NULL,
  CONSTRAINT venda_pkey PRIMARY KEY (id_venda),
  CONSTRAINT venda_id_carro_fk_fkey FOREIGN KEY (id_carro_fk)
      REFERENCES public.carro (numero_chassi) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT venda_id_pessoa_fk_fkey FOREIGN KEY (id_pessoa_fk)
      REFERENCES public.pessoa (cpf) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT venda_id_carro_fk_key UNIQUE (id_carro_fk)
)
```

**MODELO LÓGICO:**
<img src= "https://image.ibb.co/bARTFS/Sem_t_tulo.png" 
     align="left" width="100%" height="100%">
 
 
**OBSERVAÇÃO:**

> O relacionamento entre **CARRO** e **VENDA** passa a ser 1,1 devido a constraint UNIQUE atribuida a chave estrangeira de **CARRO** na tabela **VENDA**, fazendo com que um **CARRO** só possua uma **VENDA**.

> Uma pessoa pode estar em varias venda (pode ter varios carros), porém um determinado carro so pode estar em uma unica venda (só pode ser vendido uma única vez).

>A estrutura do projeto e as credenciais do banco deve ser trocadas de acordo com as suas necessidades, assim como a importação da biblioteca JDBC do banco desejado.
