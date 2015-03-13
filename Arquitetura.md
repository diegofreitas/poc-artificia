# Domain Driven Design #

O sistema será desenvolvido de forma orientada a objetos  seguindo os principios do ddd

# Princípios e Padrões #

**As entidades de negócio que são persistidas não seguem o padrão javabens(getters e setters), pois quebra o princípio de encapsulamento da OO. Somente o próprio objeto sabe o que fazer com seu estado.**


# Frameworks e Ambiente de desenvolvimento #

Segue a lista de ferramentas usadas no desenvolvimento do projeto.
**Struts** Spring
**JPA / Hibernate** hsqldb
**JUnit** Mockito
**DBUnit** Maven
**Jetty** Eclipse

# Estrutura do projeto #

A estrutura do projeto esta dividida em dois artefatos, um projeto que contém as interfaces do sistema e o projeto web de implementacao. O objetivo dessa estrutura é de reforçar o principio de desenvolvimento orientado a interface, assim como é feito nas diversas especificações do java.