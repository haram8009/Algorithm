---
schema_version: 1
platform: "Codetree"
title: "크기가 N인 순열"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-n-permutation"
notion_page_url: "https://app.notion.com/p/3f13d9b37e1c81938ee8d3c8cf4180f0"
legacy_title: "\\[코드트리\\] 크기가 N인 순열"
problem_id: ""
difficulty: "Easy"
topics: ["Backtracking"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-10-05"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N·N!) / O(N) (출력 제외)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%ED%81%AC%EA%B8%B0%EA%B0%80%20N%EC%9D%B8%20%EC%88%9C%EC%97%B4/n-permutation.java"
---
## 문제 요약
1부터 N까지의 수를 한 번씩 사용해 만들 수 있는 모든 순열을 사전식 오름차순으로 출력한다.
## 풀이 접근
- 깊이를 현재 채울 자리로 둔다.
- 1부터 N까지 순서대로 확인해 방문하지 않은 수를 넣고 다음 자리로 재귀한다.
- N개 자리가 채워지면 현재 순열을 출력한다.
## 구현에서 확인할 점
- `visited[i]`는 현재 재귀 경로에서만 선택된 수를 나타낸다.
- 재귀가 끝난 뒤 방문 표시를 반드시 되돌려야 다른 순열에서 재사용할 수 있다.
- 입력 상한이 커지면 N!개의 결과 자체가 빠르게 증가한다.
## 제출 기록
- 2026-10-05 · Java · Accepted · 1857ms / 29MB
- 분류: Trail 4 / Backtracking / 순열 만들기
- [코드트리 문제](https://www.codetree.ai/trails/complete/curated-cards/intro-n-permutation)
- [GitHub 커밋](https://github.com/haram8009/Algorithm/commit/35c71e4833c0a433056ec6b1bceaa5ecb058c4b3)
- [저장된 Java 코드](https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%ED%81%AC%EA%B8%B0%EA%B0%80%20N%EC%9D%B8%20%EC%88%9C%EC%97%B4/n-permutation.java)
## 자기 점검
- 방문 표시를 재귀 이후 되돌리지 않으면 어떤 경우가 누락되는가?
- 오름차순 선택이 출력 순서를 보장하는 이유는?
