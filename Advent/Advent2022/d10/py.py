
import time

start = time.time()

fichier=open("d10/input.txt","r")
text = fichier.readlines()
value=1
cycle=1
CTR=""
Sprite=list(str("###............................................"))
frequence=[]
for ligne in text:
    ligne=ligne.split("\n")[0].split()

    if "noop" in ligne:
        CTR+=Sprite[len(CTR)]
        cycle+=1
    else:
        CTR+=Sprite[len(CTR)]
        cycle+=1
        if (cycle-20)%40==0:
            frequence.append(cycle*value)
        if (cycle-1)%40==0:
            print(CTR)
            CTR=""
        CTR+=Sprite[len(CTR)]
        cycle+=1
        value+=int(ligne[1])
        Sprite=''.join(Sprite)
        Sprite=Sprite.replace('#','.')
        Sprite=list(Sprite)
        for i in range(3):
            Sprite[value-1+i]="#"
    
    # print(cycle,CTR)
    if (cycle-20)%40==0:
        frequence.append(cycle*value)
    if (cycle-1)%40==0:
        print(CTR)
        CTR=""

    


print("part1 :",sum(frequence))
print("part2 : Letter form by the CTR")

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")