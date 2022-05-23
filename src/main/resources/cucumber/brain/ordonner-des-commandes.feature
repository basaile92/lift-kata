#language: fr

Fonctionnalité: Ordonner des commandes d'ascenceur

  En tant qu'utilisateur je souhaite que l'ascenseur ordonne les commandes qu'il a en mémoire
  afin qu'il optimise ses déplacements

  Contexte:
    Etant donné un building avec les étages ordonnés:"0","1","2","3","4","5"

  Plan du scénario: Ajouter deux commandes dans le cerveau
    Etant donné un ascenseur situé à l'étage "0" avec un cerveau n'ayant pas de commandes en mémoire
    Quand une commande appel:monter de l'étage "2" est envoyé au cerveau
    Et une commande appel:monter de l'étage "3" est envoyé au cerveau
    Alors le cerveau va ordonner d'aller à l'étage "2"
    Et le cerveau va ordonner d'aller à l'étage "3"
    Exemples:
      |  |
