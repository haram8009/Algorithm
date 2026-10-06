---
schema_version: 1
platform: "Codetree"
title: "그래프 탐색"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-graph-traversal"
notion_page_url: "https://app.notion.com/p/3d13d9b37e1c81869e74f9630cff88d2"
legacy_title: "\\[코드트리\\] 그래프 탐색"
problem_id: ""
difficulty: "Easy"
topics: ["BFS/DFS","Graph"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-04"
retry_needed: true
retry_at: ""
language: ["Python"]
complexity: "O(V·(V+E)) / O(V+E)  ※ visited를 set/bool 배열로 바꾸면 O(V+E)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EA%B7%B8%EB%9E%98%ED%94%84%20%ED%83%90%EC%83%89/graph-traversal.py"
---

## 한 줄 인사이트

visited를 리스트로 두면 `in` 검사가 O(V)라 DFS 전체가 O(V·(V+E))로 부풀어 오른다 — set이나 bool 배열로 바꾸는 한 줄로 O(V+E).
