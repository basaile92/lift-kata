#language: fr

Fonctionnalité: Savoir l'étage de l'ascenseur

  En tant qu'utilisateur je souhaite pouvoir savoir l'étage d'un ascenseur

  Contexte:
    Étant donné un immeuble de cinq étages

  Scénario: Connaitre l'étage d'un ascenseur dans un bâtiment
    Étant donné un ascenseur situé au premier étage
    Quand je regarde l'étage de l'ascenseur sur l'écran
    Alors l'écran indique "1"
