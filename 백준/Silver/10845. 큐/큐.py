import sys

input = lambda: sys.stdin.readline().rstrip()

t = int(input())

q = []

for _ in range(t):
    line = input().split()
    op = line[0]
    if op == "push":
        q.append(line[1])
    elif op == "size":
        print(len(q))
    elif op == "empty":
        print(0 if len(q) else 1)
    elif op == "front":
        if len(q):
            print(q[0])
        else:
            print("-1")
    elif op == "back":
        if len(q):
            print(q[-1])
        else:
            print("-1")
    elif op == "pop":
        if len(q):
            print(q[0])
            q = q[1:]
        else:
            print("-1")
            

