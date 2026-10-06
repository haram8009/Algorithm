---
schema_version: 1
platform: "LeetCode"
title: "Longest Common Prefix"
problem_url: "https://leetcode.com/problems/longest-common-prefix/"
notion_page_url: "https://app.notion.com/p/3b13d9b37e1c819ab649eaab12cc91b9"
legacy_title: "Longest Common Prefix"
problem_id: 14
difficulty: "Easy"
topics: ["Array/String"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-06"
retry_needed: true
retry_at: ""
language: ["Java"]
complexity: "O(m²·n) / O(m)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Java/Easy/0014-longest-common-prefix/0014-longest-common-prefix.java"
---
## 문제 요약
> 문자열 배열의 가장 긴 공통 접두사 반환. 없으면 빈 문자열.
## 내 접근
1. `strs[0]`의 앞에서부터 한 글자씩 붙여가며 후보 `tmp`를 만든다
2. 매 단계마다 모든 문자열에 대해 `s.indexOf(tmp) != 0`으로 접두사인지 검사
3. 하나라도 실패하면 라벨 `OUTER`로 이중 루프 탈출, 직전까지 성공한 `prefix` 반환
## 막힌 지점
## 배운 것 / 패턴
- **Runtime 7ms (하위 4.87%)** 가 신호였다. 결과는 맞지만 같은 일을 반복하고 있다는 뜻.
- 병목 두 가지:
	1. `s.indexOf(tmp)`는 **전체 부분문자열 탐색**이다. 접두사 확인만 필요하면 `startsWith`면 충분하고, 사실 그마저도 불필요하다 — 앞의 `i-1`글자는 이전 단계에서 이미 맞다고 확인했으니 **새 글자 하나만** 보면 된다.
	2. `tmp = tmp + charAt(i)`는 매번 새 String을 만든다 → 누적 O(m²) 할당.
- **세로 스캔(vertical scanning)** 이 정석. 문자열들을 세로로 세워놓고 i번째 글자끼리 비교:
```java
for (int i = 0; i < strs[0].length(); i++) {
    char c = strs[0].charAt(i);
    for (String s : strs) {
        if (i == s.length() || s.charAt(i) != c) return strs[0].substring(0, i);
    }
}
return strs[0];
```
- 전체 글자 수를 S라 하면 O(S). 문자열 배열 문제에서 **"가로로 문자열 단위"가 아니라 "세로로 같은 인덱스끼리"** 보는 시각이 자주 통한다.
- 라벨 `break OUTER`는 잘 썼다. 다만 세로 스캔 버전에선 바로 `return`이 가능해서 라벨 자체가 필요 없어진다 — **탈출이 복잡하면 구조가 잘못됐다는 신호**일 때가 많다.
## 다시 볼 때 체크할 것
- [ ] 세로 스캔으로 다시 제출해서 Runtime 비교 (7ms → 얼마?)
- [ ] `strs[0]`이 가장 짧지 않아도 정답이 나오는 이유는?
- [ ] `["ab", "a"]`처럼 한 문자열이 다른 것의 접두사일 때 내 원래 코드가 어디서 걸리나 손으로 따라가기
