---
schema_version: 1
platform: "Codetree"
title: "강력한 폭발"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-strong-explosion"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c8168bfdef641f605472c"
legacy_title: "\\[코드트리\\] 강력한 폭발"
problem_id: ""
difficulty: "Medium"
topics: ["Backtracking","Matrix"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-10"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(5 \\* 3\\^M) / O(N\\^2)  ※ M은 폭탄 수, 가지치기로 실제로는 훨씬 적음"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EA%B0%95%EB%A0%A5%ED%95%9C%20%ED%8F%AD%EB%B0%9C/strong-explosion.java"
---

## 한 줄 인사이트

겹침은 칸별 폭탄 수를 카운트하고, 설치와 해제를 대칭으로 구현하면 백트래킹에서 매번 초기화할 필요가 없다.
