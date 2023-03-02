
from anytree import Node, RenderTree
import time

start = time.time()
fichier=open("d07/input.txt","r")
text = fichier.readlines()
tree={}
listdir=[]
pointeur=""
tree["base/"]=base=Node("base")
for ligne in text:
    ligne=ligne.split("\n")[0]
    if "$ cd" in ligne:
        ligne=ligne.removeprefix("$ cd ")
        pointeur+=ligne
        if pointeur=="/":
            pointeur="base"
        pointeur+="/"
        if pointeur[-4:]=="/../":
            pointeur=pointeur[:pointeur[:-4].rfind("/")] +"/"
        
    elif "$ ls" in ligne:
        pointeur=pointeur
    elif "dir " in ligne:
        dire=ligne.removeprefix("dir ")
        tree[pointeur+dire+"/"]=Node(ligne, parent=tree[pointeur])
        listdir.append(pointeur+dire+"/")
        
    else :
        ligne=ligne.split()[0]
        node=Node(ligne, parent=tree[pointeur])
listdir.append("base/")
countdir=[]
for i in range(len(listdir)):
    countdir.append(0)
    for pre, fill, node in RenderTree(tree[listdir[i]]):
        # print("%s%s" % (pre, node.name))
        if "dir" not in node.name and node.name!="base":
            countdir[i]+=int(node.name)
needclean=70000000-countdir[-1]-30000000
res=[d for d in countdir if needclean+d>=0]
res.sort()
print("part1 :",sum([d for d in countdir if d <= 100000]))
print("part2 :",res[0])

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")