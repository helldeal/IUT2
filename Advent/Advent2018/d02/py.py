
import time

start = time.time()

fichier=open("d02/input.txt","r")
text = fichier.readlines()
sum=0
sum2=0
for ligne in text:
    ligne=ligne.split("\n")[0].split("x")
    res=2*int(ligne[0])*int(ligne[1])+2*int(ligne[1])*int(ligne[2])+2*int(ligne[0])*int(ligne[2])+min([int(ligne[0])*int(ligne[1]),int(ligne[1])*int(ligne[2]),int(ligne[0])*int(ligne[2])])
    ligne=[int(i) for i in ligne]
    ligne.sort()
    res2=int(ligne[0])*int(ligne[1])*int(ligne[2])+int(ligne[0])+int(ligne[1])+int(ligne[0])+int(ligne[1])
    sum+=res
    sum2+=res2
    
    


print("part1 :",sum)
print("part2 :",sum2)

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")