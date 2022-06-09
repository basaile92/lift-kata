#language: fr

Fonctionnalité: Filtrer et ordonner des commandes d'ascenseur

  En tant qu'utilisateur je souhaite que l'ascenseur ordonne les commandes qu'il a en mémoire
  afin qu'il optimise ses déplacements

  Contexte:
    Etant donné un building avec les étages ordonnés:"0","1","2","3","4","5"

  Plan du scénario: Ajouter deux commandes Appel dans le cerveau d'un ascenseur qui monte
    Étant donné un ascenseur situé à l'étage <etage-ascenseur> qui monte avec un cerveau n'ayant pas de commandes en mémoire
    Quand une commande appel:<direction-appel1> de l'étage <etage-appel1> est envoyé au cerveau
    Et une commande appel:<direction-appel2> de l'étage <etage-appel2> est envoyé au cerveau
    Alors le cerveau va ordonner d'aller à l'étage <etage-resultat1>
    Et le cerveau va ordonner d'aller à l'étage <etage-resultat2>
    Exemples:
      | etage-ascenseur | direction-appel1 | etage-appel1 | direction-appel2 | etage-appel2 | etage-resultat1 | etage-resultat2 |
      | "0"             | monter           | "2"          | monter           | "3"          | "2"             | "3"             |
      | "0"             | monter           | "3"          | monter           | "2"          | "2"             | "3"             |
      | "0"             | descendre        | "2"          | descendre        | "3"          | "3"             | "2"             |
      | "0"             | descendre        | "3"          | descendre        | "2"          | "3"             | "2"             |
      | "0"             | monter           | "2"          | descendre        | "3"          | "2"             | "3"             |
      | "0"             | descendre        | "2"          | monter           | "3"          | "3"             | "2"             |
      | "0"             | monter           | "3"          | descendre        | "2"          | "3"             | "2"             |
      | "0"             | descendre        | "3"          | monter           | "2"          | "2"             | "3"             |
      | "4"             | monter           | "2"          | monter           | "3"          | "2"             | "3"             |
      | "4"             | monter           | "3"          | monter           | "2"          | "2"             | "3"             |
      | "4"             | descendre        | "2"          | descendre        | "3"          | "3"             | "2"             |
      | "4"             | descendre        | "3"          | descendre        | "2"          | "3"             | "2"             |
      | "4"             | monter           | "2"          | descendre        | "3"          | "3"             | "2"             |
      | "4"             | descendre        | "2"          | monter           | "3"          | "2"             | "3"             |
      | "4"             | monter           | "3"          | descendre        | "2"          | "2"             | "3"             |
      | "4"             | descendre        | "3"          | monter           | "2"          | "3"             | "2"             |
      | "2"             | monter           | "0"          | monter           | "4"          | "4"             | "0"             |
      | "2"             | descendre        | "0"          | descendre        | "4"          | "4"             | "0"             |
      | "2"             | monter           | "0"          | descendre        | "4"          | "4"             | "0"             |
      | "2"             | descendre        | "0"          | monter           | "4"          | "4"             | "0"             |
      | "2"             | monter           | "4"          | monter           | "0"          | "4"             | "0"             |
      | "2"             | descendre        | "4"          | descendre        | "0"          | "4"             | "0"             |
      | "2"             | monter           | "4"          | descendre        | "0"          | "4"             | "0"             |
      | "2"             | descendre        | "4"          | monter           | "0"          | "4"             | "0"             |

  Plan du scénario: Ajouter deux commandes Appel dans le cerveau d'un ascenseur qui descend
    Étant donné un ascenseur situé à l'étage <etage-ascenseur> qui descend avec un cerveau n'ayant pas de commandes en mémoire
    Quand une commande appel:<direction-appel1> de l'étage <etage-appel1> est envoyé au cerveau
    Et une commande appel:<direction-appel2> de l'étage <etage-appel2> est envoyé au cerveau
    Alors le cerveau va ordonner d'aller à l'étage <etage-resultat1>
    Et le cerveau va ordonner d'aller à l'étage <etage-resultat2>
    Exemples:
      | etage-ascenseur | direction-appel1 | etage-appel1 | direction-appel2 | etage-appel2 | etage-resultat1 | etage-resultat2 |
      | "0"             | monter           | "2"          | monter           | "3"          | "2"             | "3"             |
      | "0"             | monter           | "3"          | monter           | "2"          | "2"             | "3"             |
      | "0"             | descendre        | "2"          | descendre        | "3"          | "3"             | "2"             |
      | "0"             | descendre        | "3"          | descendre        | "2"          | "3"             | "2"             |
      | "0"             | monter           | "2"          | descendre        | "3"          | "2"             | "3"             |
      | "0"             | descendre        | "2"          | monter           | "3"          | "3"             | "2"             |
      | "0"             | monter           | "3"          | descendre        | "2"          | "3"             | "2"             |
      | "0"             | descendre        | "3"          | monter           | "2"          | "2"             | "3"             |
      | "4"             | monter           | "2"          | monter           | "3"          | "2"             | "3"             |
      | "4"             | monter           | "3"          | monter           | "2"          | "2"             | "3"             |
      | "4"             | descendre        | "2"          | descendre        | "3"          | "3"             | "2"             |
      | "4"             | descendre        | "3"          | descendre        | "2"          | "3"             | "2"             |
      | "4"             | monter           | "2"          | descendre        | "3"          | "3"             | "2"             |
      | "4"             | descendre        | "2"          | monter           | "3"          | "2"             | "3"             |
      | "4"             | monter           | "3"          | descendre        | "2"          | "2"             | "3"             |
      | "4"             | descendre        | "3"          | monter           | "2"          | "3"             | "2"             |
      | "2"             | monter           | "0"          | monter           | "4"          | "0"             | "4"             |
      | "2"             | descendre        | "0"          | descendre        | "4"          | "0"             | "4"             |
      | "2"             | monter           | "0"          | descendre        | "4"          | "0"             | "4"             |
      | "2"             | descendre        | "0"          | monter           | "4"          | "0"             | "4"             |
      | "2"             | monter           | "4"          | monter           | "0"          | "0"             | "4"             |
      | "2"             | descendre        | "4"          | descendre        | "0"          | "0"             | "4"             |
      | "2"             | monter           | "4"          | descendre        | "0"          | "0"             | "4"             |
      | "2"             | descendre        | "4"          | monter           | "0"          | "0"             | "4"             |

  Plan du scénario: Ajouter une première commande appel et ensuite une commande aller dans le cerveau de l'ascenseur qui monte
    Étant donné un ascenseur situé à l'étage <etage-ascenseur> qui monte avec un cerveau n'ayant pas de commandes en mémoire
    Quand une commande appel:<direction-appel1> de l'étage <etage-appel> est envoyé au cerveau
    Et une commande aller:à l'étage <etage-aller> est envoyé au cerveau
    Alors le cerveau va ordonner d'aller à l'étage <etage-resultat1>
    Et le cerveau va ordonner d'aller à l'étage <etage-resultat2>
    Exemples:
      | etage-ascenseur | direction-appel1 | etage-appel | etage-aller | etage-resultat1 | etage-resultat2 |
      | "0"             | monter           | "2"         | "3"         | "2"             | "3"             |
      | "0"             | monter           | "3"         | "2"         | "2"             | "3"             |
      | "0"             | descendre        | "2"         | "3"         | "3"             | "2"             |
      | "0"             | descendre        | "3"         | "2"         | "2"             | "3"             |
      | "4"             | monter           | "2"         | "3"         | "3"             | "2"             |
      | "4"             | monter           | "3"         | "2"         | "2"             | "3"             |
      | "4"             | descendre        | "2"         | "3"         | "3"             | "2"             |
      | "4"             | descendre        | "3"         | "2"         | "3"             | "2"             |
      | "2"             | monter           | "0"         | "4"         | "4"             | "0"             |
      | "2"             | monter           | "4"         | "0"         | "4"             | "0"             |
      | "2"             | descendre        | "0"         | "4"         | "4"             | "0"             |
      | "2"             | descendre        | "4"         | "0"         | "4"             | "0"             |

  Plan du scénario: Ajouter une première commande appel et ensuite une commande aller dans le cerveau de l'ascenseur qui descend
    Étant donné un ascenseur situé à l'étage <etage-ascenseur> qui descend avec un cerveau n'ayant pas de commandes en mémoire
    Quand une commande appel:<direction-appel1> de l'étage <etage-appel> est envoyé au cerveau
    Et une commande aller:à l'étage <etage-aller> est envoyé au cerveau
    Alors le cerveau va ordonner d'aller à l'étage <etage-resultat1>
    Et le cerveau va ordonner d'aller à l'étage <etage-resultat2>
    Exemples:
      | etage-ascenseur | direction-appel1 | etage-appel | etage-aller | etage-resultat1 | etage-resultat2 |
      | "0"             | monter           | "2"         | "3"         | "2"             | "3"             |
      | "0"             | monter           | "3"         | "2"         | "2"             | "3"             |
      | "0"             | descendre        | "2"         | "3"         | "3"             | "2"             |
      | "0"             | descendre        | "3"         | "2"         | "2"             | "3"             |
      | "4"             | monter           | "2"         | "3"         | "3"             | "2"             |
      | "4"             | monter           | "3"         | "2"         | "2"             | "3"             |
      | "4"             | descendre        | "2"         | "3"         | "3"             | "2"             |
      | "4"             | descendre        | "3"         | "2"         | "3"             | "2"             |
      | "2"             | monter           | "0"         | "4"         | "0"             | "4"             |
      | "2"             | monter           | "4"         | "0"         | "0"             | "4"             |
      | "2"             | descendre        | "0"         | "4"         | "0"             | "4"             |
      | "2"             | descendre        | "4"         | "0"         | "0"             | "4"             |


  Plan du scénario: Ajouter une première commande aller et ensuite une commande appel dans le cerveau de l'ascenseur qui monte
    Étant donné un ascenseur situé à l'étage <etage-ascenseur> qui monte avec un cerveau n'ayant pas de commandes en mémoire
    Quand une commande aller:à l'étage <etage-aller> est envoyé au cerveau
    Et une commande appel:<direction-appel1> de l'étage <etage-appel> est envoyé au cerveau
    Alors le cerveau va ordonner d'aller à l'étage <etage-resultat1>
    Et le cerveau va ordonner d'aller à l'étage <etage-resultat2>
    Exemples:
      | etage-ascenseur | direction-appel1 | etage-appel | etage-aller | etage-resultat1 | etage-resultat2 |
      | "0"             | monter           | "2"         | "3"         | "2"             | "3"             |
      | "0"             | monter           | "3"         | "2"         | "2"             | "3"             |
      | "0"             | descendre        | "2"         | "3"         | "3"             | "2"             |
      | "0"             | descendre        | "3"         | "2"         | "2"             | "3"             |
      | "4"             | monter           | "2"         | "3"         | "3"             | "2"             |
      | "4"             | monter           | "3"         | "2"         | "2"             | "3"             |
      | "4"             | descendre        | "2"         | "3"         | "3"             | "2"             |
      | "4"             | descendre        | "3"         | "2"         | "3"             | "2"             |
      | "2"             | monter           | "0"         | "4"         | "4"             | "0"             |
      | "2"             | monter           | "4"         | "0"         | "4"             | "0"             |
      | "2"             | descendre        | "0"         | "4"         | "4"             | "0"             |
      | "2"             | descendre        | "4"         | "0"         | "4"             | "0"             |

  Plan du scénario: Ajouter une première commande aller et ensuite une commande appel dans le cerveau de l'ascenseur qui descend
    Étant donné un ascenseur situé à l'étage <etage-ascenseur> qui descend avec un cerveau n'ayant pas de commandes en mémoire
    Quand une commande aller:à l'étage <etage-aller> est envoyé au cerveau
    Et une commande appel:<direction-appel1> de l'étage <etage-appel> est envoyé au cerveau
    Alors le cerveau va ordonner d'aller à l'étage <etage-resultat1>
    Et le cerveau va ordonner d'aller à l'étage <etage-resultat2>
    Exemples:
      | etage-ascenseur | direction-appel1 | etage-appel | etage-aller | etage-resultat1 | etage-resultat2 |
      | "0"             | monter           | "2"         | "3"         | "2"             | "3"             |
      | "0"             | monter           | "3"         | "2"         | "2"             | "3"             |
      | "0"             | descendre        | "2"         | "3"         | "3"             | "2"             |
      | "0"             | descendre        | "3"         | "2"         | "2"             | "3"             |
      | "4"             | monter           | "2"         | "3"         | "3"             | "2"             |
      | "4"             | monter           | "3"         | "2"         | "2"             | "3"             |
      | "4"             | descendre        | "2"         | "3"         | "3"             | "2"             |
      | "4"             | descendre        | "3"         | "2"         | "3"             | "2"             |
      | "2"             | monter           | "0"         | "4"         | "0"             | "4"             |
      | "2"             | monter           | "4"         | "0"         | "0"             | "4"             |
      | "2"             | descendre        | "0"         | "4"         | "0"             | "4"             |
      | "2"             | descendre        | "4"         | "0"         | "0"             | "4"             |

  Plan du scénario: Ajouter deux commandes aller dans le cerveau de l'ascenseur qui monte
    Étant donné un ascenseur situé à l'étage <etage-ascenseur> qui monte avec un cerveau n'ayant pas de commandes en mémoire
    Quand une commande aller:à l'étage <etage-aller1> est envoyé au cerveau
    Et une commande aller:à l'étage <etage-aller2> est envoyé au cerveau
    Alors le cerveau va ordonner d'aller à l'étage <etage-resultat1>
    Et le cerveau va ordonner d'aller à l'étage <etage-resultat2>
    Exemples:
      | etage-ascenseur | etage-aller1 | etage-aller2 | etage-resultat1 | etage-resultat2 |
      | "0"             | "2"          | "3"          | "2"             | "3"             |
      | "0"             | "3"          | "2"          | "2"             | "3"             |
      | "4"             | "2"          | "3"          | "3"             | "2"             |
      | "4"             | "3"          | "2"          | "3"             | "2"             |
      | "2"             | "0"          | "4"          | "4"             | "0"             |
      | "2"             | "4"          | "0"          | "4"             | "0"             |

  Plan du scénario: Ajouter deux commandes aller dans le cerveau de l'ascenseur qui descend
    Étant donné un ascenseur situé à l'étage <etage-ascenseur> qui descend avec un cerveau n'ayant pas de commandes en mémoire
    Quand une commande aller:à l'étage <etage-aller1> est envoyé au cerveau
    Et une commande aller:à l'étage <etage-aller2> est envoyé au cerveau
    Alors le cerveau va ordonner d'aller à l'étage <etage-resultat1>
    Et le cerveau va ordonner d'aller à l'étage <etage-resultat2>
    Exemples:
      | etage-ascenseur | etage-aller1 | etage-aller2 | etage-resultat1 | etage-resultat2 |
      | "0"             | "2"          | "3"          | "2"             | "3"             |
      | "0"             | "3"          | "2"          | "2"             | "3"             |
      | "4"             | "2"          | "3"          | "3"             | "2"             |
      | "4"             | "3"          | "2"          | "3"             | "2"             |
      | "2"             | "0"          | "4"          | "0"             | "4"             |
      | "2"             | "4"          | "0"          | "0"             | "4"             |

  Scénario: Ajouter 10 commandes dans le cerveau de l'ascenseur
    Étant donné un ascenseur situé à l'étage "0" qui monte avec un cerveau n'ayant pas de commandes en mémoire
    Quand une commande aller:à l'étage "3" est envoyé au cerveau
    Alors le cerveau va ordonner d'aller à l'étage "3"
    Et le cerveau n'a plus rien à faire
    Quand une commande appel:monter de l'étage "2" est envoyé au cerveau
    Et une commande aller:à l'étage "2" est envoyé au cerveau
    Alors le cerveau va ordonner d'aller à l'étage "2"
    Et le cerveau n'a plus rien à faire
    Quand une commande appel:monter de l'étage "0" est envoyé au cerveau
    Et une commande appel:descendre de l'étage "5" est envoyé au cerveau
    Et une commande aller:à l'étage "5" est envoyé au cerveau
    Et une commande appel:descendre de l'étage "5" est envoyé au cerveau
    Et une commande appel:descendre de l'étage "5" est envoyé au cerveau
    Et une commande aller:à l'étage "5" est envoyé au cerveau
    Alors le cerveau va ordonner d'aller à l'étage "5"
    Et le cerveau va ordonner d'aller à l'étage "0"
    Et le cerveau n'a plus rien à faire
