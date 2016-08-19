# Problema  3. 

> Tio  Ash  cansou  de  treinar pokémons e  resolveu  abrir  o  seu  próprio  negócio: vender pokébolas e itens de necessidades básicas para os novos treinadores pokémons que costumam usar seus  dispositivos  móveis  para  capturar pokémons. 

Ash  é  um  velhinho  muito  organizado  e  separou os registros de compras e vendas em vários arquivos de texto, 
nos quais cada registro tem o seguinte formato:

```sh
“C:NN” 
```
* onde  C  é  uma  letra  que  pode  ser  ‘v’  representando  uma  venda  (receita) ou  ‘c’ representando  uma  compra  (despesa)  
* NN  são  um  número  real  que  indica  o  valor  da  receita  ou despesa. 

O exemplo abaixo ilustra algumas entradas de um arquivo: 

```sh
v:5.25 
v:3.50 
c:10.00 
v:12.00 
v:7.75
```


Apesar de organizado, tio Ash não é muito bom em contas e está com dificuldades de fazer o balanço final (até porque existem muuuitos registros em cada arquivo). 
Ajude  tio  Ash  a  descobrir  qual  foi  o  seu  lucro  ou  prejuízo  total  somando  as receitas e despesas registradas em cada um dos 20 arquivos 
de registros. 

* Faça uma implementação em C  “e”  outra em JAVA utilizando threads. 