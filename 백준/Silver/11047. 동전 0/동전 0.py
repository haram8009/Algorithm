n, k = map(int, input().split())
coins = [0]*n

for i in range(n):
    coins[i] = int(input())
    
cnt=0
total=0

for i in range(n-1,-1,-1):
    if total==k:
        break
    if coins[i]>(k-total):
        continue
    this_cnt = (k-total)//coins[i]
    cnt+=this_cnt
    total += coins[i]*this_cnt

print(cnt)
        