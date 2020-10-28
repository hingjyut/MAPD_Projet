# MAPD_Projet

**********************************************************
* MAPD - Réseau de pétri				 *
* V0 du 28/10.20					 *
* Authors : X.ZHANG, R.VOYER	 			 *
**********************************************************

##Le dossier  contient :

### -> 4 repertoires :
	- bin pour le stockage des .class
	- docs pour le stockage de la documentation des fichiers, la javadoc
	- src pour les fichiers source du projet en .java
	- tools pour les differents scripts 

### -> 5 scripts :
	- cleanAll pour effacer la javadoc et les .class contenus dans les repertoires bin/ et docs/
	- compile pour compiler les fichiers .java contenu dans src/ 
	- genDoc pour génerer la javadoc des fichiers .java
	- runTests pour executer les auto-tests du logiciel 
	- genDeliverable pour le fichier .tar.gz contenant tous les fichiers et scripts vu ci-dessus


### Utilisation : 
Dans le dossier tools ouvrez un terminal.

Pour lancer une simulation executer la commande(il n'y a pas de code de tests actuellement) : 
```terminal
sh simulation
```
Pour lancer les tests de code (il n'y a pas de code de tests actuellement) executer la commande : 
```terminal
sh runTests
```
Pour compiler le code excuter la commande : 
```terminal
sh compile
```
Pour generer la javadoc executer la commande : 
```terminal
sh genDoc
```

Pour effacer les fichier .class et javadoc executer la commande : 
```terminal
sh cleanAll
```

Pour compresser ce dossier excuter la commande : 
```terminal
sh genDeliverable
```
