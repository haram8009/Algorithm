# 사과 담기 게임
n, m = map(int, input().split(" "))
j = int(input())

start = 1
end = m

movement = 0

for i in range(j):
    pos = int(input())
    # 범위 안에 있을 때
    if start <= pos and end >= pos:
        continue
    # 왼쪽에 있을 때
    elif pos < start:
        mv = start-pos
        start -= mv
        end -= mv
        movement += mv
    # 오른쪽에 있을 때
    else:  # pos>end
        mv = pos-end
        start += mv
        end += mv
        movement += mv

print(movement)
