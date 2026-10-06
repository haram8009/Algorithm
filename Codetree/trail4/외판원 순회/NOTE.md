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

## 한 줄 인사이트

출발 도시를 고정하면 나머지 N−1개 도시의 순열만 보면 되고, 비용 0인 간선은 제외한 뒤 현재 비용이 최솟값을 넘으면 탐색을 끊는다.
