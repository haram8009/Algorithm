n, k = map(int, input().split())
commands = [tuple(map(int, input().split())) for _ in range(k)]

# Please write your code here.
lst = [0]*n

for a, b in commands:
    for i in range(a-1,b):
            lst[i]+=1

print(max(lst))