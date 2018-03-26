# CRUD-JDBC

### TABELAS SQL:

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

### MODELO LÓGICO:
<img src= "https://image.ibb.co/bARTFS/Sem_t_tulo.png" 
     align="left" width="100%" height="100%">
 
 
### OBSERVAÇÃO:

> O relacionamento entre **CARRO** e **VENDA** passa a ser 1,1 devido a constraint UNIQUE atribuida a chave estrangeira de **CARRO** na tabela **VENDA**, fazendo com que um **CARRO** só possua uma **VENDA**.

> Uma pessoa pode ter varios carros, porem um determinado carro so pode ser vendido uma única vez.
