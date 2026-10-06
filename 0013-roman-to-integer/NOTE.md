---
schema_version: 1
platform: "LeetCode"
title: "Roman to Integer"
problem_url: "https://leetcode.com/problems/roman-to-integer/"
notion_page_url: "https://app.notion.com/p/3b13d9b37e1c8100b237f4e8434ceea0"
legacy_title: "Roman to Integer"
problem_id: 13
difficulty: "Easy"
topics: ["Array/String"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-06"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(n) / O(1)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/0013-roman-to-integer/0013-roman-to-integer.java"
---
## 문제 요약
> 로마 숫자 문자열을 정수로 변환. IV=4, IX=9처럼 작은 값이 큰 값 **앞**에 오면 빼는 규칙.
## 내 접근
1. 문자 → 값 매핑을 HashMap으로 준비
2. **같은 문자가 연속되면 한 덩어리로 묶어** `this_val`에 누적
3. 다른 문자를 만나면, 다음 값이 지금 덩어리보다 크면 `answer -= this_val`, 아니면 `answer += this_val`
4. 마지막 덩어리를 더하고 반환
## 막힌 지점
## 배운 것 / 패턴
- 로마 숫자의 뺄셈 규칙은 **국소적**이다 — 바로 옆 글자 하나만 보면 결정된다. 그래서 덩어리로 묶지 않고 인접 두 글자만 비교해도 된다:
```java
int answer = 0;
for (int i = 0; i < arr.length; i++) {
    int cur = map.get(arr[i]);
    if (i + 1 < arr.length && cur < map.get(arr[i + 1])) answer -= cur;
    else answer += cur;
}
return answer;
```
- 덩어리로 묶은 내 풀이도 맞다. 유효한 로마 숫자에서 **빼기 대상이 되는 덩어리는 항상 길이 1**(IIX 같은 표기는 없음)이라 `tmp > this_val` 비교가 깨지지 않는 것.
- Java 팁: `new HashMap<>() {{ put(...) }}` 이중 중괄호 초기화는 **익명 클래스를 만드는 안티패턴**. 호출마다 클래스 로딩 비용이 붙고 바깥 인스턴스 참조를 붙잡는다. `static final` 맵이나 `switch`로 빼는 게 낫다.
## 다시 볼 때 체크할 것
- [ ] 인접 두 글자 비교 버전으로 다시 짜서 줄 수 비교
- [ ] 맵을 `static final`로 빼거나 `switch`로 바꿔서 Runtime 변화 확인
- [ ] `arr[0]`을 미리 읽는 코드는 빈 문자열이 오면 터진다 — 제약조건이 이를 막아주나?
