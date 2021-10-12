# Getting Started

### Demarrage de postgresql et redis

```
docker-compose up -d db redis
```

### Compilation

```
./gradlew clean build
```

### Lancement des tests unitaires

```
./gradlew test
```

### Demarrage du projet

```
./gradlew flywayClean
./gradlew flywayMigrate
./gradlew bootRun
```
