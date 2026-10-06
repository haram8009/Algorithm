---
schema_version: 1
platform: "Codetree"
title: "집합의 원소"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-elements-of-a-set"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c8166896bf5d0d4feb552"
legacy_title: "집합의 원소"
problem_id: ""
difficulty: "Easy"
topics: ["Graph"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-10-01"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O((N + M) log N) / O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail6/%EC%A7%91%ED%95%A9%EC%9D%98%20%EC%9B%90%EC%86%8C/elements-of-a-set.java"
---

## 한 줄 인사이트

연결 여부만 묻는 문제는 Union-Find가 정답이고, 경로 압축 한 줄(uf\[x\] = find(uf\[x\]))이 성능의 핵심이다.
