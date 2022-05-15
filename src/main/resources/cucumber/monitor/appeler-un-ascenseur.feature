#language: fr

Fonctionnalité: Appeler un ascenseur depuis un moniteur

  En tant qu'utilisateur je souhaite pouvoir appeler un ascenseur depuis le moniteur d'un étage
  Afin de pouvoir prendre l'ascenseur

  Contexte:
    Étant donné un immeuble de cinq étages

  Scénario: Appeler depuis le premier étage un ascenseur situé au premier étage pour monter
    Étant donné un ascenseur situé au premier étage
    Quand j'appuie sur le bouton monter du moniteur du premier étage
    Alors l'ascenseur reçoit une demande pour monter provenant du premier étage
