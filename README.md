# Gestion des Réservations de Voyage

## Description
Ce projet est une application web de gestion des réservations de voyage développée avec **Java Spring Boot** et **Thymeleaf**. L'application permet de gérer les réservations, les clients, les transports et les voyages. Elle inclut des fonctionnalités CRUD pour chaque entité ainsi que des filtres pour affiner les recherches.

## Technologies utilisées
- **Backend** : Java 21+, Spring Boot
- **Frontend** : Thymeleaf, Bootstrap, CSS
- **Base de données** : MySQL
- **Outils** : Maven, NetBeans/XAMPP

## Dépendances
Le projet utilise les dépendances suivantes :
- **Spring Web** : Gestion des routes et services HTTP
- **Spring Data JPA** : Interaction avec la base de données
- **Thymeleaf** : Moteur de templates HTML
- **Spring Validation** : Validation des données utilisateurs
- **SQL Driver** : Connexion à MySQL

## Structure du projet
```
src
│── main
│   ├── java
│   │   ├── Controller (Gestion des requêtes HTTP)
│   │   ├── Entity (Modèles de données)
│   │   ├── Repository (Accès aux données)
│   ├── resources
│   │   ├── static
│   │   │   ├── img (Images du projet)
│   │   │   ├── js (Scripts JavaScript)
│   │   │   ├── style (Fichiers CSS)
│   │   ├── templates
│   │   │   ├── Client (Vues des clients)
│   │   │   ├── Reservation (Vues des réservations)
│   │   │   ├── Transport (Vues des transports)
│   │   │   ├── Voyage (Vues des voyages)
│   │   │   ├── index.html (Page d'accueil)
```
Le **diagramme UML des classes** sera disponible à la racine du repository.

## Fonctionnalités principales
✅ Gestion complète du CRUD pour les entités :
- **Clients**
- **Réservations**
- **Transports**
- **Voyages**

✅ Système de filtrage des données pour une recherche optimisée
✅ Interface utilisateur responsive avec **Bootstrap** et **CSS**

## Installation
### Prérequis
- Java 21+
- Maven 3.8+
- MySQL (via XAMPP ou autre serveur de base de données)
- NetBeans ou un IDE compatible avec Spring Boot

### Étapes
1. **Cloner le repository**
```sh
git clone https://github.com/geffred/AgenceVoyageApp.git
```
2. **Configurer la base de données**
- Assurez-vous que MySQL est en cours d'exécution.
- Créez une base de données nommée `agence`.
- Modifiez le fichier `application.properties` avec vos identifiants MySQL :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/agence?autoReconnect=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=
```
3. **Exécuter l'application**
```sh
mvn spring-boot:run
```

## Utilisation
Une fois l'application démarrée, ouvrez votre navigateur et accédez à :
```
http://localhost:8080/
```

## Contribuer
Les contributions sont les bienvenues ! Pour toute amélioration ou correction :
1. Forkez le projet
2. Créez une branche (`feature/amélioration`)
3. Faites un commit de vos modifications
4. Ouvrez une Pull Request

## Licence
Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus d’informations.

