# Spring Boot property encryption
This project illustrates how to use jasypt to encrypt a spring boot property such as the spring datasource password.
The example project used is standard Camunda Spring Boot setup, the approach is generic and not specific to Camunda though.

# Encrypting the password 
The *testEncryption()* method in [Encryptor](src/main/test/org/camunda/example/Encryptor.java) can be used to 
test the setup or to generate the encrypted string.  

**JASYPTPWD** specifies the encryption Key, which will later need to be provided to the server.   
**DBPWD** is the clear text (db password) you want to encrypt.   

The test will generate the following output if the environment is working as expected:
```text
Encrypting text camundauser with password 12345
Result iWyoO2dUt3l/1aHrG8XXNYMvOELiUgwjSh4Q6UtCmk/PLuFO9kCi9NrKQPZJooJl
```

# Using the encrypted text in a property

Surrounded with *ENC(...)*, the encrypted result can be used in a spring property e.g. as shown in [application.yaml](src/main/resources/application.yaml)
```yaml
spring:   
  datasource:  
    password: ENC(zGNw1AKtGoDFza2fjXYaKJ6+ti7aene8il47urfwGnLBf6FrbswehLhK/ut6HsEp)`
```

# Starting the Server 
In order to decrypt encrypted properties the server requires the key. If the correct key is missing, the server will no longer start.
The key used to encrypt the property can be provided to the server during startup, e.g. as an environment variable.
Pass the environment variable **jasypt.encryptor.password=123456** in the run configuration of the application in your IDE or set the environment variable before running the application from Maven.

   
(replace 12345 with the key you defined if you changed it)


# Dependencies
In a Spring Boot environment adding the jasypt Spring Boot starter  is sufficient to enable the decryption 
```xml
<groupId>com.github.ulisesbocchio</groupId>
<artifactId>jasypt-spring-boot-starter</artifactId>
<version>3.0.3</version>
```

# Variations
The example can be easily adjusted to work with other encryption algorithms, without Spring Boot or Camunda or to use a key file instead (pass 'jasypt.encryptor.private-key-string' or 'jasypt.encryptor.private-key-location' as an environment variable instead).

