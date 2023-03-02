
import time
from json import loads


start = time.time()

fichier=open("d13/inputtest.txt","r")
text = fichier.readlines()

tabpairs=[[]]
for ligne in text:
    ligne=ligne.split("\n")[0]
    if len(ligne)>0:
        tabpairs[len(tabpairs)-1].append(loads(ligne))
    else:
        tabpairs.append([])

def compare(i,rightorder):
    if len(i[0])==0 and len(i[1])>0:
        return True
    if len(i[1])==0 and len(i[0])>0:
        return False
    for y in range(min(len(i[0]),len(i[1]))):
        if type(i[0][y])!=type(i[1][y]):
            if type(i[0][y])==int:
                i[0][y]=[i[0][y]]
            elif type(i[1][y])==int:
                i[1][y]=[i[1][y]]

        if type(i[0][y])==int:
            if i[0][y]<i[1][y]:
                return True
            if i[0][y]>i[1][y]:
                return False

        if y+1==len(i[0]) and y+1<len(i[1]):
            return True
        if y+1==len(i[1]) and y+1<len(i[0]):
            return False

        if type(i[0][y])==list:
            rightorder=compare([i[0][y],i[1][y]],rightorder)


    return rightorder

total=0
for i in range(len(tabpairs)):
    rightorder=compare(tabpairs[i],rightorder=False)

    if rightorder:
        total+=i+1

 


print("part1 :",total)
print("part2 :")

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")