---
schema_version: 1
platform: "Codetree"
title: "회의실 준비 구현"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-implement-scheduling-meeting-room"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c81859e9bdc060b0f0ad5"
legacy_title: "\\[코드트리\\] 회의실 준비 구현"
problem_id: ""
difficulty: "Easy"
topics: ["Greedy"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-29"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N log N) / O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail5/%ED%9A%8C%EC%9D%98%EC%8B%A4%20%EC%A4%80%EB%B9%84%20%EA%B5%AC%ED%98%84/implement-scheduling-meeting-room.java"
---

## 한 줄 인사이트

회의 선택은 끝나는 시간이 빠른 순으로 정렬해 겹치지 않는 것을 차례로 고르는 그리디이고, 끝 시간이 같으면 시작이 빠른 것을 먼저 둬야 한다.
