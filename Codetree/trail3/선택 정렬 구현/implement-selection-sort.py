n = int(input())
arr = list(map(int, input().split()))

# Please write your code here.
for i in range(n):
    min = i
    for j in range(i+1,n):
        if arr[j] < arr[min]:
            min = j
    tmp = arr[i]
    arr[i] = arr[min]
    arr[min] = tmp

for a in arr:
    print(a, end=" ")