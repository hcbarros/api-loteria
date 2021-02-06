# api-loteria


Endpoint base -> http://localhost:8080/loteria

POST/\
GET/\
GET/{id}\
GET/email/{email}\
DELETE/{id}\
DELETE/aposta/{id}

URL: () -> https://api-loteria-zup.herokuapp.com/loteria/


API criada com Spring boot, enviando por POST: 

{\
     email:"umEmailQualquer@email.com"
\} 

e retornando como resultado o email informado com uma sequencia de 6 numeros aleatorios de 1 a 60, representando o sorteio de numeros para uma loteria.

Se passar o mesmo email por POST, caixa alta ou baixa, retornara mais outra sequencia, exibindo as duas apostas realizadas em uma lista.

A cada email informado havera uma lista inicial com apenas um sorteio.

Um sorteio jamais tera numeros repetidos (o construtor da classe "Aposta" garante isso) e um mesmo jogador jamais tera duas sequencias identicas (o metodo addAposta chamado na classe de servico garante isso). Tambem jamais havera dois jogadores com o mesmo email.
