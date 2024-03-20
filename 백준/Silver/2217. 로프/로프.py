n = int(input())
loops = []
for i in range(n):
    loops.append(int(input()))
loops.sort(reverse=True)

# 1개만 썼을 때 최대 버틸 수 있는 중량..
# 2개 썼을 떄 ..
# ...
# n개 썼을 때..
# => 이중에서 최대값?
# n개 중 가장 작은값 * n

mess = []
for i in range(1, n+1):
    # i개 썼을 때 최대 버틸 수 있는 중량
    m = loops[i-1] * i
    mess.append(m)

mess.sort(reverse=True)
print(mess[0])  # 가장 큰 중량
