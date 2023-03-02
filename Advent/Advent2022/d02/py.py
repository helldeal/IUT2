
# A rock X
# B paper Y
# C scisoor Z

import time

start = time.time()

score=0
combi={
    "A":1,
    "X":1,
    "B":2,
    "Y":2,
    "C":3,
    "Z":3
}
fichier=open("d02/input.txt","r")
text = fichier.readlines()
for ligne in text:
    score+=(combi[ligne[2]]-combi[ligne[0]]-2)%3*3+combi[ligne[2]]

print(score)
end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")