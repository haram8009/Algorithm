n, m = map(int, input().split())
edges = [tuple(map(int, input().split())) for _ in range(m)]

arr={}
# Please write your code here.
for node1, node2 in edges:
    if node1==0 or node2==0:
        continue
    if node1 not in arr:
        arr[node1] = [node2]
    else:
        arr[node1].append(node2)
    if node2 not in arr:
        arr[node2] = [node1]
    else:
        arr[node2].append(node1)

cnt=0
visited=[]
def dfs(start):
    global cnt, arr
    if start in visited:
        return
    # 방문한적없으면
    cnt+=1
    visited.append(start)
    for neighbor in arr[start]:
        dfs(neighbor)

if 1 not in arr:
    print(0)
else:
    dfs(1)
    print(cnt-1)