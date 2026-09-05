n = int(input())
arr = list(map(int, input().split()))

# Please write your code here.
bubbled=True 
end=n-1
while(bubbled):
    bubbled=False
    for i in range(end):
        if arr[i]>arr[i+1]:
            tmp=arr[i]
            arr[i]=arr[i+1]
            arr[i+1] = tmp
            bubbled=True
    end-=1 

for a in arr:
    print(a, end=" ")