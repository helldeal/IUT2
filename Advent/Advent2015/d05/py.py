
import time

start = time.time()

fichier=open("d05/input.txt","r")
text = fichier.readlines()
nicecount=0
for ligne in text:
    ligne=ligne.split("\n")[0]
    dd=False
    vo=False
    for i in range(1,len(ligne)):
        if ligne[i]==ligne[i-1]:
            dd=True
            break
    vow=map(ligne.lower().count, "aeiou")
    cpt=0
    for i in vow:
        if i>0:
            cpt+=i
    if cpt >2:
        vo = True
    if "ab" in ligne or "cd" in ligne or "xy" in ligne or "pq" in ligne:
        vo=False
    if dd and vo:
        nicecount+=1
    
    


print("part1 :",nicecount)
nicecount=0
for ligne in text:
    ligne=ligne.split("\n")[0]
    dd=False
    vo=False
    for i in range(1,len(ligne)-1):
        if ligne[i-1]==ligne[i+1]:
            dd=True
            break
    
    for i in range(1,len(ligne)):
        for y in range(i+2,len(ligne)):
            if ligne[i]==ligne[y] and ligne[y-1]==ligne[i-1]:
                vo=True

    if dd and vo:
        nicecount+=1
print("part2 :",nicecount)

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")

    