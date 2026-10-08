---
schema_version: 1
platform: "SWEA"
title: "하나로"
problem_url: "https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD"
notion_page_url: ""
legacy_title: ""
problem_id: "1251"
difficulty: "D4"
topics: ["MST","Kruskal","Union-Find"]
study_group: ""
result: "Accepted"
solved_at: "2026-10-07"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N² log N) / O(N²)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/SWEA/D4/1251.%E2%80%85%EF%BC%BBS%EF%BC%8FW%E2%80%85%EB%AC%B8%EC%A0%9C%ED%95%B4%EA%B2%B0%E2%80%85%EC%9D%91%EC%9A%A9%EF%BC%BD%E2%80%854%EC%9D%BC%EC%B0%A8%E2%80%85%EF%BC%8D%E2%80%85%ED%95%98%EB%82%98%EB%A1%9C/%EF%BC%BBS%EF%BC%8FW%E2%80%85%EB%AC%B8%EC%A0%9C%ED%95%B4%EA%B2%B0%E2%80%85%EC%9D%91%EC%9A%A9%EF%BC%BD%E2%80%854%EC%9D%BC%EC%B0%A8%E2%80%85%EF%BC%8D%E2%80%85%ED%95%98%EB%82%98%EB%A1%9C.java"
---

## 문제 요약
각 섬의 좌표와 환경 부담 세율 E가 주어질 때, 모든 섬을 연결하는 해저 터널을 건설하고 환경 부담금의 합을 구한다. 두 섬 사이 비용은 두 좌표 간 거리의 제곱에 E를 곱한 값이며, 최종 합은 반올림한다.

## 코드의 접근
모든 서로 다른 섬 쌍에 대해 좌표 차의 제곱합을 가중치로 하는 간선을 생성하고 오름차순 정렬한다. Union-Find로 서로 다른 집합을 잇는 간선만 선택해 MST의 거리 제곱 합을 누적한 뒤, E를 곱해 반올림 출력한다.

## 핵심 원리
환경 부담금은 각 터널의 거리 제곱에 동일한 세율 E를 곱한 값이다. E가 모든 간선에 공통이고 음수가 아니므로 거리 제곱 합이 최소인 MST를 먼저 구한 뒤 E를 곱해도 최적 선택이 같다. Union-Find는 사이클을 만들지 않으면서 컴포넌트를 합친다.

## 커밋에서 확인한 변경과 주의점
기간 내 첫 제출에서 README와 Java 풀이 파일이 함께 추가됐다. 구현은 각 쌍을 양방향으로 생성하므로 같은 섬 쌍의 간선이 중복되지만, 가중치가 동일하고 Union-Find가 사이클 간선을 거르므로 MST 정답에는 영향을 주지 않는다. 좌표 차의 제곱과 합을 long으로 계산한다.

## 복잡도
N은 섬의 수다. 간선은 최대 N(N−1)개를 생성하고 정렬하므로 시간 복잡도는 O(N² log N)이며, 간선 목록에 O(N²), 좌표와 부모 배열에 O(N) 공간이 든다.

## 한 줄 인사이트
모든 섬 쌍의 거리 제곱을 간선 비용으로 두고 Union-Find 기반 Kruskal로 최소 부담 연결을 만든다.

## 복습 질문
- 거리 자체가 아니라 거리 제곱을 가중치로 사용해도 MST 선택이 맞는 이유는?
- 모든 간선에 동일한 E를 곱한 뒤가 아니라 먼저 MST를 구할 수 있는 이유는?
- 간선을 양방향으로 중복 생성해도 결과가 바뀌지 않는 이유는?
