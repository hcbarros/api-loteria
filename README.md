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
     email: 'umEmailQualquer@email.com'\
} 

e retornando como resultado o email informado com uma sequência de 6 números aleatórios de 1 a 60, representando o sorteio de números para uma loteria.

Se passar o mesmo email por POST, caixa alta ou baixa, retornará mais outra sequência, exibindo as duas apostas realizadas em uma lista.

A cada email informado haverá uma lista inicial com apenas um sorteio.

Um sorteio jamais terá números repetidos (o construtor da classe "Aposta" garante isso) e um mesmo jogador jamais terá duas sequências idênticas (o método addAposta chamado na classe de serviço garante isso). Também jamais haverá dois jogadores com o mesmo email.
