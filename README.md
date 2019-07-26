# Server and Rich Client vulnerable to Java Deserialization Attacks

## Run Server

```bash
docker run --rm  -p 8081:8080 marmelatze/vuln-server 
```

Browse http://localhost:8081/serialization/index.action

## Run Client

Download JAR from [Releases](https://github.com/Marmelatze/vuln-server/releases)

Run with

```bash
java -jar client.jar
```

The client connects to localhost:8081 and has proxy localhost:8081 preconfigured.
