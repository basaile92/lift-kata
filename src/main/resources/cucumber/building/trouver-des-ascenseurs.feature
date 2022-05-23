#language: fr

Fonctionnalité: Trouver des ascenseurs dans un bâtiment

  En tant qu'utilisateur je souhaite pouvoir trouver un ou plusieurs ascenseurs dans un bâtiment

  Scénario: Avoir un ascenseur dans un bâtiment
    Étant donné un immeuble de cinq étages avec un ascenseur
    Quand je cherche des ascenseurs
    Alors je trouve l'ascenseur

  Scénario: Avoir deux ascenseurs dans un bâtiment
    Étant donné un immeuble de cinq étages avec deux ascenseurs
    Quand je cherche des ascenseurs
    Alors je trouve les deux ascenseurs
