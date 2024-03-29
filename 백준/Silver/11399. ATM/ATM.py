n=int(input())
lst=list(map(int,input().split(" ")))
lst.sort()
total=0
thisSum=0
for l in lst:
    thisSum+=l
    total+=thisSum
    
print(total)