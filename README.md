# Projet API

## Objectif du projet

Ce projet consiste à la création d'une API Restful afin de permettre à un organisme bancaire de gérer ses demandes de crédit.

## Architecture et technologies

Ce projet est un projet maven dans lequel le framework Spring Boot a été utilisé afin de mettre en place une architecture micro services composée de deux services. 

En ce qui concerne la base de données, j'ai choisi d'utiliser une base de données H2, étant donné que nous avions déjà mis en place une base H2 pendant les TPs. Cette base tourne sur un serveur H2 qui peut être lancé en téléchargeant le .jar se trouvant à la racine du repo et en exécutant la commande suivante dans le dossier où le fichier a été téléchargé :
`java -cp h2-2.2.224.jar org.h2.tools.Server -tcp -web -ifNotExists`

Dans chacun des micros services l'on retrouve l'arborescence suivante :
- Controller : contient les classes controller qui traitent les requêtes
- Exceptions : contient les exceptions créées pour gérer des erreurs métier
- Model : contient les classes qui vont représenter les objets du domaine, mappés à la base de données
- Repository : contient la couche abstraite d'accès aux données grâce à des interfaces JPA
- Service : contient les classes qui encapsulent la logique métier et qui servent d'intermédiaire entre les controllers et les données.
- Utils : contient les énumérations spécifiques au statut d'une demande de crédit
- Resources : contient la configuration de l'application ainsi que les script de création et de population de base de données
- ApplicationMain : classe permettant de lancer le micro service
## Micro services

Deux micros services ont donc été mis en place dans ce projet.

Le premier micro service, appelé ***clients*** permet de gérer les clients ainsi que leurs demandes de crédit.

Pour la gestion des clients, les requêtes sont mappées sur le chemin `/api/clients` et offrent les fonctionnalités suivantes :
- Récupérer tous les clients
- Récupérer un client en particulier depuis son id `/{id}`
- Créer un client
- Mettre à jour un client depuis son id `/{id}`
- Supprimer un client depuis son id `/{id}`

Les requêtes concernant les demandes de crédits sont quant à elles mappées sur le chemin `/api/creditdemands` et offrent les fonctionnalités suivantes :
- Récupérer toutes les demandes de crédit
- Créer une demande de crédit [NON FONCTIONNEL]
- Mettre à jour une demande de crédit [NON FONCTIONNEL]
- Supprimer une demande de crédit depuis son id `{id}`
- Annuler sa demande de crédit depuis son id `{id}/cancel` -> change le statut de la demande à "Refusée"


Le deuxième micro service, appelé ***public-finance*** permettent de gérer de façon avancée les demandes de crédit ainsi que les conseillers bancaires. 

Pour la gestion des conseillés bancaires, les requêtes sont mappées sur le chemin `/api/bankadvisors` et offrent les fonctionnalités suivantes :
- Récupérer tous les conseillers
- Récupérer un conseiller en particulier depuis son id `{id}`
- Créer un conseiller
- Mettre à jour un conseiller depuis son id `{id}`
- Supprimer un conseiller `{id}`

Les requêtes concernant la gestion avancée des demandes de crédits sont quant à elles mappées sur le chemin `/api/creditdemands/operations` et offrent les fonctionnalités suivantes :
- Assigner une demande à un conseiller `{creditDemandId}/link/{bankAdvisorId}`
- Etudier une demande `{creditDemandId}/review`
- Valider une demande `{creditDemandId}/validation`
- Accepter une demande `{creditDemandId}/accept`
- Refuser une demande `{creditDemandId}/review`

Dans liens HATEOAS sont mis en place en réponse à l'ensemble de ces requêtes, ce qui a surtout été utile pour indiquer le workflow a suivre entre les différents statuts possibles pour une demande. En effet, lorsqu'on assigne une demande à un conseiller, on propose la route vers l'étude de la demande. Quand on étudie une demande, on propose la route vers la validation de la demande, et ainsi de suite.

En supplément des liens HATEOAS, je fais une vérification du changement de statut pour les fonctionnalités de gestion des demandes de crédit citées plus haut. En contrôlant les transitions d'état illicite, je m'assure que la procédure est bien respectée. Dans le cadre d'un organisme bancaire, c'est important à mettre en place déjà par rapport à l'image donnée au client mais aussi pour de simples raison d'organisation et d'historisation.

## Circuit breaker

J'ai également pu implémenter dans ce projet un circuit breaker. Son implémentation est très légère, mais démontre un bon exemple d'à quel point cela pourrait être utile à une plus grande échelle. J'ai appliqué ce circuit breaker sur la méthode permettant d'assigner un conseiller à une demande de crédit. Ainsi, si pour une raison quelconque cette méthode ne peut pas s'exécuter correctement, on a une méthode fallback qui va retourner une exception spécifique à ce cas là. C'est une implémentation très basique à ce niveau là, il serait plus intéressant de proposer dans cette méthode fallback la dernière image capturée des demandes et des conseillers pour tout de même mettre en attente des assignations qui seront exécutée à la reprise du service. Bien évidemment dans ce cas là, il faudrait mettre en place un système permettant de gérer les assignations multiples à une même demande de crédit pendant le downtime du service, en les stackant dans une file ou une pile par exemple.
## Problèmes rencontrés

Le plus gros problème auquel j'ai été confronté et que je n'ai pas pu surmonté est lié aux requêtes de création et de mise à jour des demandes de crédit. En effet, sur ces requêtes spécifiquement, certains des champs étaient considéré comme nuls par le système de persistance, quand bien même le champ dans la requête était renseigné. Après un bon moment à débugger et à chercher d'où venait l'erreur, je n'ai malheureusement pas pu trouver, mais cela ne se produit pas sur les autres entités du projet. 

## Pistes d'améliorations 

- Ajouter un nouveau statut "Annulé" pour bien faire la distinction entre une demande annulée et refusée. Cela permettrait à l'organisme bancaire de mieux identifier le comportement des utilisateurs et de localiser à quels moments de leur demande les clients sont le plus susceptibles d'annuler leur demande. 
- Pour une gestion plus contrôlée des transitions d'état des demandes de crédit, il serait intéressant d'intégrer une sorte d'automate à états fini, auquel on indique des règles de transition strictes.
- Mieux mettre à profit le circuit breaker pour prévenir les pannes de tous les services