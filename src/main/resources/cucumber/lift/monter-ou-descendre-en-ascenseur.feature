#language: fr

Fonctionnalité: Monter ou descendre en ascenseur

  En tant qu'utilisateur je souhaite pouvoir monter ou descendre en ascenseur

  Contexte:
    Étant donné un immeuble de cinq étages

  Scénario: Monter en ascenseur
    Étant donné un ascenseur situé au premier étage
    Quand j'appuie sur le bouton 'deuxième étage' de l'ascenseur
    Alors l'ascenseur reçoit une demande pour aller au deuxième étage

  Scénario: Rester sur place en ascenseur
    Étant donné un ascenseur situé au premier étage
    Quand j'appuie sur le bouton 'premier étage' de l'ascenseur
    Alors l'ascenseur reçoit une demande pour aller au premier étage

  Scénario: Descendre en ascenseur
    Étant donné un ascenseur situé au deuxième étage
    Quand j'appuie sur le bouton 'premier étage' de l'ascenseur
    Alors l'ascenseur reçoit une demande pour aller au premier étage
