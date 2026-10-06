---
schema_version: 1
platform: "LeetCode"
title: "H-Index"
problem_url: "https://leetcode.com/problems/h-index/"
notion_page_url: "https://app.notion.com/p/3b13d9b37e1c8178bd78f540571b76c4"
legacy_title: "H-Index"
problem_id: 274
difficulty: "Medium"
topics: ["Array/String"]
study_group: "타임어택"
result: "Accepted"
solved_at: "2026-08-06"
retry_needed: true
retry_at: ""
language: ["Python"]
complexity: "O(n·maxCitations) / O(1)"
time_minutes: ""
code_url: "https://github.com/haram8009/algorithm-study/blob/main/이하람/week01/timeattack/h_index.py"
---

## 한 줄 인사이트

매 라운드마다 전체 배열을 훑는 시뮬레이션이라 느리다(207ms, 하위 5%) — 정렬 후 threshold 찾기가 정석.
