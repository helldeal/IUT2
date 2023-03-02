import pandas as pd
import matplotlib.pyplot as plt

### pour charger le classeur Excel :
data_file = pd.ExcelFile('video_etu.xlsx')
""" pour importer les donn ́ees de la feuille 'films'
comme un objet DataFrame :"""
data_films = pd.read_excel(data_file, 'films')
""" pour r ́ecup ́erer les donn ́ees du champ 'FILM_DUREE'
comme un objet Series :"""
duree_films=data_films['FILM_DUREE']

moyenne_pop_duree=duree_films.mean()
ecart_type_pop=duree_films.std()



plt.bar(moyenne_pop_duree,)
plt.show()