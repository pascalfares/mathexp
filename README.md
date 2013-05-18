mathexp
=======

D�monstration d'un interpretteur: application au calcul formel

Traducteur Objets recusifs inspir� de la  Descente r�cursive

Descente r�cursive
==================

L'analyse descendante peut �tre consid�r�e comme une tentative pour d�terminer une d�rivation gauche associ�e à une chaîne d'entr�e. Elle peut être vue aussi comme une tentative pour construire un arbre d'analyse de la chaine d'entr�e, en partant de la racine et en cr�ant les noeuds de l'arbre en pr�ordre. A la section , nous avons pr�sent� un cas particulier d'analyse par descente r�cursive, appel�e analyse pr�dictive, dans laquelle aucun rebroussement n'�tait n�cessaire.

Une forme g�n�rale d'analyse descendante, appel�e descente r�cursive, peut impliquer des retours arri�re, c'est-à-dire n�cessiter des passages r�p�t�s sur le texte source. Cependant, les analyseurs avec rebroussement ne sont pas tr�s fr�quents. Une des raisons est que le rebroussement est rarement n�cessaire pour analyser les constructions des langages de programmation.

pour en savoir plus [Notions de compilation](http://www.infeig.unige.ch/support/cpil/lect/recurs/web.html)
