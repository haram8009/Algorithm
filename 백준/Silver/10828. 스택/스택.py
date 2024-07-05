import sys
input=sys.stdin.readline

n = int(input())
stack = []

for _ in range(n):
    line = list(input().strip().split())
    op = line[0]
    if op == "push":
        stack.append(line[1])
    elif op == "pop":
        if len(stack):
            print(stack[-1])
            stack= stack[:-1]
        else:
            print("-1")
    elif op == "size":
        print(len(stack))
    elif op == "empty":
        print("0" if len(stack) else "1")
    elif op == "top":
        if len(stack):
            print(stack[-1])
        else:
            print("-1")
