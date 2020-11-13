# MAPD_Projet

**********************************************************
* MAPD - Réseau de pétri				 
* V0 du 28/10.20					 
* Authors : X.ZHANG, R.VOYER	
* IDE: Eclipse 2018-09
* OS: Ubuntu 20.04
* Java version: Java 8
**********************************************************

## Le dossier  contient :

### -> 3 repertoires :
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

### Amélioration de ce projet :
	- Le patron de conception de Factory est pour créer des nouveaux arcs
	- Le patron de conception de singleton limite le nombre de reseau, il n'existe qu'un réseau dans la simulation.
	- Ajouter des méthodes pour supprimer des arcs, des places et des transitions. En fait nous n'avons pas réussi de détruire des objets, on supprime des références pour des objets utilisés, la mécanisme de collecte des déchets en java nous aide de supprime des objets.

### Utilisation : 

Après git clone ce projet dans votre ordi, l'importer en Eclipse, vous pouvez lancer le test par le fichier *TestMain.java* dans /src/mapd/tests/


### Quelques notes :

Au début, le dossier tools sert à générer des docs et lancer le projet, mais ça l'air plus facile si on les fait avec IDE eclipse.

Dans le dossier **tools** ouvrez un terminal.

Pour générer la javadoc executer la commande : 
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
