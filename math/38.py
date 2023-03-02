
import random

def tirage(li,n):
    ls=[]
    for i in range(n):
        l=[]
        for i in range(4):
            l.append(random.choice(li))
        ls.append(l)

    return ls

def bbprob(ls):
    prob=0
    for i in ls:
        if i.count("b")==2:
            prob+=1
    prob=prob/len(ls)
    return prob

def bplusprob(ls):
    prob=0
    for i in ls:
        if i.count("b")>0:
            prob+=1
    prob=prob/len(ls)
    return prob

li=["b","b","b","b","b","b","b","n","n","n"]
ls=tirage(li,1000000)
print("2 blancs :",bbprob(ls))
print("Au moins 1 blanc :",bplusprob(ls))

