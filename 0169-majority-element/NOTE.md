---
schema_version: 1
platform: "LeetCode"
title: "Majority Element"
problem_url: "https://leetcode.com/problems/majority-element/"
notion_page_url: "https://app.notion.com/p/3b13d9b37e1c814ab592d026ccbc0588"
legacy_title: "Majority Element"
problem_id: 169
difficulty: "Easy"
topics: ["Array/String"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-06"
retry_needed: true
retry_at: ""
language: ["Java"]
complexity: "O(n) / O(n)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/0169-majority-element/0169-majority-element.java"
---
## 문제 요약
> 크기 n 배열에서 ⌊n/2⌋번보다 많이 등장하는 원소를 반환. 그런 원소는 항상 존재한다고 가정.
## 내 접근
1. HashMap으로 등장 횟수 카운트
2. 세는 도중 `count > n/2`가 되는 순간 **즉시 반환** (끝까지 셀 필요 없음)
## 막힌 지점
## 배운 것 / 패턴
- **조기 반환**이 좋았다. 과반수 원소는 어차피 절반 넘게 나오므로 전체를 다 세기 전에 반드시 걸린다.
- 다만 맵 때문에 **공간이 O(n)**. 이 문제의 최적해는 공간 O(1)인 **Boyer-Moore 투표 알고리즘**:
```java
int candidate = nums[0], count = 0;
for (int num : nums) {
    if (count == 0) candidate = num;
    count += (num == candidate) ? 1 : -1;
}
return candidate;
```
- 원리: 과반수 원소 하나와 다른 원소 하나를 **짝지어 상쇄**시키면, 과반수이기 때문에 반드시 마지막에 살아남는다. 카운터 하나로 "지금까지의 유력 후보"만 들고 간다.
- 121번(왼쪽 최솟값 하나만 기억)과 같은 계열의 사고 — **전체를 저장하지 말고 상태 하나만 굴린다.** 13주차 Kadane's로 이어짐.
## 다시 볼 때 체크할 것
- [ ] Boyer-Moore로 다시 제출해서 메모리 사용량 비교
- [ ] "과반수가 아니면 이 알고리즘이 왜 틀리나?" 반례 만들어보기 (검증 단계가 왜 필요한지)
- [ ] `return -1`은 실제로 도달 가능한가? (문제 조건상)
