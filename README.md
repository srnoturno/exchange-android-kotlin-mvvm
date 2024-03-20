# Currency Converter App

Este é um aplicativo simples de conversão de moedas que permite aos usuários visualizar as cotações de uma moeda base em relação a outras moedas. A aplicação é desenvolvida em Kotlin e utiliza uma variedade de tecnologias modernas para proporcionar uma experiência fluida aos usuários.

## Funcionalidades

- Inicia utilizando a cotação em BRL como base.
- Exibe cards de cotação da moeda base em outras moedas.
- Permite que o usuário selecione outras moedas bases.
- Permite que o usuário atualize para receber cotações mais recentes.
- Bloqueia a tela durante as consultas e exibe um indicador de carregamento.

## Instruções para Utilização

1. Clone o projeto para sua máquina local.
2. Build e execute o projeto utilizando o Android Studio ou através da linha de comando.
3. O projeto está desenvolvido em Kotlin 1.9 e funciona bem em diversas versões do Android. Recomenda-se utilizar pelo menos o Android 13 (API 33).
4. Certifique-se de ter o Android Studio versão "Iguana | 2023.2.1" instalado.

## Arquitetura e Tecnologias Utilizadas

- **MVVM:** Utilizada para separar claramente as responsabilidades entre a lógica de negócios e a interface do usuário.
- **Clean Redux:** Adotada para manter o código limpo e facilitar a escalabilidade do projeto.
- **Kotlin:** Linguagem de programação moderna e concisa.
- **Ktor:** Framework para criação de clientes e servidores HTTP em Kotlin.
- **Coroutines:** Utilizadas para facilitar a programação assíncrona e concorrente.
- **Compose:** Utilizado para construção de interfaces de usuário declarativas.
- **JUnit:** Framework de teste para Java e Kotlin.
- **MockK:** Biblioteca de mocking para Kotlin.

## Pontos a Melhorar

- Adicionar injeção de dependências com Koin ou Hilt para melhorar a modularidade e a testabilidade do código.
- Implementar testes de UI com a lib do Compose Test para garantir a qualidade e robustez das interfaces de usuário.
- Reduzir o acoplamento entre as telas, visando torná-las mais reutilizáveis e facilitar a manutenção futura. Por exemplo, a componenteização das telas pode ser realizada, como no caso do `CurrencyCard`, que poderia receber dois atributos ("título" e "subtítulo") para facilitar seu reaproveitamento em outras partes do aplicativo.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests para melhorar este projeto.

---

Este projeto é desenvolvido e mantido por [André Reis].
