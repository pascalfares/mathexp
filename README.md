mathexp
=======

Démonstration d'un interpreteur: application au calcul formel

Traducteur Objets recusifs inspiré de la  Descente récursive

Descente récursive vers Objets récursifs
==================

L'analyse descendante peut ètre considérée comme une tentative pour déterminer une dérivation gauche associée à une chaîne d'entrée. 
Elle peut être vue aussi comme une tentative pour construire un arbre d'analyse de la chaine d'entrée, 
en partant de la racine et en créant les noeuds de l'arbre en préordre. 
Ce projet présente un cas particulier d'analyse par descente récursive adapté a la génération de l'arbre sémantique du langage par modèle objet, appelée les "objets récursifs", 
dans laquelle aucun rebroussement n'était nécessaire.

Une forme générale d'analyse descendante, appelée descente récursive, peut impliquer des retours arrière, c'est-à-dire nécessiter des passages répétés sur le texte source. Cependant, les analyseurs avec rebroussement ne sont pas très fréquents. Une des raisons est que le rebroussement est rarement nécessaire pour analyser les constructions des langages de programmation. Le langage mathexp est adapté à la descente récurive sans rebroussement.

Dans ce projet nous définirons une technique dite d'"Objets récursifs" inspirée de la descente récursive.

pour en savoir plus:

* [Objets récursifs, la méthode](http://algorithmes.cofares.net/fondamental/objets-rcursif-la-mthode)

* [Notions de compilation](http://www.infeig.unige.ch/support/cpil/lect/recurs/web.html)
