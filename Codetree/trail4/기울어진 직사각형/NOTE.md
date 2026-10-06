---
schema_version: 1
platform: "Codetree"
title: "기울어진 직사각형"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-slanted-rectangle"
notion_page_url: "https://app.notion.com/p/3d03d9b37e1c81a09e09dbeaa71eb3e5"
legacy_title: "\\[코드트리\\] 기울어진 직사각형"
problem_id: ""
difficulty: "Hard"
topics: ["Matrix"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-03"
retry_needed: true
retry_at: ""
language: ["Java"]
complexity: "O(n\\^5) / O(n\\^2)  ※ 최적해는 대각선 누적합으로 O(n\\^4)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EA%B8%B0%EC%9A%B8%EC%96%B4%EC%A7%84%20%EC%A7%81%EC%82%AC%EA%B0%81%ED%98%95/slanted-rectangle.java"
---

## 한 줄 인사이트

기울어진 도형은 격자를 회전시켜 보려 하지 말고 '한 꼭짓점 + 두 변 길이'로 매개화하면 경계 조건이 부등식 두 개로 정리된다.
