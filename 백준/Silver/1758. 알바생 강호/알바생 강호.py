n = int(input())
arr=[]
for i in range(n):
    arr.append(int(input()))
    
arr.sort(reverse=True)

result=0
for i, c in enumerate(arr):
    tmp=c-i
    if tmp>0:
        result += (c-i)

print(result)
    