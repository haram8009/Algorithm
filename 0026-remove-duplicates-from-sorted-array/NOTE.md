---
schema_version: 1
platform: "LeetCode"
title: "Remove Duplicates from Sorted Array"
problem_url: "https://leetcode.com/problems/remove-duplicates-from-sorted-array/"
notion_page_url: "https://app.notion.com/p/3b13d9b37e1c81eaa2a6f459e0e92f11"
legacy_title: "Remove Duplicates from Sorted Array"
problem_id: 26
difficulty: "Easy"
topics: ["Array/String","Two Pointers"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-04"
retry_needed: false
retry_at: ""
language: ["Python"]
complexity: "O(n) / O(1)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/58a6192fae0daa9b7907a2ea08cdad7012e5deb9/0026-remove-duplicates-from-sorted-array/0026-remove-duplicates-from-sorted-array.py"
---

## 한 줄 인사이트

비교 대상은 원본의 직전 값(i-1)이 아니라 이미 써넣은 마지막 값(k-1)이다.
