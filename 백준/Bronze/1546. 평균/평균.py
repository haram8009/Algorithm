t = int(input())

arr= list(map(int, input().split()))

m = max(arr)

print((sum(arr)*100)/(m*len(arr)))