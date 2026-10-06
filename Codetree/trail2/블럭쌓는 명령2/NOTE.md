---
schema_version: 1
platform: "Codetree"
title: "블럭쌓는 명령2"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-block-stacking-commands2"
notion_page_url: "https://app.notion.com/p/3d03d9b37e1c812fa92bfc5ab3302564"
legacy_title: "\\[코드트리\\] 블럭쌓는 명령2"
problem_id: ""
difficulty: "Easy"
topics: ["Array/String"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-03"
retry_needed: true
retry_at: ""
language: ["Python"]
complexity: "O(n·k) / O(n)  ※ 차이 배열이면 O(n+k) / O(n)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail2/%EB%B8%94%EB%9F%AD%EC%8C%93%EB%8A%94%20%EB%AA%85%EB%A0%B92/block-stacking-commands2.py"
---
## 문제 요약
길이 n인 블럭 줄에 `(a, b)` 명령이 k개 주어진다. 각 명령은 1-indexed 닫힌 구간 \[a, b\]의 모든 칸에 블럭을 1개씩 쌓는다. 모든 명령을 수행한 뒤 가장 높이 쌓인 칸의 높이를 출력한다.
## 내 접근
길이 n 배열을 0으로 초기화하고, 명령마다 `range(a-1, b)`를 돌며 +1. 마지막에 `max(lst)`.
1-indexed 닫힌 구간 \[a, b\]를 0-indexed로 옮기면 \[a-1, b-1\]이고, 파이썬 `range`는 끝이 열려 있으므로 `range(a-1, b)`가 정확히 그 범위가 된다.
```python
lst = [0]*n
for a, b in commands:
    for i in range(a-1, b):
        lst[i] += 1
print(max(lst))
```
## 막힌 지점
## 배운 것 / 패턴
**구간 칠하기(imos / 차이 배열)의 원형 문제다.** 지금 풀이는 구간 길이만큼 매번 순회하므로 O(n·k). 구간의 *양 끝만* 건드리고 마지막에 한 번 누적하면 O(n+k)로 떨어진다.
```python
n, k = map(int, input().split())
diff = [0]*(n+1)
for _ in range(k):
    a, b = map(int, input().split())
    diff[a-1] += 1   # 구간 시작에서 +1
    diff[b]   -= 1   # 구간 끝 다음에서 -1

cur = best = 0
for i in range(n):
    cur += diff[i]
    best = max(best, cur)
print(best)
```
핵심 아이디어: **"각 칸의 값"을 직접 저장하는 대신 "이전 칸 대비 변화량"을 저장한다.** 구간 갱신이 O(1)이 되고, 값을 알고 싶을 때 한 번만 prefix sum으로 복원한다. `diff` 크기를 `n+1`로 잡는 이유는 `b == n`일 때 `diff[n]`에 -1을 써야 하기 때문 — 이 칸은 답 계산에 쓰이지 않는 sentinel이다.
같은 trail의 \[코드트리\] 최대로 겹치는 구간과 **완전히 같은 골격**이지만, 그쪽은 좌표축 위의 구간이라 인덱싱 규약이 다르다. 두 문제를 나란히 놓고 보면 "칸을 세는가 / 점을 세는가"의 차이가 선명해진다.
앞으로 연결되는 지점: 구간 갱신 + 구간 최댓값 질의가 **온라인**으로 섞여 들어오면 차이 배열로는 안 되고 세그먼트 트리 lazy propagation이 필요하다. 차이 배열은 "모든 갱신이 끝난 뒤 한 번만 조회"라는 오프라인 조건에서만 쓸 수 있는 최적화다.
## 다시 볼 때 체크할 것
- [ ] 차이 배열을 보지 않고 처음부터 쓸 수 있는가? `diff[a-1] += 1`, `diff[b] -= 1`의 인덱스를 헷갈리지 않는가?
- [ ] `diff` 배열 크기를 `n`이 아니라 `n+1`로 잡아야 하는 이유를 한 문장으로 설명할 수 있는가?
- [ ] 이 문제에서 차이 배열을 쓸 수 있는 조건(오프라인 갱신)이 깨지는 변형은 무엇인가?
