@all
Feature: Simulação de seguro
  Objetivo: Realizar o cadastro de dados pessoais de usuário para simulação de plano de seguro.
  EU, usuário gostaria de gerar uma simulação de plano de seguro.

  Background:
    Given acesso o site SulAmerica Contrata Fácil

  Scenario: Tela 1 - Dados Pessoais
    Given clico na opção EU QUERO SIMULAR
    When preencher os campos:
      | Qual o seu nome completo? | Qual a sua data de nascimento? | Qual sua ocupação atual? |
      | Carlos Pereira            | 20/01/1980                     | Analista de Sistema      |
    And no botão PRÓXIMO
    Then a tela de informações de contato deve informar primeiro nome do usuário seguido da frase "podemos falar com você?"


  Scenario: Tela 2 - Informações de contato
    Given clico na opção EU QUERO SIMULAR
    When preencher os campos:
      | Qual o seu nome completo? | Qual a sua data de nascimento? | Qual sua ocupação atual? |
      | Rodrigo Viana Campos      | 20/01/2000                     | Arquiteto                |
    And no botão PRÓXIMO
    Then a tela de informações de contato deve informar primeiro nome do usuário seguido da frase "podemos falar com você?"
    When preencher os campos da tela de contato:
      |Check Ligação |Check WhatsApp |Check E-mail | Telefone (Celular) | E-mail               | Autorizo um corretor a entrar em contato comigo |
      | Sim     | Sim      | Sim    | 11998521245        | rodrigo@teste.com.br | Sim                                             |
    And no botão ENCONTRAR MEU SEGURO
    Then a tela de configuração de seguro deve ser apresentada com o nome do usuário seguido da frase "hora de personalizar seu seguro!"