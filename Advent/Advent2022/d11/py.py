
import time

start = time.time()

class Monkey:
  def __init__(self, items, operation, divisible, true, false):
    self.items = items
    self.operation = operation
    self.divisible = divisible
    self.true = true
    self.false = false
    self.inspection=0

  def estDivisible(self,nombre):
    return (nombre%self.divisible)==0

fichier=open("d11/input.txt","r")
text = fichier.readlines()
Monkeys=[]
items=[]
operation=()
divisible=0
true=0
false=0
for ligne in text:
    ligne=ligne.split("\n")[0].split()
    if len(ligne)<1:
        Monkeys.append(Monkey(items,operation,divisible,true,false))
    if "Starting" in ligne:
        items=[int(i.removesuffix(',')) for i in ligne[2:]]
    if "Operation:" in ligne:
        operation=(ligne[4],ligne[5])
    if "divisible" in ligne:
        divisible=int(ligne[3])
    if "true:" in ligne:
        true=int(ligne[5])
    if "false:" in ligne:
        false=int(ligne[5])
    # print(ligne)
    

import operator
import math

ops = {
    '+' : operator.add,
    '-' : operator.sub,
    '*' : operator.mul,
    '/' : operator.truediv,  # use operator.div for Python 2
    '%' : operator.mod,
    '^' : operator.xor,
}
supermodulo=math.prod([i.divisible for i in Monkeys])
for i in range(10000):
    for singe in Monkeys:
        for item in singe.items:
            singe.inspection+=1
            operator,value=singe.operation
            if value=="old":
                throw=ops[operator](item,item)
            else:
                throw=ops[operator](item,int(value))
            throw=throw%supermodulo
            if singe.estDivisible(throw):
                Monkeys[singe.true].items.append(throw)
            else:
                Monkeys[singe.false].items.append(throw)
        singe.items=[]



tabins=[i.inspection for i in Monkeys]
tabins.sort(reverse=True)
print("part1 :",tabins[0]*tabins[1],[i.inspection for i in Monkeys],[i.items for i in Monkeys])
print("part2 :")

end = time.time()
print("exec time :",
    (end-start) * 10**3, "ms")