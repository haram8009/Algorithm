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

## 한 줄 인사이트

각 자리에서 미사용 수를 오름차순으로 고르고 방문 상태를 복구하면 1부터 N까지의 순열을 사전식으로 열거할 수 있다.
