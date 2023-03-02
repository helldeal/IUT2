
import time

start = time.time()

fichier=open("d08/input.txt","r")
text = fichier.readlines()
map=[]
cpt=0
for ligne in text:
    ligne=ligne.split("\n")[0]
    map.append([])
    for i in ligne:
        map[cpt].append(int(i))
    cpt+=1
    

visiblec=(len(map)+len(map[0]))*2-4
for x in range(1,len(map)-1):
    for y in range(1,len(map[0])-1):
        visible=False
        gauche=[map[x][i] for i in range(0,y+1)]
        droite=[map[x][i] for i in range(y,len(map[0]))]
        haut=[map[i][y] for i in range(0,x+1)]
        bas=[map[i][y] for i in range(x,len(map))]

        if map[x][y]==max(gauche) and gauche.count(map[x][y])==1:
            visible=True
        elif map[x][y]==max(droite) and droite.count(map[x][y])==1:
            visible=True
        elif map[x][y]==max(haut) and haut.count(map[x][y])==1:
            visible=True
        elif map[x][y]==max(bas) and bas.count(map[x][y])==1:
            visible=True
        if visible:
            visiblec+=1 
            # print((x,y))   


print("part1 :",visiblec)

from copy import deepcopy
mapscore=deepcopy(map)  
for x in range(0,len(map)):
    for y in range(0,len(map[0])):
        gauche=[map[x][i] for i in range(0,y+1)]
        gauche.reverse()
        droite=[map[x][i] for i in range(y,len(map[0]))]
        haut=[map[i][y] for i in range(0,x+1)]
        haut.reverse()
        bas=[map[i][y] for i in range(x,len(map))]
        
        a=1
        b=1
        c=1
        d=1
        z=1
        while z<len(gauche)-1 and gauche[z]<map[x][y]:
                a+=1
                z+=1
        z=1
        while z<len(droite)-1 and droite[z]<map[x][y]:
                b+=1
                z+=1
        z=1
        while z<len(haut)-1 and haut[z]<map[x][y]:
                c+=1
                z+=1
        z=1
        while z<len(bas)-1 and bas[z]<map[x][y]:
                d+=1
                z+=1

        mapscore[x][y]=a*b*c*d

        
        
print("part2 :",max([max(l) for l in mapscore]))

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")