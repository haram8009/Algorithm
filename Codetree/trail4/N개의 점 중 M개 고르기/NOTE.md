---
schema_version: 1
platform: "Codetree"
title: "N개의 점 중 M개 고르기"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/test-choose-m-out-of-n-points"
problem_id: "test-choose-m-out-of-n-points"
difficulty: "보통"
topics: ["조합", "백트래킹", "완전탐색"]
study_group: ""
result: "Passed"
solved_at: "2026-11-13"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "시간 O(N² + Σ(k=0..M) C(N,k) + C(N,M) × M²), 공간 O(N² + M)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/N개의%20점%20중%20M개%20고르기/choose-m-out-of-n-points.java"
---

## 한 줄 인사이트

M개 점의 조합을 모두 만들고, 각 조합의 가장 먼 두 점 사이 거리의 제곱을 구해 그중 최솟값을 선택한다.

## 문제 요약

N개 점 중 M개를 선택한다. 선택한 점들 사이에서 가장 먼 두 점의 거리의 제곱이 최소가 되도록 하고, 그 값을 출력한다.

## 현재 코드의 접근

먼저 모든 점 쌍의 거리의 제곱을 저장한다.

```java
dist[i][j] = (x1 - x2) * (x1 - x2)
           + (y1 - y2) * (y1 - y2);
```

제곱근은 크기 순서를 바꾸지 않으며 문제에서 요구하는 값도 거리의 제곱이므로 제곱근을 구하지 않는다.

`dfs(s, cnt)`는 다음에 선택할 수 있는 시작 인덱스 s와 현재 선택 개수 cnt를 뜻한다.

```java
selected[cnt] = i;
dfs(i + 1, cnt + 1);
```

다음 인덱스를 i + 1부터 고르므로 같은 점을 중복 선택하지 않고, 선택 순서만 다른 경우도 다시 만들지 않는다.

M개를 고르면 `calc()`에서 모든 선택 점 쌍을 비교한다. tmp에는 현재 조합의 가장 큰 거리를 저장하고, answer에는 조합별 tmp의 최솟값을 저장한다. 즉 **조합 안에서는 최댓값, 조합 사이에서는 최솟값**이다.

## 거리 배열의 절반만 채워도 되는 이유

코드는 i < j인 거리만 저장한다. 선택 인덱스가 항상 증가하도록 조합을 만들기 때문에 계산할 때도 p1 < p2가 성립한다. 따라서 채워진 원소만 참조한다.

선택 순서를 바꾸는 방식으로 수정한다면 거리 배열을 대칭으로 채우거나 작은 인덱스부터 접근해야 한다.

## 복잡도

- 거리 전처리: 시간 O(N²), 공간 O(N²).
- 완성된 조합 평가: C(N, M)개 조합마다 O(M²).
- 현재 DFS는 남은 점 수에 따른 가지치기가 없으므로 M개를 채우지 못하는 부분 조합도 방문한다. 탐색 노드 수는 Σ(k=0..M) C(N,k)로 나타낼 수 있다.
- 전체 시간: O(N² + Σ(k=0..M) C(N,k) + C(N,M) × M²).
- 전체 공간: O(N² + M). 거리 배열, 선택 배열, 재귀 스택을 포함한다.

## 주의할 점과 개선

- Math.max로 현재 조합의 최장 거리를 구한 뒤 Math.min으로 전체 답을 갱신한다.
- 좌표 범위가 커지면 차이의 제곱에서 int가 넘칠 수 있다. 그런 조건에서는 곱셈 전에 long으로 변환해야 한다.
- 반복문을 `i <= n - (m - cnt)`까지로 제한하면 남은 점으로 M개를 채울 수 없는 가지를 만들지 않는다.
- 점 하나를 추가할 때 기존 선택 점들과 비교한 현재 최댓값을 전달할 수도 있다. 그 값이 이미 answer 이상이면 더 탐색하지 않는 가지치기가 가능하다.
