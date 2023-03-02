import time

start = time.time()

pair1=0
pair2=0
fichier=open("d04/input.txt","r")
text = fichier.readlines()
for ligne in text:
    ligne=ligne.split()[0].split(",")
    tab1=[]
    tab2=[]
    for i in range(int(ligne[0].split("-")[0]),int(ligne[0].split("-")[1])+1):
        tab1.append(i)
    for i in range(int(ligne[1].split("-")[0]),int(ligne[1].split("-")[1])+1):
        tab2.append(i)
    # print(ligne,tab1,tab2)
    if all(item in tab1 for item in tab2) or all(item in tab2 for item in tab1) :
        pair1+=1

    if any(item in tab1 for item in tab2) or any(item in tab2 for item in tab1) :
        pair2+=1

print("part1:",pair1)
print("part2:",pair2)
end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")