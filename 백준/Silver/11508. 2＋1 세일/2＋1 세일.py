n = int(input())
arr=[]
for i in range(n):
    arr.append(int(input()))
    
arr.sort(reverse=True)

result =0
for i, c in enumerate(arr):
    if (i+1)%3 != 0:
        result += c
    
print(result)
    