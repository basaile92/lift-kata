#language: fr

Fonctionnalité: Ouvrir et fermer les portes de l'ascenseur

  En tant qu'utilisateur je souhaite qu'une fois que l'ascenseur a terminé un déplacement, il fait DING et ouvre ses portes puis les referme

  Contexte:
    Étant donné un immeuble de cinq étages

  Scénario: Ouvrir et fermer les portes de l'ascenseur
    Étant donné un ascenseur se déplaçant au premier étage
    Quand l'ascenseur arrive au premier étage
    Alors l'ascenseur fait: DING!
    Et les portes de l'ascenseur s'ouvre et se referme
