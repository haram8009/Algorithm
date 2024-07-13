t = int(input())

arr=[]
for _ in range(t):
    arr.append(input().split())
    
arr = sorted(arr, key=lambda x: int(x[0]))

for a,b in arr:
    print(a,b)