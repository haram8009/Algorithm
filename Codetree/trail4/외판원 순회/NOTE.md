---
schema_version: 1
platform: "Codetree"
title: "외판원 순회"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-traveling-salesman-problem"
notion_page_url: "https://app.notion.com/p/3f13d9b37e1c81b2b138e70cd4cd4a3b"
legacy_title: "\\[코드트리\\] 외판원 순회"
problem_id: ""
difficulty: "Medium"
topics: ["Backtracking"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-10-06"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O((N−1)!·N) / O(N² + N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EC%99%B8%ED%8C%90%EC%9B%90%20%EC%88%9C%ED%9A%8C/traveling-salesman-problem.java"
---
## 문제 요약
1번 도시에서 출발해 모든 도시를 정확히 한 번씩 방문하고 다시 1번으로 돌아오는 순회 경로 중 총 비용의 최솟값을 구한다. 비용 0은 이동 불가를 뜻한다.
## 풀이 접근
- 순회는 시작점을 어디로 잡아도 같은 사이클이므로 1번 도시(인덱스 0)를 고정하고 나머지 도시 순열을 만든다.
- 완성된 순서의 연속 이동 비용에 마지막 도시에서 1번으로 돌아오는 비용까지 더한다.
- 비용이 0인 간선이 있으면 해당 순열은 불가능하다.
- 누적 비용이 현재 최솟값보다 커지면 양의 비용을 더해도 답을 개선할 수 없으므로 가지치기한다.
## 구현에서 확인할 점
- 순열 생성 후 반드시 swap을 원상 복구한다.
- 마지막 복귀 간선도 검사하고 합산한다.
- 불가능 경로는 최솟값 갱신에서 제외한다.
## 제출 기록
- 2026-10-06 · Java · Accepted · 225ms / 12MB
- 분류: Trail 4 / Backtracking / 순열 만들기
- [코드트리 문제](https://www.codetree.ai/trails/complete/curated-cards/challenge-traveling-salesman-problem)
- [GitHub 커밋](https://github.com/haram8009/Algorithm/commit/3527bf8320251f23ae806c921869bbe0fe03a3fa)
- [저장된 Java 코드](https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EC%99%B8%ED%8C%90%EC%9B%90%20%EC%88%9C%ED%9A%8C/traveling-salesman-problem.java)
## 자기 점검
- 시작 도시를 고정해도 모든 순회 경우를 빠짐없이 보는 이유는?
- 비용 0 간선을 별도로 걸러야 하는 이유는?
- 가지치기에 현재 최솟값과 비교하는 것이 안전한 조건은?
