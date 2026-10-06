---
schema_version: 1
platform: "Codetree"
title: "최대로 겹치는 구간"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-maximum-overlapped-segments"
notion_page_url: "https://app.notion.com/p/3d03d9b37e1c81358b3cd3023731e65d"
legacy_title: "\\[코드트리\\] 최대로 겹치는 구간"
problem_id: ""
difficulty: "Medium"
topics: ["Array/String","Intervals"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-03"
retry_needed: true
retry_at: ""
language: ["Python"]
complexity: "O(n·C) / O(C), C=좌표 범위 200  ※ 차이 배열이면 O(n+C)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail2/%EC%B5%9C%EB%8C%80%EB%A1%9C%20%EA%B2%B9%EC%B9%98%EB%8A%94%20%EA%B5%AC%EA%B0%84/maximum-overlapped-segments.py"
---

## 한 줄 인사이트

겹침은 '점'이 아니라 '칸'으로 센다 — 그래서 닫힌 구간 \[l, r\]도 반열린 \[l, r)로 순회해야 한다.
