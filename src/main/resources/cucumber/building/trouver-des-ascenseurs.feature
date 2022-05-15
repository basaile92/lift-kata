#language: fr

Fonctionnalité: Trouver des ascenseurs dans un bâtiment

  En tant qu'utilisateur je souhaite pouvoir trouver un ou plusieurs ascenseurs dans un bâtiment

  Contexte:
    Étant donné un immeuble de cinq étages

  Scénario: Avoir un ascenseur dans un bâtiment
    Étant donné un ascenseur situé au premier étage
    Quand je cherche des ascenseurs
    Alors je trouve l'ascenseur

  Scénario: Avoir deux ascenseurs dans un bâtiment
    Étant donné deux ascenseurs situé au premier étage
    Quand je cherche des ascenseurs
    Alors je trouve les deux ascenseurs