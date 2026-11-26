---
schema_version: 1
platform: "Codetree"
title: "우리는 하나"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/test-we-are-the-one"
problem_id: "test-we-are-the-one"
difficulty: "보통"
topics: ["조합", "완전탐색", "BFS", "다중 시작점 BFS"]
study_group: ""
result: "Passed"
solved_at: "2026-11-23"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "시간 O(C(N², K) × N²), 공간 O(N² + K)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/우리는%20하나/we-are-the-one.java"
---

## 한 줄 인사이트

시작할 K개 칸을 조합으로 고른 뒤, 모든 시작점을 한 큐에 넣어 도달 가능한 칸의 합집합 크기를 센다.

## 문제와 접근

N×N 격자에서 K개 시작점을 선택한다. 상하좌우로 인접한 두 칸의 값 차이가 U 이상 D 이하일 때 이동할 수 있으며, 방문 가능한 칸 수의 최댓값을 구한다.

1. `dfs(depth, start)`로 N²개 칸 중 K개를 고른다.
2. 선택이 끝나면 다중 시작점 BFS를 실행한다.
3. 중복 없이 방문한 칸 수 `cnt`로 정답을 갱신한다.

## 코드 흐름

격자의 좌표를 `idx` 하나로 표현하면 행은 `idx / n`, 열은 `idx % n`이다. 다음 재귀에는 `idx + 1`을 넘기므로 같은 칸을 다시 고르거나 순서만 다른 선택을 중복 탐색하지 않는다.

BFS를 시작할 때 선택한 K개 좌표를 모두 큐에 넣고 방문 처리한다. 이후 다음 조건을 만족하는 이웃만 추가한다.

```java
int diff = Math.abs(grid[node.r][node.c] - grid[nr][nc]);
if (diff >= u && diff <= d) {
    visited[nr][nc] = true;
    q.offer(new Node(nr, nc, node.w + 1));
    cnt++;
}
```

서로 다른 시작점에서 같은 칸에 도달하더라도 `visited`를 공유하므로 한 번만 센다. 각 시작점을 따로 BFS하고 결과를 단순히 더하면 겹치는 영역을 중복 계산할 수 있다.

## 복잡도

- 시작점 조합 수: C(N², K)
- BFS 한 번: 각 칸을 최대 한 번 방문하므로 O(N²)
- 전체 시간: O(C(N², K) × N²)
- 공간: 격자, 방문 배열, 큐 O(N²), 선택 좌표 O(K)

## 주의점과 개선 메모

- `visited`는 시작점 조합이 바뀔 때마다 새로 만들어야 한다.
- 큐에 넣을 때 방문 처리해야 중복 삽입을 막는다.
- `maxDist`는 거리가 아니라 방문 가능한 칸 수의 최댓값이다.
- `Node.w`는 정답 계산에 사용되지 않으므로 좌표만 저장해도 된다.
- 이동 조건은 양방향으로 같으므로 연결 요소를 미리 구하는 접근도 생각할 수 있다. 현재 코드는 모든 시작점 조합마다 BFS하는 완전탐색 풀이이다.
