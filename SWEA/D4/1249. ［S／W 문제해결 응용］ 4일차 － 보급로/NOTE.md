---
schema_version: 1
platform: "SWEA"
title: "보급로"
problem_url: "https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD"
notion_page_url: ""
legacy_title: ""
problem_id: "1249"
difficulty: "D4"
topics: ["Shortest Path","Dijkstra","Priority Queue"]
study_group: ""
result: "Accepted"
solved_at: "2026-10-07"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N² log N) / O(N²)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/SWEA/D4/1249.%E2%80%85%EF%BC%BBS%EF%BC%8FW%E2%80%85%EB%AC%B8%EC%A0%9C%ED%95%B4%EA%B2%B0%E2%80%85%EC%9D%91%E7%94%A8%EF%BC%BD%E2%80%854%EC%9D%BC%EC%B0%A8%E2%80%85%EF%BC%8D%E2%80%85%EB%B3%B4%EA%B8%89%EB%A1%9C/%EF%BC%BBS%EF%BC%8FW%E2%80%85%EB%AC%B8%EC%A0%9C%ED%95%B4%EA%B2%B0%E2%80%85%EC%9D%91%E7%94%A8%EF%BC%BD%E2%80%854%EC%9D%BC%EC%B0%A8%E2%80%85%EF%BC%8D%E2%80%85%EB%B3%B4%EA%B8%89%EB%A1%9C.txt"
---

## 문제 요약
N×N 복구 시간 격자에서 좌상단에서 우하단까지 이동할 때, 밟는 칸의 복구 시간 합의 최솟값을 테스트 케이스마다 출력한다. 시작 칸은 비용 0으로 두고 다음 칸에 들어갈 때 그 칸의 비용을 더한다.

## 코드의 접근
각 칸을 정점으로 보고 상하좌우 인접 칸으로 이동하는 그래프를 만든다. 우선순위 큐에서 현재까지 비용이 가장 작은 칸을 꺼내 이웃 칸의 후보 비용을 완화하고, 더 짧은 경로가 발견되면 거리 배열과 큐를 갱신한다. 큐 항목의 비용이 최신 거리와 다르면 오래된 항목이므로 건너뛴다.

## 핵심 원리
칸에 들어가는 비용이 0 이상이므로 Dijkstra가 최단 경로를 확정할 수 있다. 거리 배열은 현재 발견한 최솟값을 보관하고, 우선순위 큐는 다음에 확장할 최소 비용 상태를 선택한다.

## 커밋에서 확인한 변경과 주의점
최근 제출에서는 방문 배열을 없애고 큐에서 꺼낸 비용과 거리 배열을 비교해 낡은 항목을 걸러낸다. 이때 이웃까지의 후보 비용은 거리 배열에 저장된 값을 다시 읽는 대신 꺼낸 항목의 위치 비용에 현재 이웃 칸 비용을 더해 계산한다. 시작 칸은 비용에 포함하지 않는 초기화다.

## 복잡도
N은 격자 한 변의 길이다. 정점 수는 N², 각 정점의 차수는 최대 4이므로 현재 구현의 우선순위 큐 연산에 따라 O(N² log N) 시간, 거리 배열에 O(N²) 공간을 사용한다.

## 한 줄 인사이트
가중치가 0 이상인 격자에서 우선순위 큐와 거리 배열로 비용이 가장 작은 경로부터 확장한다.

## 복습 질문
- 시작 칸의 복구 비용을 0으로 초기화하고 다음 칸의 비용을 더하는 이유는?
- 큐에서 꺼낸 비용과 거리 배열의 값이 다르면 왜 해당 항목을 건너뛰는가?
- 모든 이동 비용이 음수가 아니어야 Dijkstra를 적용할 수 있는 이유는?
