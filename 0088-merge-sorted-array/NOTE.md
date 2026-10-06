---
schema_version: 1
platform: "LeetCode"
title: "Merge Sorted Array"
problem_url: "https://leetcode.com/problems/merge-sorted-array/"
notion_page_url: "https://app.notion.com/p/3b13d9b37e1c8130b032ce4a9b384dac"
legacy_title: "Merge Sorted Array"
problem_id: 88
difficulty: "Easy"
topics: ["Array/String","Two Pointers"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-05"
retry_needed: false
retry_at: ""
language: ["Python"]
complexity: "O(m+n) / O(1)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/0088-merge-sorted-array/0088-merge-sorted-array.py"
---
## 문제 요약
> 정렬된 nums1(뒤쪽 n칸은 0으로 패딩)과 nums2를 합쳐서 nums1에 in-place 정렬 상태로 만들기. 리턴값 없음.
## 내 접근
1. `k = m+n-1` — nums1의 **맨 뒤**를 쓰기 위치로 잡는다
2. 두 배열의 **끝에서부터** 비교해서 큰 값을 `nums1[k]`에 넣고 해당 포인터를 하나 당긴다
3. 루프가 끝났을 때 nums2가 남아 있으면(`if n:`) `nums1[:n] = nums2[:n]`로 앞을 채운다
## 막힌 지점
## 배운 것 / 패턴
- **빈 공간이 뒤에 있으니 뒤에서부터 채운다.** 앞에서부터 병합하면 nums1의 아직 안 읽은 원소를 덮어써서 임시 배열이 필요해진다. 여분 공간의 위치가 순회 방향을 정한다.
- 루프 종료 후 처리가 비대칭인 이유: **nums1이 남으면 이미 제자리에 있어서 할 일이 없고, nums2가 남을 때만 옮기면 된다.** 이 비대칭이 in-place 병합의 핵심.
- 27번의 sentinel 트릭과 대비된다. 그건 값 범위 제약에 기댄 우회였고, 이건 **자료구조의 형태(뒤쪽 여유 공간) 자체를 활용**한 정공법.
## 다시 볼 때 체크할 것
- [ ] `m=0, n=3`일 때 코드가 어떻게 흘러가나 손으로 따라가기
- [ ] 앞에서부터 병합하면 정확히 어느 시점에 깨지는지 예시 배열로 확인
