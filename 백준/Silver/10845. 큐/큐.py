import sys

input = lambda: sys.stdin.readline().rstrip()

t = int(input())

q = []

answer = ""

for _ in range(t):
    line = input().split()
    op = line[0]
    if op == "push":
        q.append(line[1])
    elif op == "size":
        answer+= f"{len(q)}\n"
    elif op == "empty":
        answer += f"{0 if len(q) else 1}\n"
    elif op == "front":
        if len(q):
            answer += f"{q[0]}\n"
        else:
            answer += "-1\n"
    elif op == "back":
        if len(q):
            answer += f"{q[-1]}\n"
        else:
            answer += "-1\n"
    elif op == "pop":
        if len(q):
            answer += f"{q[0]}\n"
            q = q[1:]
        else:
            answer += "-1\n"
            
print(answer)
            
