n = int(input())
segments = [tuple(map(int, input().split())) for _ in range(n)]

# Please write your code here.
lst = [0]*200

for l,r in segments:
    for i in range(l+100, r+100):
        lst[i]+=1

print(max(lst))