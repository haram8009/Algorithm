from collections import deque

t = int(input())

class Item:
    def __init__(self, value, priority) -> None:
        self.value = value
        self.priority = priority
    
    def __lt__(self, other):
        return self.priority < other.priority
    
    def __str__(self) -> str:
        return f"( {self.value}, {self.priority} )"


for _ in range(t):
    n, m = map(int, input().split())
    arr = list(map(int, input().split()))
    q = deque()

    for i in range(n):
        q.append(Item(i, arr[i]))

    cnt=0
    while(True):
        if q[0].priority == max(q).priority:
            cnt+=1

            if q[0].value == m:
                print(cnt)
                break
            else:
                q.popleft()
        else:
            q.append(q.popleft())