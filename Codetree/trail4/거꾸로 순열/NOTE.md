---
schema_version: 1
platform: "Codetree"
title: "거꾸로 순열"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-backward-permutation"
notion_page_url: "https://app.notion.com/p/3f13d9b37e1c81719a27cc973efe6d9c"
legacy_title: "\\[코드트리\\] 거꾸로 순열"
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
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EA%B1%B0%EA%BE%B8%EB%A1%9C%20%EC%88%9C%EC%97%B4/backward-permutation.java"
---
## 문제 요약
1부터 N까지의 수를 한 번씩 사용해 만들 수 있는 모든 순열을 역순 사전식 순서로 출력한다.
## 풀이 접근
- 깊이를 순열의 현재 자리로 보고, 방문하지 않은 수를 큰 값부터 시도한다.
- 선택한 수를 결과 배열에 기록하고 방문 표시한 뒤 다음 자리로 재귀한다.
- N개 자리가 모두 채워지면 한 줄 출력한다.
## 구현에서 확인할 점
- 역순 출력을 위해 반복문을 `N−1`부터 0까지 돈다.
- 재귀 복귀 시 방문 표시를 해제해야 다음 경우에서 그 수를 쓸 수 있다.
- 출력이 많으므로 실제 제출에서는 문자열 버퍼를 쓰면 입출력 부담을 줄일 수 있다.
## 제출 기록
- 2026-10-05 · Java · Accepted · 1859ms / 29MB
- 분류: Trail 4 / Backtracking / 순열 만들기
- [코드트리 문제](https://www.codetree.ai/trails/complete/curated-cards/challenge-backward-permutation)
- [GitHub 커밋](https://github.com/haram8009/Algorithm/commit/673531619c9301613bd00293056f8e8ab6bd77c0)
- [저장된 Java 코드](https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EA%B1%B0%EA%BE%B8%EB%A1%9C%20%EC%88%9C%EC%97%B4/backward-permutation.java)
## 자기 점검
- 방문 배열이 없으면 같은 수열이 여러 번 만들어지는 이유는?
- 반복 방향을 오름차순에서 내림차순으로 바꾸면 출력 순서가 어떻게 달라지는가?
