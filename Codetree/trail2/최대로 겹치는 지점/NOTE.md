---
schema_version: 1
platform: "Codetree"
title: "최대로 겹치는 지점"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-maximum-overlapped-points"
notion_page_url: "https://app.notion.com/p/3d03d9b37e1c816890eafe28f79d3cc0"
legacy_title: "\\[코드트리\\] 최대로 겹치는 지점"
problem_id: ""
difficulty: "Easy"
topics: ["Array/String","Intervals"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-03"
retry_needed: true
retry_at: ""
language: ["Python"]
complexity: "O(n·C) / O(C), C=좌표 범위 101  ※ 차이 배열이면 O(n+C)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail2/%EC%B5%9C%EB%8C%80%EB%A1%9C%20%EA%B2%B9%EC%B9%98%EB%8A%94%20%EC%A7%80%EC%A0%90/maximum-overlapped-points.py"
---

## 한 줄 인사이트

'구간'과 뼈대가 같은데 순회 규약만 정반대다 — 점을 세니까 닫힌 구간 \[l, r\]을 range(l, r+1)로 끝까지 돌아야 한다.
