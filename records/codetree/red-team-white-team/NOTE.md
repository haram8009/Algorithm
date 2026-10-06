---
schema_version: 1
platform: "Codetree"
title: "레드팀 화이트팀"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-red-team-and-white-team"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c812084b5e37a97992d87"
legacy_title: "\\[코드트리\\] 레드팀 화이트팀"
problem_id: ""
difficulty: "Medium"
topics: ["BFS/DFS","Graph","UnionFind"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-10-01"
retry_needed: true
retry_at: ""
language: ["Java"]
complexity: "DFS: O(N+M) / O(N+M)  ※ Union-Find: O(M·α(N)) / O(N)"
time_minutes: ""
code_url: ""
---

## 한 줄 인사이트

이분 그래프 판별은 노드를 한 번 훑는 게 아니라 '반대 색을 이웃으로 전파'해야 하고, Union-Find로는 '적의 적은 내 편'을 againsts 배열로 기록하면 된다.
