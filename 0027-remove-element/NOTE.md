---
schema_version: 1
platform: "LeetCode"
title: "Remove Element"
problem_url: "https://leetcode.com/problems/remove-element/"
notion_page_url: "https://app.notion.com/p/3b13d9b37e1c810c937cfc7fea976da2"
legacy_title: "Remove Element"
problem_id: 27
difficulty: "Easy"
topics: ["Array/String","Two Pointers"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-03"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(n log n) / O(1)"
time_minutes: 20
code_url: "https://github.com/haram8009/Algorithm/blob/main/0027-remove-element/0027-remove-element.java"
---
## 문제 요약
> array에서 특정 값을 제외한 원소만 앞으로 빼서 정렬하기 + 제외한 값 개수 리턴
## 내 접근
1. 일단 아닌 값을 최대값보다 크게 수정하고 
2. sort 해서 원소 앞으로 모으기
## 막힌 지점
처음에 영어 끝까지 안읽고 정렬해야하는거 모르고 그냥 replace만 했더니 출력이 좀 이상했다..
## 배운 것 / 패턴
- 필요한 원소만 앞에 모아놓을거라 굳이 값 수정 → sort 할 필요 없이 `k=0; k++` 하면서 차근차근 앞에 쌓아놓으면 되는거였다
- 문제유형이 너무 리트코드스러워서 이걸 어떻게 코테에서 적용하게될지는 모르겠다..?
```java
int k = 0;
for (int i = 0; i < nums.length; i++) {
    if (nums[i] != val) {
        nums[k] = nums[i];
        k++;
    }
}
return k;
```
- 이 패턴은 26번과 **구조가 같다**. `i`는 원본을 훑고 `k`는 결과를 쌓는다 — 조건만 다를 뿐.
- 코테 적용처: 배열을 새로 만들 메모리가 없거나, 필터링 후 인덱스가 그대로 필요한 경우. 슬라이딩 윈도우·투 포인터의 기본 구성요소라 3주차에 바로 이어짐.
## 다시 볼 때 체크할 것
- [ ] 쓰기 포인터 버전으로 재제출해서 Runtime 비교
- [ ] `nums[i] <= 100` 제약이 없었다면 101 트릭은 어떻게 깨지나?
- [ ] 26번과 27번이 같은 문제라는 걸 한 문장으로 설명하기
<empty-block/>
