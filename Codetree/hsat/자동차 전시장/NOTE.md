---
schema_version: 1
platform: "Codetree"
title: "자동차 전시장"
problem_url: "https://www.codetree.ai/frequent-problems/hsat/problems/car-showroom"
problem_id: "car-showroom"
difficulty: ""
topics: ["BFS", "최단 경로", "그래프", "최댓값 최소화"]
study_group: ""
result: "Passed"
solved_at: "2026-10-23"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "시간 O(k(n+m)), 공간 O(kn+n+m)"
time_minutes: ""
code_url: ""
---

## 한 줄 인사이트

각 출발점에서 BFS로 거리를 구하고, 후보마다 가장 늦게 도착하는 사람의 거리를 구해 그 최댓값을 최소화한다.

## 문제를 식으로 바꾸기

`dist[i][j]`는 i번째 사람의 출발점에서 j번 정점까지의 최단 거리다. 전시장을 j에 설치했을 때 모두 도착하는 시간은 `max_i dist[i][j]`이므로 답은 `min_j max_i dist[i][j]`다. 거리 합을 최소화하는 문제가 아니다.

## 풀이 흐름

1. 입력 간선을 방향 그대로 인접 리스트에 넣는다.
2. k명의 각 출발점에서 BFS를 실행해 거리 배열을 채운다.
3. 모든 정점을 순회하며 사람별 거리의 최댓값을 구한다.
4. 이 값들의 최솟값을 출력한다. 모두 도달할 수 있는 정점이 없으면 -1이다.

간선의 이동 비용이 모두 1이므로 FIFO 큐의 BFS로 최단 거리를 구할 수 있다. 모든 출발점을 동시에 넣는 BFS는 가장 가까운 출발점까지의 거리만 구하므로 사람별 거리 계산을 대신할 수 없다.

## 현재 코드의 방문 처리

큐에서 꺼낼 때 방문 처리한다. 같은 정점이 여러 번 들어갈 수 있지만 처음 꺼낸 경로가 최단 경로이고, 방문한 정점은 다시 확장하지 않는다. 도달하지 못한 거리는 `Integer.MAX_VALUE`로 남으므로 후보의 최댓값에도 반영되어 제외된다.

## 복잡도

- 시간: BFS 한 번 `O(n+m)`, k번 BFS와 후보 평가를 합해 `O(k(n+m))`.
- 공간: 거리 배열 `O(kn)`, 인접 리스트 `O(n+m)`. 현재 방문 방식의 큐는 최악 `O(m)`이므로 전체 `O(kn+n+m)`.

## 주의점과 개선

- 방향 그래프이므로 반대 간선을 추가하면 안 된다.
- 시작 정점의 거리는 0이다. 사람이 없는 정점도 후보나 경로가 될 수 있다.
- 큐에 넣을 때 방문 처리하면 중복 삽입을 막고 큐 공간을 `O(n)`으로 줄일 수 있다.
- 변수 이름은 pq지만 실제로는 ArrayDeque다. 우선순위 큐가 아니므로 Node의 Comparable은 필요하지 않다.
