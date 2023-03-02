
import time
import hashlib

start = time.time()
compt=1

fichier=open("d04/input.txt","r")
text = fichier.readlines()
for ligne in text:
    ligne=ligne.split("\n")[0]
    ligne+=str(compt)
    while "00000" != hashlib.md5(ligne.encode("utf-8")).hexdigest()[:5]:
        ligne=ligne[:len(ligne)-len(str(compt))]
        compt+=1
        ligne+=str(compt)
    
    
    


print("part1 :",compt)

for ligne in text:
    ligne=ligne.split("\n")[0]
    ligne+=str(compt)
    while "000000" != hashlib.md5(ligne.encode("utf-8")).hexdigest()[:6]:
        ligne=ligne[:len(ligne)-len(str(compt))]
        compt+=1
        ligne+=str(compt)
    
print("part2 :",compt)

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")