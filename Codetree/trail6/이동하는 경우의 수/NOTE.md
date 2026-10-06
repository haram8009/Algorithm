---
schema_version: 1
platform: "Codetree"
title: "이동하는 경우의 수"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-number-of-moving-cases"
notion_page_url: "https://app.notion.com/p/3f13d9b37e1c811d9112d24ece41d927"
legacy_title: "\\[코드트리\\] 이동하는 경우의 수"
problem_id: ""
difficulty: "Hard"
topics: ["Graph","DP"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-10-05"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N + M) / O(N + M)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail6/%EC%9D%B4%EB%8F%99%ED%95%98%EB%8A%94%20%EA%B2%BD%EC%9A%B0%EC%9D%98%20%EC%88%98/number-of-moving-cases.java"
---
## 문제 요약
방향 그래프의 각 간선에 이동 시간이 주어진다. 1번에서 N번까지 가는 최장 이동 시간과, 그 최장 시간 경로들에 포함되는 간선 수를 구한다.
## 풀이 접근
1. 위상 정렬 순서로 각 정점까지의 최장 시간 `dp[v]`를 갱신한다. 시작점 `dp[1]=0`, 도달 불가는 −1로 구분한다.
2. 간선을 역방향으로 저장해 두고 N번부터 BFS한다.
3. `dp[이전] + 간선시간 == dp[현재]`인 간선만 최장 경로에 속하므로 세고, 이전 정점을 방문 표시해 큐에 한 번만 넣는다.
## 구현에서 확인할 점
- 위상 정렬 차수는 최장 거리 갱신 여부와 무관하게 간선을 처리할 때마다 감소시킨다.
- 임계 경로의 간선 수를 세되, 정점은 방문 배열로 중복 큐 삽입을 막는다.
- 도달 불가능한 정점의 초기값과 실제 거리 0을 구분한다.
## 제출 기록
- 2026-10-05 · Java · Accepted · 1654ms / 98MB
- Trail 6 / 위상정렬 / Graph DP · 어려움
- [코드트리 문제](https://www.codetree.ai/trails/complete/curated-cards/challenge-number-of-moving-cases)
- [GitHub 커밋](https://github.com/haram8009/Algorithm/commit/c6d1d2aabab872acb9efe6ee3b9885b55722dc95)
- [저장된 Java 코드](https://github.com/haram8009/Algorithm/blob/main/Codetree/trail6/%EC%9D%B4%EB%8F%99%ED%95%98%EB%8A%94%20%EA%B2%BD%EC%9A%B0%EC%9D%98%20%EC%88%98/number-of-moving-cases.java)
## 자기 점검
- 왜 일반 BFS가 아니라 위상 정렬 순서로 DP를 해야 하는가?
- 최장 경로에 포함되는 간선을 판별하는 등식은 무엇인가?
- 여러 최장 경로가 같은 간선을 공유할 때 중복 집계가 생기지 않게 어떻게 처리하는가?
