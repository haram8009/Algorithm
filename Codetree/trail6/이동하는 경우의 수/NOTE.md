---
schema_version: 1
platform: "Codetree"
title: "이동하는 경우의 수"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-number-of-moving-cases"
notion_page_url: "https://app.notion.com/p/3f13d9b37e1c811d9112d24ece41d927"
legacy_title: "\\[코드트리\\] 이동하는 경우의 수"
problem_id: ""
difficulty: "Hard"
topics: ["Graph","DP"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-10-05"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N + M) / O(N + M)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail6/%EC%9D%B4%EB%8F%99%ED%95%98%EB%8A%94%20%EA%B2%BD%EC%9A%B0%EC%9D%98%20%EC%88%98/number-of-moving-cases.java"
---

## 한 줄 인사이트

위상 순서에서 1번부터의 최장 시간을 구한 다음, 최장 경로를 만드는 역방향 간선만 따라가면 임계 경로에 포함된 간선 수를 중복 없이 셀 수 있다.
