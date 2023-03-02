import copy
import random
import time

from matplotlib import pyplot as plt
import math as mt
from random import randrange

def premier(x):
    if x>1:
        for i in range(2,int(x/2+1)):
            if x%i==0:
                return False
        return True
    return False

# print(estPremier(1)) #False
# print(estPremier(2)) #True
# print(estPremier(407)) #False
# print(estPremier(19)) #True
# print(estPremier(17)) #True
# print(estPremier(89)) #True

def listPremiers(n):
    list=[]
    for i in range(1,n):
        if premier(i):
            list.append(i)
    return list

# print(listPremier(100))
# print(listPremier(10))
# print(listPremier(20))

def euclideEtendu(a,b):

    if a>b:
        u0=1
        u1=0
        v0=0
        v1=1
        r=a%b
        q=a//b
        while r!=0:
            a=b
            b=r
            u2=u0-(q*u1)
            v2=v0-(q*v1)
            u0=u1
            v0=v1
            u1=u2
            v1=v2
            r=a%b
            q=a//b
        return [b,u1,v1]
    return



list_nb_a_tester=[13,1009,10007,100003,1000003,10000019,100000007,1000000007,10000000019,100000000003,1000000000039,100000000000031]

def testListePremiers(X):
    list=[]
    for i in X:
        start = time.time()
        premier(i)
        list.append(time.time()-start)
    return list

# testPrem(list_nb_a_tester)
        
def graphPremTime(X):
    Y=testListePremiers(X)
    X= [len(str(x)) for x in X]
    plt.scatter(X,Y)
    plt.ylabel('Temps')
    plt.xlabel('Nombre')
    plt.title('Temps/Decimal calcul premier')
    plt.show()

# graphPremTime([13,1009,10007,100003,1000003,10000019,100000007])

def nombreDeChiffres(X):
    liste=[]
    for i in X:
        liste.append(len(str(i)))
    return liste

def nuageDePoints(X):
    x=nombreDeChiffres(X)
    _,y=testListePremiers(X)
    plt.scatter(x,y)

    plt.title('Nuage de points')
    plt.xlabel('nombre de chiffres')
    plt.ylabel('temps')

    plt.show()

def nuageDePointsLog(X):
    x=nombreDeChiffres(X)
    _,y=testListePremiers(X)
    z=[]
    for i in y:
        z.append(mt.log(i))
    plt.scatter(x,z)

    plt.title('Nuage de points')
    plt.xlabel('nombre de chiffres')
    plt.ylabel('temps')

    plt.show()

list_nb_a_tester=[13,1009,10007,100003,1000003,10000019,100000007,1000000007,10000000019,100000000003,1000000000039,100000000000031]
list_2=[13,1009,10007,100003,1000003,10000019]

#3.1
dict_cod={'a':1,'b':2,'c':3,'d':4,'e':5,'f':6,'g':7,'h':8,
            'i':9,'j':10,'k':11,'l':12,'m':13,'n':14,'o':15,
            'p':16,'q':17,'r':18,'s':19,'t':20,'u':21,'v':22,
            'w':23,'x':24,'y':25,'z':26,' ':27,',':28,'.':29,
            '?':30,':':0}
keys=list(dict_cod.keys())
values=list(dict_cod.values())
new_dict={}
for i in range(len(keys)):
    new_dict[values[i]]=keys[i]


#3.2
def chiff_affine(mess,a,b):
    crypted_mess=""
    for i in mess:
        old_value=dict_cod[i]
        new_value=(a*old_value + b)%31
        crypted_mess+=new_dict[new_value]
    return crypted_mess

def dechiff_affine(mess,a,b):
    decrypted_mess=""
    # for i in mess:
    #     old_value=dict_cod[i]
    #     for n in range(0, 31):
    #         if (a * n) % 31 == 1:
    #             new_value=n*(old_value - b)%31
    #     decrypted_mess+=new_dict[new_value]
    _,_,u=euclideEtendu(31,a)
    for i in mess:    
        decrypted_mess+=new_dict[u*(dict_cod[i]-b)%31]

    return decrypted_mess

# print(chiff_affine("oui", 4, 5))
# print(dechiff_affine(chiff_affine("bonjour", 15, 13), 15, 13))

def cass_chiff_affine(mess):
    chifmess=chiff_affine(mess,randrange(31),randrange(31))
    for i in range(1,31):
        for y in range(1,31):
            de=dechiff_affine(chifmess,i,y)
            print(de,i,y)
            if de==mess:
                return "DECRYPT : "+de+" A="+str(i)+" B="+str(y)

    return

# print(cass_chiff_affine("oui"))

# 4.1

def generateur(p):
    if not premier(p): 
        return "p pas premier"
    Zpz=[i for i in range(p)]
    for i in range(1,p-1):
        list=[]
        for k in range(1,p):
            list.append(i**k%p) 
        complist = [value for value in Zpz if value in list]
        if len(complist)==p-1:
            # print(complist)
            return i
    return

# print(generateur(13))

def logdiscret(p,x):
    if x<p:
        g=generateur(p)
        for k in range(p-1):
            if g**k%p==x:
                return k
    else: 
        return "x doit etre <p"


def Shanks(p,y):
    g=generateur(p)
    s=1+int(mt.modf(mt.sqrt(p))[1])
    _,_,u=euclideEtendu(p,g)
    baby=[]
    giant=[]
    for i in range(s):
        baby.append(g**i)
        giant.append((y*(u%p)**(i*s))%p)
    for i in range(s):
        for j in range(s):
            if baby[i]==giant[j]:
                return i+j*s
    return

# print(Shanks(13,6))
# print(logdiscret(13,6))

"""# Exercice 5 

## scénario

Je suis Alice
- je choisis un nombre premier p
- je cherche un générateur g de (Z/pZ)*
- je choisis un exposant a dans [2, p-2] qui sera ma clé privée
- je calcule A qui est égal à g^a
- je publie ma clé publique qui se compose de (p, g, A)

Je suis Bob
- j'ai à ma disposition p, g et A qui sont publics
- je choisis un exposant b dans [2, p-2] qui sera ma clé privée
- je calcule B qui est égal à g^b
- je publie ma clé publique qui se compose de (p, g, B)
- je souhaite chiffrer un message m à Alice
- je vais utiliser le calcul A^b qui dans les faits est égal à (g^b)^a ou encore g^(b*a) ou encore (g^a)^b qu'Alice peut calculer aussi de son côté
- je chiffre le message avec la fonction m*A^b mod(p) ce qui me donne c, et j'envoie à Alice le coupe (c, B)

Je suis Alice

- je calcule l'[inverse modulaire](https://fr.wikipedia.org/wiki/Inverse_modulaire) de B^a (i.e. (g^b)^a) 
    qui correspond au coeff de Bezout u calculé à partir de l'algorithme d'Euclide étendue 
- je déchiffre le message chiffré c à l'aide de la fonction c*u mod(p)
"""


""" 
## Mise en oeuvre

Je suis Alice
- pour un semblant de réalité (mais pas trop), je choisis p dans [1000, 2000]. 
    Pour ce faire, je peux réutiliser mes méthodes précédemment développées. 
    Par exemple je peux précalculer les nombres premiers de 1 à 2000. 
    Puis tirer au hazard un nombre de cette liste tant que j'en obtiens pas un de plus de 1000... 
    Probablement que les 25 premiers nombres de cette liste peuvent être sautés.
""" 
""" 
Je suis Alice
- pour trouver g je peux calculer (Z/pZ)* puis pour chaque valeur de g compris entre 1 et p-1 tester 
    si g est générateur en construisant l'ensemble générée par g et en le comparant à (Z/pZ)*. Je stoppe quand ils sont identiques.
""" 

""" 
Je suis Alice
- je choisis aléatoirement a dans [2, p-2]
""" 

def choixprem():
    list=[]
    for i in range(1000,2000):
            if premier(i):
                list.append(i)
    return random.choice(list)

def choixa(p):
    list=[]
    for i in range(2,p-2):
            list.append(i)
    return random.choice(list)

def keycreation():
    p=choixprem()
    g=generateur(p)
    a=choixa(p)
    A=g**a
    return p,g,A,a
# p,g,A,a=keycreation()
# print("--------------ALICE---------------")
# print("CLE ALICE :\n","p:",p,"g:",g,"a:",a,"A:",A,"\n")

""" 
Je suis Bob
- je prépare mon message pour pouvoir le chiffrer i.e. que je le transforme en valeur numérique et 
    je le découpe en paquets suivant le modulo p ; je chiffrerai chaque paquet
TODO cf. code dans le sujet
"""

import hashlib as hash
message="Messi est le GOAT n'en déplaise à SUNAJ MUSIC SUIIIIIIIII"
message_utf8=message.encode()
'''utilisation de la fonction de hashage SHA256 et encode
en hexad ́ecimal :'''
message_hash=hash.sha256(message_utf8).hexdigest()
#conversion en entier base 10 :
number_message_hash=int(message_hash,16)

def cryptbob(p,g,A):
    b=choixa(p)
    m=number_message_hash
    B=g**b
    pack=[]
    while m>0:
        pack=[m%1000]+pack
        m=m//1000
    pack_cry=[(i*A**b)%p for i in pack]
    print("pack non crypt :",pack,"\n")
    return pack_cry,b,B
    
# print("---------------BOB---------------")
# print("msg :",number_message_hash)
# pack,b,B=cryptbob(p,g,A)
# print("CLE BOB :\n","b:",b,"B:",B,"\npackcry:",pack,"\n")


def decryptalice(pack,B,a,p):
    _,u,_=euclideEtendu(B**a,p)
    inv=u%p
    pack_decry=[i*inv%p for i in pack]
    print("pack decry :",pack_decry,"\n")
    pack_decry.reverse()
    number_message_hash_recalculé=0
    for i in range(len(pack_decry)):
        number_message_hash_recalculé=pack_decry[i]*1000**i+number_message_hash_recalculé
    print("msg decry :",number_message_hash_recalculé) 
    return number_message_hash_recalculé

# print("--------------ALICE---------------")
# number_message_hash_recalculé=decryptalice(pack,B,a,p)
# print("la signature est correcte:",number_message_hash_recalculé==number_message_hash)


#EXERCICE 6 :

def deg(A):
    return len(A)-1

def plus(A,B):
    A=copy.deepcopy(A)
    B=copy.deepcopy(B)
    A.reverse()
    B.reverse()
    Add=[]
    for i in range(max(len(A),len(B))):
        r=0
        if i<len(A): r+=A[i]
        if i<len(B): r+=B[i]
        Add.append(r%2)
    Add.reverse()
    return Add
    
print(plus([1,1,1,1,1,0,0],[1,0,1]))

def div_eucl_pol(A,B):
    A=copy.deepcopy(A)
    B=copy.deepcopy(B)
    binA=0
    binB=0
    A.reverse()
    B.reverse()
    for i in range(len(A)):
        if A[i]==1:
            binA+=(2*A[i])**i
    for i in range(len(B)):
        if B[i]==1:
            binB+=(2*B[i])**i
    div=[int(i) for i in bin(binA//binB)[2:]]
    reste=[int(i) for i in bin(binA%binB)[2:]]
    return div,reste

print(div_eucl_pol([1,1,1,1,1,0,0],[1,0,1]))