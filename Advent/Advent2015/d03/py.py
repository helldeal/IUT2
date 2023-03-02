
import time

start = time.time()

fichier=open("d03/input.txt","r")
text = fichier.readlines()
sum=[(0,0)]
x=0
y=0
for ligne in text:
    for i in ligne:
        if i=="v":
            y-=1
        if i=="^":
            y+=1
        if i=="<":
            x-=1
        if i==">":
            x+=1
        if (x,y) not in sum:
            sum.append((x,y))
    

print("part1 :",len(sum))


sum=[(0,0)]
x=0
y=0
x2=0
y2=0
santaswitch=False
for ligne in text:
    for i in ligne:
        santaswitch= not santaswitch
        if santaswitch:
            if i=="v":
                y-=1
            if i=="^":
                y+=1
            if i=="<":
                x-=1
            if i==">":
                x+=1
            if (x,y) not in sum:
                sum.append((x,y))
        else:
            if i=="v":
                y2-=1
            if i=="^":
                y2+=1
            if i=="<":
                x2-=1
            if i==">":
                x2+=1
            if (x2,y2) not in sum:
                sum.append((x2,y2))
                
print("part2 :",len(sum))

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")