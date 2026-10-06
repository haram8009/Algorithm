---
schema_version: 1
platform: "Codetree"
title: "K개 중에 1개를 N번 뽑기"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-n-permutations-of-k-with-repetition"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c81e08d2cc531934ade01"
legacy_title: "\\[코드트리\\] K개 중에 1개를 N번 뽑기"
problem_id: ""
difficulty: "Easy"
topics: ["Backtracking"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-09"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N * K\\^N) / O(N * K\\^N)  ※ 출력 버퍼 포함, 재귀 스택만 O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/K%EA%B0%9C%20%EC%A4%91%EC%97%90%201%EA%B0%9C%EB%A5%BC%20N%EB%B2%88%20%EB%BD%91%EA%B8%B0/n-permutations-of-k-with-repetition.java"
---

## 한 줄 인사이트

자리마다 1부터 K까지 오름차순으로 채우는 재귀면 중복 순열이 사전순으로 나온다.
