---
schema_version: 1
platform: "LeetCode"
title: "Length of Last Word"
problem_url: "https://leetcode.com/problems/length-of-last-word/"
notion_page_url: "https://app.notion.com/p/3b13d9b37e1c8153af1fefab20296282"
legacy_title: "Length of Last Word"
problem_id: 58
difficulty: "Easy"
topics: ["Array/String"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-06"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(n) / O(n)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Java/Easy/0058-length-of-last-word/0058-length-of-last-word.java"
---
## 문제 요약
> 공백으로 구분된 문자열에서 마지막 단어의 길이 반환. 뒤에 공백이 붙어 있을 수 있음.
## 내 접근
1. `trim()`으로 앞뒤 공백 제거
2. `toCharArray()` 후 **끝에서부터** 순회하며 공백을 만날 때까지 카운트
## 막힌 지점
## 배운 것 / 패턴
- 마지막 단어만 필요하므로 **뒤에서부터 훑는 게 자연스럽다.** 앞에서 훑으면 단어를 셀 때마다 카운터를 리셋해야 해서 오히려 복잡해진다.
- `trim()`과 `toCharArray()`가 각각 문자열을 **복사**해서 공간이 O(n)이 된다. `charAt`으로 인덱스만 움직이면 O(1):
```java
int i = s.length() - 1, cnt = 0;
while (i >= 0 && s.charAt(i) == ' ') i--;   // 뒤쪽 공백 건너뛰기
while (i >= 0 && s.charAt(i) != ' ') { cnt++; i--; }
return cnt;
```
- 패턴: **"전처리로 정리한 뒤 훑기" vs "훑으면서 처리하기".** 전자가 읽기 쉽고 후자가 공간 효율적이다. 코테에선 보통 전자로 충분하지만 어느 쪽인지는 알고 쓰는 게 좋다.
## 다시 볼 때 체크할 것
- [ ] `charAt` 버전으로 다시 짜서 Runtime·Memory 비교
- [ ] `"   "`(공백만)이 들어오면 두 버전 모두 0을 반환하나?
- [ ] `split(" ")`으로 푸는 방법은 왜 비효율적인가?
