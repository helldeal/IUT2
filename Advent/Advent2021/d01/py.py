
import time

start = time.time()

fichier=open("d01/input.txt","r")
text = fichier.readlines()
cpt=0
for ligne in text:
    ligne=ligne.split("\n")[0]
    for i in ligne:
        if i=="(":
            cpt+=1
        else:
            cpt-=1


print("part1 :",cpt)
cpt=0
step=1
for ligne in text:
    ligne=ligne.split("\n")[0]
    for i in ligne:
        if i=="(":
            cpt+=1
        else:
            cpt-=1
        if cpt<0:
            break
        step+=1
print("part2 :",step)

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")