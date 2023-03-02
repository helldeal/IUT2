import time

start = time.time()

fichier=open("d01/input.txt","r")
lignes = fichier.readlines()
tab=[[]]
tabsum =[]
i=0
for z in lignes:
    if z=="\n":
        tab.append([])
        tabsum.append(sum(tab[i])) 
        i+=1
    else:
        tab[i].append(int(z.replace("\n","")))

tabsum.sort()
print(sum(tabsum[-3:]))
end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")