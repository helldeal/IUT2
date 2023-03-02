
import numpy as np
import matplotlib.pyplot as plt
import time
import copy

start = time.time()

fichier=open("d09/input.txt","r")
text = fichier.readlines()

H=[0,0]
T=[0,0]
Tailpath=[]
for ligne in text:
    ligne=ligne.split("\n")[0]
    for i in range(int(ligne[2:])):
        if ligne[0]=="R":
            H[0]+=1
        if ligne[0]=="L":
            H[0]-=1
        if ligne[0]=="U":
            H[1]+=1
        if ligne[0]=="D":
            H[1]-=1
        x=H[0]-T[0]
        y=H[1]-T[1]
        if 1<x or x<-1:
            T[0]+=x/2
            if y!=0:
                T[1]+=y
        elif 1<y or y<-1:
            T[1]+=y/2
            if x!=0:
                T[0]+=x
        
        Tail=copy.deepcopy(T)
        Tailpath.append(Tail)

        # print("Head",H)
        # print("Tail",T)
        # plt.grid(True,'major')
        # tha=plt.scatter(T[0],T[1],color='crimson')
        # bha=plt.scatter(H[0],H[1],color='blue')
        # plt.draw()
        # plt.pause(0.001)
        # bha.remove()
        # tha.set_color('black')



    
    
    

print("part1 :",len(set(tuple(i) for i in Tailpath)))

H=[0,0]
Rope=[H,[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]
Tailpath=[]

def move(Head,Tails):
    x=int(Head[0]-Tails[0])
    y=int(Head[1]-Tails[1])
    if 1<x or x<-1:
        Tails[0]+=np.sign(x)
        if y!=0:
            Tails[1]+=np.sign(y)
    elif 1<y or y<-1:
        Tails[1]+=np.sign(y)
        if x!=0:
            Tails[0]+=np.sign(x)

for ligne in text:
    ligne=ligne.split("\n")[0]
    for i in range(int(ligne[2:])):
        if ligne[0]=="R":
            H[0]+=1
        if ligne[0]=="L":
            H[0]-=1
        if ligne[0]=="U":
            H[1]+=1
        if ligne[0]=="D":
            H[1]-=1
        
        for i in range(1,len(Rope)):
            move(Rope[i-1],Rope[i])

        Tail=copy.deepcopy(Rope[9])
        Tailpath.append(Tail)

        # print("Head",H)
        # print("Tail",Rope[1:])
        plt.grid(True,'major')
        X=[i[0] for i in Rope[1:]]
        Y=[i[1] for i in Rope[1:]]
        tail=plt.scatter(X[-1],Y[-1],color='black')
        tha=plt.scatter(X,Y,color='crimson')
        bha=plt.scatter(H[0],H[1],color='blue')
        plt.draw()
        plt.pause(0.1)
        bha.remove()
        tha.remove()

print("part2 :",len(set(tuple(i) for i in Tailpath)))

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")




    
