# 주유소
n = int(input())
road = list(map(int, input().split(" ")))
price = list(map(int, input().split(" ")))
# price = price[:-1] # 마지막 도시에선 주유 안하니까..

pos = 0  # 현재 도시 idx
next = 1  # 다음 주유할 도시 idx

charge = 0  # 낸 요금

while (next <= n-1):
    # 현재 주유소보다 가격이 싼 주유소 나올때까지 충전해야함
    # 다음 주유소가 더 싸다면 거기 갈때까지만 충전
    if price[pos] >= price[next]:
        for i in range(pos, next):  # pos~next-1
            charge += price[pos]*road[i]
        pos = next
    next += 1

print(charge)
