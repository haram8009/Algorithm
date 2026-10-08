---
schema_version: 1
platform: "Codetree"
title: "화성 탐사"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-explore-mars"
notion_page_url: ""
legacy_title: ""
problem_id: ""
difficulty: "Medium"
topics: ["MST","Shortest Path","Dijkstra","Kruskal"]
study_group: ""
result: "Accepted"
solved_at: "2026-10-08"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(K·N² log N + K² log K) / O(N² + K²)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail6/%ED%99%94%EC%84%B1%20%ED%83%90%EC%82%AC/explore-mars.java"
---

## 문제 요약
N×N 격자에서 값이 -1인 칸은 통과할 수 없고, 값이 1 또는 2인 칸은 기지다. 기지 사이의 통행 가능한 최단 거리를 간선 가중치로 두고, 모든 기지를 연결하는 최소 이동 거리 또는 연결 불가 시 -1을 출력한다.

## 코드의 접근
각 기지를 시작점으로 우선순위 큐 기반 최단 거리 탐색을 실행해 다른 기지까지의 거리를 구한다. 기지 쌍의 거리를 간선으로 저장한 뒤, 정렬된 간선을 Union-Find로 처리하는 크루스칼 알고리즘으로 MST를 만든다. 선택 간선 수가 기지 수보다 하나 적지 않으면 연결할 수 없다고 판단한다.

## 핵심 원리
격자에서 기지 쌍을 직접 연결하는 대신, 실제 격자 이동 비용의 최솟값을 완전 그래프의 간선 비용으로 정의한다. 이 그래프의 MST는 모든 기지를 연결하는 총 이동 거리의 최솟값을 제공한다. 기지 수를 K라 하면 격자 최단 경로 계산은 기지마다 한 번씩 수행된다.

## 커밋에서 확인한 변경과 주의점
기간 내 커밋은 기지에서 바로 인접한 통로가 없는 경우를 미리 실패 처리하던 가지치기를 주석 처리했다. 인접 칸이 막혀 있어도 더 먼 우회 경로로 다른 기지에 도달할 수 있으므로, 한 칸의 이웃만 보고 전체 연결 불가를 결론 내릴 수 없다. 최신 코드는 실제 최단 경로 간선을 만든 뒤 MST 간선 수로 연결 가능성을 판정한다.

## 복잡도
N은 격자 한 변의 길이, K는 기지 수다. 기지마다 N²개 칸을 우선순위 큐로 탐색해 O(K·N² log N), 기지 쌍 K²개의 간선을 정렬해 O(K² log K)가 걸린다. 간선 목록과 격자 거리 배열을 포함한 공간은 O(N² + K²)다.

## 한 줄 인사이트
기지들을 연결하는 문제는 기지 쌍별 격자 최단 거리를 간선 비용으로 바꾸면 MST 문제로 풀 수 있다.

## 복습 질문
- 한 기지의 인접 칸이 전부 막혔다는 사실만으로 연결 불가라고 할 수 없는 이유는?
- 격자에서 계산한 기지 쌍 거리를 MST 간선 가중치로 써도 되는 이유는?
- Kruskal 종료 시 연결 여부를 간선 수로 어떻게 판별하는가?
