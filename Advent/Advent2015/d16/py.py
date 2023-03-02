
import time

start = time.time()

fichier=open("d04/input.txt","r")
text = fichier.readlines()
for ligne in text:
    ligne=ligne.split("\n")[0]
    
    
    


print("part1 :")
print("part2 :")

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")