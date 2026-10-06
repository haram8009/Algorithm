---
schema_version: 1
platform: "Codetree"
title: "지질 연구"
problem_url: "https://www.codetree.ai/ko/trails/complete/curated-cards/challenge-geological-research/description"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c81c8bf2ce5b8231a5377"
legacy_title: "지질 연구"
problem_id: ""
difficulty: "Easy"
topics: ["Graph","BFS/DFS","DP"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-10-01"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N + M) / O(N + M)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail6/%EC%A7%80%EC%A7%88%20%EC%97%B0%EA%B5%AC/geological-research.java"
---

## 한 줄 인사이트

DAG에서 "최댓값이 2번 이상이면 +1, 아니면 그대로" 규칙으로 값이 전파되는 구조(Strahler 수)는 메모이제이션 DFS 한 번으로 O(N+M)에 끝난다.
