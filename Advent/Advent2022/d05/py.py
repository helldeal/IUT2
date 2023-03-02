import time

start = time.time()

stock=""
stock2=""
fichier=open("d05/input.txt","r")
text = fichier.readlines()
chargefaite=False
cargaison=[]
cargaison2=[]
for ligne in text:
    ligne=ligne.split("\n")[0]
    ligne=ligne.replace("    "," empty ").split()
    # print(ligne)
    if len(ligne)>0 and chargefaite:
        # print(ligne[1],ligne[3],ligne[5])
        for i in range(int(ligne[1])):
            cargaison[int(ligne[5])-1].insert(0,cargaison[int(ligne[3])-1][0])
            cargaison[int(ligne[3])-1].remove(cargaison[int(ligne[3])-1][0])
            cargaison2[int(ligne[5])-1].insert(i,cargaison2[int(ligne[3])-1][0])
            cargaison2[int(ligne[3])-1].remove(cargaison2[int(ligne[3])-1][0])
        # print(cargaison)
    if not chargefaite and ligne[0]=="1":
        chargefaite=True
        print(cargaison)
    if not chargefaite:
        while len(cargaison)<len(ligne):
            cargaison.append([])
            cargaison2.append([])
        
        for i in range(len(ligne)):
            if ligne[i]!="empty":
                cargaison[i].append(ligne[i])
                cargaison2[i].append(ligne[i])
    

stock=[i[0] for i in cargaison]
stock2=[i[0] for i in cargaison2]

print("part1:",stock)
print("part2:",stock2)
end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")