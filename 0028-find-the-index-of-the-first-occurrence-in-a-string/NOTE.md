---
schema_version: 1
platform: "LeetCode"
title: "Find the Index of the First Occurrence in a String"
problem_url: "https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/"
notion_page_url: "https://app.notion.com/p/3bb3d9b37e1c81e08425dc151dbff3b4"
legacy_title: "Find the Index of the First Occurrence in a String"
problem_id: 28
difficulty: "Easy"
topics: ["Array/String"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-12"
retry_needed: false
retry_at: ""
language: ["Python","Java"]
complexity: "O(n·m) / O(m)"
time_minutes: ""
code_url: "https://github.com/haram8009/algorithm-study/blob/main/이하람/week02/FindTheIndexOfTheFirstOccurrenceInAString.java"
---

## 한 줄 인사이트

naive 매칭은 O(n·m)이고, 접두사=접미사 정보를 재활용하면 KMP로 O(n+m)까지 줄어든다
