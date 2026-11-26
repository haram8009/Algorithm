---
schema_version: 1
platform: "Codetree"
title: "뿌요뿌요"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/test-puyo-puyo"
problem_id: "test-puyo-puyo"
difficulty: "보통"
topics: ["DFS", "연결 요소", "격자 탐색"]
study_group: ""
result: "Passed"
solved_at: "2026-11-23"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "시간 O(N²), 공간 O(N²)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/뿌요뿌요/puyo-puyo.java"
---

## 한 줄 인사이트

같은 숫자가 상하좌우로 이어진 연결 요소를 한 번씩 탐색하여, 크기가 4 이상인 요소의 개수와 가장 큰 요소의 크기를 구한다.

## 문제와 접근

N×N 격자에서 같은 숫자가 인접해 있는 묶음을 찾는다. 크기가 4 이상인 묶음의 개수와 전체 묶음 중 최대 크기를 출력한다.

모든 칸을 순회하면서 아직 방문하지 않은 칸에서 DFS를 시작한다. DFS 한 번이 연결 요소 하나를 완전히 탐색하므로 탐색이 끝났을 때의 `cnt`가 그 묶음의 크기이다.

## 코드 흐름

새 연결 요소를 시작할 때 다음 값을 초기화한다.

```java
num = grid[i][j];
cnt = 1;
visited[i][j] = true;
dfs(i, j);
```

DFS에서는 격자 범위 안에 있고, 아직 방문하지 않았으며, 시작 숫자 `num`과 같은 이웃만 방문한다.

```java
if (grid[nr][nc] == num && !visited[nr][nc]) {
    cnt++;
    visited[nr][nc] = true;
    dfs(nr, nc);
}
```

탐색이 끝나면 `maxCnt`를 갱신하고 `cnt >= 4`이면 `totalCnt`를 1 증가시킨다. `totalCnt`는 해당 묶음에 속한 칸 수의 합이 아니라 조건을 만족하는 묶음의 개수이다.

## 복잡도

바깥 반복문은 N²개 칸을 확인하고, DFS도 모든 탐색을 합쳐 각 칸을 한 번만 방문한다.

- 시간: O(N²)
- 공간: 격자와 방문 배열 O(N²), 최악의 재귀 깊이 O(N²)

## 주의점과 개선 메모

- 시작 칸을 이미 세었으므로 `cnt`는 1로 시작한다.
- 재귀 호출 전에 방문 처리해야 인접한 칸 사이를 반복해서 오가지 않는다.
- 방문 배열은 연결 요소마다 초기화하지 않고 전체 탐색에서 공유한다.
- 이 풀이는 연결 요소의 크기를 집계한다. 블록 제거, 중력, 연쇄 처리를 하는 시뮬레이션은 코드에 없다.
- 선언한 `TreeMap`은 계산에 사용되지 않으므로 제거할 수 있다.
- 격자가 커지면 재귀 깊이로 스택이 부족할 수 있다. 그 경우 명시적인 스택이나 BFS 큐로 바꿀 수 있다.
