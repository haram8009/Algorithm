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
## 문제 요약
> 정렬된 배열에서 중복 제거하고 고유 원소 개수 k 리턴. 앞 k칸에 고유 원소가 순서대로 들어있어야 함. in-place.
## 내 접근
1. `k`를 쓰기 포인터로 두고 1에서 시작 (첫 원소는 무조건 유지되니까)
2. `i`를 1부터 훑으면서 `nums[i]`가 **직전에 써넣은 값** `nums[k-1]`과 다르면 `nums[k]`에 쓰고 `k++`
3. 같으면 그냥 넘어감 → 중복이 자연스럽게 덮어써짐
## 막힌 지점
## 배운 것 / 패턴
- **읽기 포인터 / 쓰기 포인터 분리**가 in-place 배열 문제의 기본형. `i`는 원본을 훑고 `k`는 결과를 쌓는다.
- `k`는 "지금까지 확정된 결과의 길이"이자 "다음에 쓸 위치"라는 이중 의미를 갖는다.
- 비교 대상을 `nums[i-1]`(원본 기준)이 아니라 `nums[k-1]`(결과 기준)으로 잡은 게 핵심. 이번엔 둘 다 통과하지만 **결과 기준으로 비교하는 습관**이 80번(중복 2개까지 허용)에서 그대로 확장된다.
## 다시 볼 때 체크할 것
- [ ] `nums[k-1]` 대신 `nums[i-1]`을 써도 되는 이유는? (정렬 조건이 왜 필요한지)
- [ ] 빈 배열이 들어오면 현재 코드는 `k=1`을 반환한다 — 제약조건 `n >= 1` 덕에 통과할 뿐 방어 코드는 없음
- [ ] 80번을 이 코드에서 몇 글자만 바꿔 만들 수 있나?
