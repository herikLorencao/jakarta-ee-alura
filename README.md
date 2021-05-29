## Criando usuário no WindFly

- Acesse a pasta wildfly-20.0.1.Final\bin.
- Execute, via CMD, o add-user.bat
- Escolha o tipo Management User (Usuário de Administração)
- Escolha o username de sua preferência
- Defina a senha e a confirme
- Não é necessário definir grupos, então só é necessário apertar enter
- Insira yes para incluir o usuário ao ManagementRealm
- Insira yes para permitir que o usuário possa acessar outros AS Process

## Adicionando DataSource no WindFly (Jboss-cli)

```bash 
module add --name=com.mysql --resources="/home/herik/Downloads/mysql-connector-java-8.0.20.jar" --dependencies=javax.api,javax.transaction.api
```

```bash 
/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)
```

## Adicionando Fila com JMS

```bash
jms-queue add --queue-address=EmailQueue --entries=java:/jms/queue/EmailQueue
```