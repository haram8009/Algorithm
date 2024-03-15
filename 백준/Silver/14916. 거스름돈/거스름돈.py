# 거스름돈

n = int(input())

count5 = 0
count2 = 0

while (n):
    if n >= 5:
        n -= 5
        count5 += 1
    else:
        break

while (n):
    if n >= 2:
        n -= 2
        count2 += 1
    elif n == 1:
        if count5 > 0:
            n += 5
            count5 -= 1
        else:
            count2 = 0
            break

ans = count5 + count2

print(ans if ans > 0 else -1)
