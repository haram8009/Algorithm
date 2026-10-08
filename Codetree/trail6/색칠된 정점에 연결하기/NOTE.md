---
schema_version: 1
platform: "Codetree"
title: "색칠된 정점에 연결하기"
problem_url: ""
notion_page_url: ""
legacy_title: "\\[코드트리\\] 색칠된 정점에 연결하기"
problem_id: ""
difficulty: ""
topics: ["Graph", "MST", "Greedy", "Priority Queue"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-10-08"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O((N + M) log M) / O(N + M)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail6/%EC%83%89%EC%B9%A0%EB%90%9C%20%EC%A0%95%EC%A0%90%EC%97%90%20%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0/connect-to-colored-vertices.java"
---

## 핵심 아이디어

색칠된 정점들은 이미 전원에 연결된 시작점들로 본다. 모든 정점을 하나의 MST로 합치는 대신, 각 일반 정점을 가장 싸게 색칠된 정점 집합에 연결한다. 색칠된 정점끼리는 연결할 필요가 없으며, 색칠된 정점의 비용은 0이다.

이를 다중 시작점 프림으로 구현한다.

## 알고리즘

1. 모든 색칠된 정점을 우선순위 큐에 비용 0으로 넣고, `dp[v]`를 현재 발견한 정점 `v`의 최소 연결 간선 비용으로 둔다.
2. 큐에서 비용이 가장 작은 항목을 꺼낸다. 이미 선택된 정점이면 건너뛰고, 아니면 선택한다.
3. 선택한 정점의 인접 정점 중 아직 선택되지 않은 정점에 대해, 간선 비용이 `dp`보다 작으면 `dp`와 큐를 갱신한다.
4. 최종 답은 모든 정점의 `dp` 합이다.

색칠된 정점은 `dp=0`인 채 시작한다. 간선 가중치가 음수가 아니고 갱신 조건이 엄격한 `<`이므로, 다른 색칠된 정점으로 향하는 간선이 색칠 정점의 0 비용을 대체하지 않는다.

## 우선순위 큐의 중복 항목

한 정점의 `dp`가 더 작은 값으로 갱신되어도 이전 큐 항목은 큐에 남을 수 있다. 큐에서 꺼낼 때 `selected[v]`를 확인하면, 가장 작은 후보로 정점이 처음 확정된 뒤의 중복 항목을 버릴 수 있다.

또는 꺼낸 비용과 현재 `dp[v]`를 비교해 낡은 항목을 버릴 수 있다. 두 방식 모두 사용할 수 있지만, 방문 여부와 거리를 일관되게 확인해야 한다.

## 복잡도

인접 리스트와 이진 힙을 사용하면 시간 복잡도는 `O((N + M) log M)`, 공간 복잡도는 `O(N + M)`이다.
