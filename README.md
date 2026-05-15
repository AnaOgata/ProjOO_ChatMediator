# ProjOO_ChatMediator

Chat Básico — Padrão de Projeto Mediator
Implementação de um sistema de chat simples em Java utilizando o padrão de projeto Mediator.

# Problema resolvido
Em um sistema de chat sem o padrão, cada usuário precisaria manter referências a todos os outros para enviar mensagens — resultando em alto acoplamento e dificuldade de manutenção. Com o Mediator, nenhum usuário conhece os demais: toda comunicação passa pela sala de chat.

# Estrutura do Projeto
O projeto é composto por um único arquivo ChatMediator.java com todas as classes internas:
ChatMediator.java
  Mediator (interface — contrato do mediador)
  SalaDeChat (Mediator Concreto)
  Usuario (Colega abstrato — Colleague)
  UsuarioComum (Colega Concreto)
  ChatMediator (classe principal — main)
