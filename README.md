Exchange App

Este é um aplicativo Android desenvolvido em Kotlin, seguindo a arquitetura MVVM (Model-View-ViewModel), com princípios de Clean Architecture e SOLID. Este projeto foi inspirado em um exemplo simples (disponível em "https://github.com/codeWithCal/FetchDataFromWebAndroid"), onde identifiquei oportunidades de melhorias e adaptei-as para este aplicativo.

Melhorias a serem Realizadas:
Utilizar o Koin para instanciação da ViewModel: Integrar o Koin ao projeto para gerenciar a injeção de dependência e facilitar a instanciação da ViewModel, garantindo uma abordagem mais limpa e desacoplada.

Implementar tratamento de erros na ViewModel: Desenvolver um tratamento de erros na ViewModel para lidar com situações inesperadas e fornecer feedback adequado para o usuário, melhorando a robustez e a experiência do aplicativo.

Extrair "magic strings" do código: Identificar e extrair "magic strings", como "brl", do código-fonte para melhorar a manutenibilidade e facilitar a localização do aplicativo.

Como Contribuir:
Se você deseja contribuir para este projeto, siga estas etapas:

Faça um fork do repositório e clone-o em sua máquina local.
Crie uma branch com um nome descritivo (git checkout -b nome-da-sua-branch).
Faça suas alterações e comente-as de forma clara e concisa.
Envie suas alterações (git push origin nome-da-sua-branch).
Abra um pull request explicando as alterações propostas.
Tecnologias Utilizadas:
Kotlin
MVVM
Clean Architecture
SOLID
Koin
Autor:
André Reis da Silva

Licença:
Este projeto está licenciado sob a Licença MIT - consulte o arquivo LICENSE.md para mais detalhes.
