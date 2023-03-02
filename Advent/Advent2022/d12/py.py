
import time
import string

start = time.time()

alphabet=string.ascii_lowercase
prio={}
prio["E"]=26
prio["S"]=1
startpos=()
objectif=()
for i in range(len(alphabet)):
    prio[alphabet[i]]=i+1

heighmap=[]
cpt=0
fichier=open("d12/input.txt","r")
text = fichier.readlines()
for ligne in text:
    ligne=ligne.split("\n")[0]
    heighmap.append([])
    for i in ligne:
        if i=="S":
            startpos=(cpt,ligne.find("S"))
        if i=="E":
            objectif=(cpt,ligne.find("E"))
        heighmap[cpt].append(prio[i])
    cpt+=1
    
# print(startpos,objectif)
# for i in heighmap: 
#     print(i)

import copy
minstep=9223372036854775807
# for alti in range(len(heighmap)):
#     startpos=(alti,0)
pathfinding=[[startpos,[startpos],0]]
for i in pathfinding:
    y,x=i[0]
    hist=i[1]
    print(i[0],i[2])
    if x>0 and (y,x-1) not in hist and heighmap[y][x-1]-heighmap[y][x]<2 and objectif not in hist:
        npos=(y,x-1)
        hist.append(npos)
        pathfinding.append([npos,hist,i[2]+1])
    
    if x<len(heighmap[0])-1 and (y,x+1) not in hist and heighmap[y][x+1]-heighmap[y][x]<2 and objectif not in hist:
        npos=(y,x+1)
        hist.append(npos)
        pathfinding.append([npos,hist,i[2]+1])
    
    if y>0 and (y-1,x) not in hist and heighmap[y-1][x]-heighmap[y][x]<2 and objectif not in hist:
        npos=(y-1,x)
        hist.append(npos)
        pathfinding.append([npos,hist,i[2]+1])
    
    if y<len(heighmap)-1 and (y+1,x) not in hist and heighmap[y+1][x]-heighmap[y][x]<2 and objectif not in hist:
        npos=(y+1,x)
        hist.append(npos)
        pathfinding.append([npos,hist,i[2]+1])
    # pathfinding.remove(i)

if max([i[2] for i in pathfinding if objectif in i[1]])<minstep:
    minstep=max([i[2] for i in pathfinding if objectif in i[1]])


print("part1 :",minstep)
print("part2 :")

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")