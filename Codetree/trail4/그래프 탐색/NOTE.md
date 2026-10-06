---
schema_version: 1
platform: "Codetree"
title: "그래프 탐색"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-graph-traversal"
notion_page_url: "https://app.notion.com/p/3d13d9b37e1c81869e74f9630cff88d2"
legacy_title: "\\[코드트리\\] 그래프 탐색"
problem_id: ""
difficulty: "Easy"
topics: ["BFS/DFS","Graph"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-04"
retry_needed: true
retry_at: ""
language: ["Python"]
complexity: "O(V·(V+E)) / O(V+E)  ※ visited를 set/bool 배열로 바꾸면 O(V+E)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EA%B7%B8%EB%9E%98%ED%94%84%20%ED%83%90%EC%83%89/graph-traversal.py"
---
## 문제 요약
노드 n개, 무방향 간선 m개가 주어진다. **1번 노드에서 출발해 도달할 수 있는 노드의 개수**를 출력한다(1번 자기 자신은 제외). Trail 4 / DFS 단원의 첫 개념 문제.
## 내 접근
딕셔너리로 인접 리스트를 만들고(양방향 삽입), 재귀 DFS로 방문 노드 수를 세서 `cnt-1`을 출력.
```python
arr = {}
for node1, node2 in edges:
    if node1 == 0 or node2 == 0:
        continue
    arr.setdefault(node1, []).append(node2)   # 원본은 if/else로 분기
    arr.setdefault(node2, []).append(node1)

cnt = 0
visited = []
def dfs(start):
    global cnt
    if start in visited:      # ← 함수 '진입 시' 방문 체크
        return
    cnt += 1
    visited.append(start)
    for neighbor in arr[start]:
        dfs(neighbor)

if 1 not in arr:
    print(0)
else:
    dfs(1)
    print(cnt - 1)
```
답은 맞고 52ms로 통과했다. 다만 통과한 이유가 "제약이 작아서"인 부분이 두 군데 있다.
## 막힌 지점
## 배운 것 / 패턴
### 1. `visited`를 리스트로 둔 게 유일한 진짜 성능 문제다
`start in visited`는 리스트를 앞에서부터 훑는 **선형 탐색 O(V)**다. dfs 호출은 총 O(V+E)번 일어나므로 전체가 **O(V·(V+E))**로 부풀어 오른다.
자료구조만 바꾸면 끝난다:
```python
visited = set()          # in 검사 O(1) 평균
# 또는
visited = [False]*(n+1)  # 인덱스 접근 O(1) 확정, 이 문제처럼 번호가 1~n이면 이쪽이 더 좋다
```
**반례로 감을 잡을 것:** 노드 10만 개가 `1-2-3-...-100000` 체인으로 이어진 그래프. 리스트 버전은 100000번째 노드를 볼 때마다 앞의 10만 개를 다 훑으므로 약 10\^10번 비교 → 몇 분. set 버전은 같은 입력을 0.1초에 끝낸다. 이 문제는 n이 작아서 둘 다 52ms로 보일 뿐이다.
### 2. 재귀 깊이 — 두 번째 "우연히 통과"
파이썬 기본 재귀 한도는 **1000**이다. 그래프가 일자로 늘어서면 DFS 깊이가 노드 수만큼 된다.
- **통과하는 예:** 별 모양 그래프(1번이 중심, 나머지 전부 1번과만 연결). 노드가 10만 개여도 재귀 깊이는 2.
- **터지는 반례:** `1-2-3-...-1500` 체인. 노드가 고작 1500개인데 `RecursionError: maximum recursion depth exceeded`로 죽는다.
노드 수가 아니라 **그래프 모양**이 결정한다는 게 핵심. 그래서 재귀 DFS를 쓸 때는 사실상 관습적으로 `sys.setrecursionlimit(10**6)`을 깔고 들어간다. 아예 안전하게 가려면 스택 반복문:
```python
def dfs_iter(start):
    visited = [False]*(n+1)
    stack = [start]
    visited[start] = True
    while stack:
        cur = stack.pop()
        for nxt in graph[cur]:
            if not visited[nxt]:
                visited[nxt] = True     # 푸시하는 순간 방문 표시 — 중복 푸시 방지
                stack.append(nxt)
    return sum(visited) - 1
```
### 3. 방문 체크를 "진입 시"에 하느냐 "호출 전"에 하느냐
내 코드는 **진입 시 체크**다 — 일단 `dfs(neighbor)`를 전부 부르고, 함수 첫 줄에서 `if start in visited: return`으로 되돌려보낸다. 둘 다 정답이지만 차이가 있다:
<table header-row="true">
<tr>
<td></td>
<td>진입 시 체크 (내 코드)</td>
<td>호출 전 체크</td>
</tr>
<tr>
<td>함수 호출 횟수</td>
<td>간선마다 양쪽으로 1번씩 = O(V+2E)</td>
<td>실제 방문하는 노드만 = O(V)</td>
</tr>
<tr>
<td>부모로 되돌아가는 호출</td>
<td>반드시 발생(바로 return되지만 프레임은 쌓인다)</td>
<td>없음</td>
</tr>
<tr>
<td>코드 길이</td>
<td>짧다</td>
<td>살짝 길다</td>
</tr>
</table>
무방향 그래프라 2-3이 연결되면 `dfs(2)` 안에서 `dfs(3)`, 그 안에서 다시 `dfs(2)`가 **반드시** 불린다. 바로 return하니 정답은 같지만, 호출 오버헤드가 간선 수에 비례해 붙는다. 진입 시 체크는 대신 **여러 시작점에서 돌리는 경우(연결 요소 세기)**에 코드가 깔끔해지는 장점이 있다.
### 4. 자잘한 것들
- `if node1 == 0 or node2 == 0: continue` — 노드 번호가 1\~n이면 0은 안 들어온다. 디버깅 잔재로 보이는데, 남겨두면 "0번 노드가 있는 문제"로 올 때 조용히 틀린 답을 낸다.
- `if 1 not in arr: print(0)` 특수 분기 — 인접 리스트를 `[[] for _ in range(n+1)]`로 잡으면 고립 노드도 빈 리스트라 **분기 자체가 사라진다.** 딕셔너리로 인접을 담으면 계속 키 존재 검사를 해야 한다.
- `global arr`는 불필요 — 읽기만 하는 이름은 global 선언 없이도 바깥 스코프에서 찾아진다. `global cnt`만 진짜로 필요하다(재할당하므로).
### 정리된 버전
```python
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

n, m = map(int, input().split())
graph = [[] for _ in range(n+1)]
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

visited = [False]*(n+1)

def dfs(cur):
    visited[cur] = True
    for nxt in graph[cur]:
        if not visited[nxt]:   # 호출 전 체크
            dfs(nxt)

dfs(1)
print(sum(visited) - 1)
```
### 앞으로 연결되는 지점
이 골격을 그대로 두고 바깥 루프만 얹으면 **연결 요소 개수**(1\~n 도며 미방문이면 dfs 호출 + 카운터 +1)가 되고, 방문 표시를 bool 대신 깊이/색으로 바꾸면 **최단경로(BFS)**와 **사이클 판정**이 된다. Trail 4의 격자 문제들도 결국 "격자 칸 = 노드, 상하좌우 = 간선"으로 보는 같은 DFS다 — 인접 리스트를 명시적으로 만드느냐 방향 벡터로 암묵적으로 두느냐의 차이만 있다.
## 다시 볼 때 체크할 것
- [ ] `visited`를 리스트로 둔 풀이가 왜 O(V·(V+E))가 되는지, 어떤 모양의 입력에서 가장 느려지는지 설명할 수 있는가?
- [ ] 재귀 DFS가 `RecursionError`로 터지는 그래프 모양과, 노드가 많아도 안 터지는 모양을 각각 하나씩 들 수 있는가?
- [ ] 방문 체크를 "진입 시"에서 "호출 전"으로 옮기면 함수 호출 횟수가 어떻게 바뀌는가? 스택 반복문에서는 방문 표시를 pop할 때가 아니라 push할 때 해야 하는 이유는?
