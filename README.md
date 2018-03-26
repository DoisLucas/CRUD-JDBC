## CRUD-JDBC

 ***SOBRE:***
 
 >Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis justo libero, euismod sit amet maximus nec, ullamcorper sit amet leo. >Duis et mi nunc. Morbi porttitor velit eget vulputate feugiat. Proin dignissim, ipsum sed euismod efficitur, arcu leo elementum ante, >ut dapibus metus est et lorem. Integer quis gravida diam. Duis a felis et turpis facilisis lobortis finibus ut elit. Vestibulum ante >ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;

***TABELAS SQL:***

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

***MODELO LÓGICO:***
<img src= "https://image.ibb.co/bARTFS/Sem_t_tulo.png" 
     align="left" width="100%" height="100%">
 
 
***OBSERVAÇÃO:***

> O relacionamento entre **CARRO** e **VENDA** passa a ser 1,1 devido a constraint UNIQUE atribuida a chave estrangeira de **CARRO** na tabela **VENDA**, fazendo com que um **CARRO** só possua uma **VENDA**.

> Uma pessoa pode ter varios carros, porem um determinado carro so pode ser vendido uma única vez.
