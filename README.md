# Projet_7

##BDD
**configuration des bases de données :**

les blobs de restoration sont dans le dossier \Projet_7\script_bdd

ils y a deux bases de données a créer :
<ul>La base de donnée principale : biblioc_bdd utiliser le fichier biblioc_db.sql</ul>
<ul>La base de donnée du batch : batch_db utiliser le fichier batch_db.sql</ul>

un role d'acces est a configurer, dans les configuration.properties sur github les modification sont a faire sur les lignes : <br>

spring.datasource.username= admin_biblioc<br>
spring.datasource.password= admin<br>
<br>
Sinon créer les roles dans la base de donnée.

## SPRING CLOUD CONFIG

**configuration de cloud config :**

les fichiers .properties sont disponibles

<ul>sur <a href="https://github.com/benzouille/biblioc-config">github.com/benzouille/biblioc-config</a></ul>

Vous devez modifier la boite mail dans le fichier biblioc-batch.properties afin d'y mettre votre adresse email

##ordre de lancement des micro-services :
Lancer les micros -services dans l'ordre suivant :
<ul>ConfigServerApplication</ul>
<ul>EurekaServerApplication</ul>
<ul>BibliocAuthentificationApplication</ul>
<ul>BibliocBibliothequeApplication</ul>
<ul>BibliocReservationApplication</ul>
<ul>BibliocUtilisateurApplication</ul>
<ul>BibliocClientUiApplication</ul>
<ul>BibliocBatchApplication</ul>

## Lancement des micros services :

Aller dans chaques microservices : <br>
biblioc/biblioc-"MICROSERVICE"/src/main/java/fr.banane.biblioc"MICROSERVICE"/"MICROSERVICE"Application<br>
 puis RUN.

Sous intellij onglet Service en bas et démarrer les micros-services dans l'ordre ci-dessus.