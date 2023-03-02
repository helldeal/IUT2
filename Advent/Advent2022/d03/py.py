

import string
import time

start = time.time()

alphabet=string.ascii_lowercase
alphabet2=string.ascii_uppercase
prio={}
for i in range(len(alphabet)):
    prio[alphabet[i]]=i+1
    prio[alphabet2[i]]=i+27

score=0
fichier=open("d03/input.txt","r")
text = fichier.readlines()
for ligne in text:
    ligne1=[ligne[i] for i in range(len(ligne)//2)]
    ligne2=[ligne[i] for i in range(len(ligne)//2,len(ligne))]
    # print(ligne1,ligne2)
    for i in ligne1:
        if i in ligne2:
            score+=prio[i]
            break

print("part1:",score)

score=0
fichier=open("d03/input.txt","r")
text = fichier.readlines()
ligne123=[]
for ligne in text:
    if len(ligne123)<3:
        ligne123.append(ligne.strip())
    if len(ligne123)>=3:
        for i in ligne123[0]:
            if i in ligne123[1] and i in ligne123[2]:
                score+=prio[i]
                break
        ligne123=[]
    

print("part2:",score)
end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")