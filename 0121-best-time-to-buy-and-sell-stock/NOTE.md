---
schema_version: 1
platform: "LeetCode"
title: "Best Time to Buy and Sell Stock"
problem_url: "https://leetcode.com/problems/best-time-to-buy-and-sell-stock/"
notion_page_url: "https://app.notion.com/p/3b13d9b37e1c81979024f7e18464ac75"
legacy_title: "Best Time to Buy and Sell Stock"
problem_id: 121
difficulty: "Easy"
topics: ["Array/String","Greedy"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-05"
retry_needed: false
retry_at: ""
language: ["Python"]
complexity: "O(n) / O(1)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/0121-best-time-to-buy-and-sell-stock/0121-best-time-to-buy-and-sell-stock.py"
---
## 문제 요약
> 하루에 한 번 사고 한 번 파는 단일 거래로 얻을 수 있는 최대 이익. 이익이 없으면 0.
## 내 접근
1. 배열을 **뒤에서부터** 순회
2. `thisMax` = 현재 위치 오른쪽의 최댓값(= 미래 최고 매도가)을 계속 갱신
3. 현재가가 `thisMax`보다 작으면 `thisMax - curr`이 그 시점에 살 때의 이익 → 전체 최댓값 갱신
## 막힌 지점
## 배운 것 / 패턴
- 본질은 **"각 시점에서, 지금까지 본 것 중 극값 하나만 기억하면 된다"**. 전체를 다시 훑을 필요가 없다.
- 정방향 풀이(왼쪽 최솟값 유지)와 역방향 풀이(오른쪽 최댓값 유지)는 **완전히 대칭**. 훑는 방향만 다르고 아이디어는 같다.
- 이 사고방식이 **13주차 Kadane's Algorithm**으로 그대로 이어진다 — "지금까지의 최적값 하나를 들고 한 번만 훑는다."
## 다시 볼 때 체크할 것
- [ ] `thisMaxP`와 `maxP`는 항상 같은 값을 갖는다 — 변수 하나로 줄여보기
- [ ] 정방향(왼쪽 최솟값) 버전으로도 작성해서 두 코드가 대칭임을 확인
- [ ] 여러 번 거래 가능하면(122번) 어떻게 달라지나?
