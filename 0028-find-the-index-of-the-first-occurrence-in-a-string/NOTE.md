---
schema_version: 1
platform: "LeetCode"
title: "Find the Index of the First Occurrence in a String"
problem_url: "https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/"
notion_page_url: "https://app.notion.com/p/3bb3d9b37e1c81e08425dc151dbff3b4"
legacy_title: "Find the Index of the First Occurrence in a String"
problem_id: 28
difficulty: "Easy"
topics: ["Array/String"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-12"
retry_needed: false
retry_at: ""
language: ["Python","Java"]
complexity: "O(n·m) / O(m)"
time_minutes: ""
code_url: "https://github.com/haram8009/algorithm-study/blob/main/이하람/week02/FindTheIndexOfTheFirstOccurrenceInAString.java"
---
## 문제 요약
haystack 안에서 needle이 처음 등장하는 인덱스를 반환한다. 없으면 -1.
## 내 접근
- **Python**: 내장 `haystack.find(needle)` 한 줄로 처리.
- **Java**: 시작 인덱스 `i`를 `0 ~ len(haystack)-len(needle)`까지 옮기며 `substring(i, i+n).equals(needle)`로 비교하는 naive 매칭.
## 막힌 지점
## 배운 것 / 패턴
**1. Java 코드의 숨은 비용 — ****`substring`****은 매번 새 String을 만든다**
Java 9 이후 `substring`은 O(m) 복사가 일어나므로, 반복문 안에서 부르면 비교 자체는 O(m)이어도 **매 루프마다 m 크기의 객체 할당**이 추가된다. `charAt`으로 바꾸면 추가 공간이 O(1)이 된다:
```java
for (int i = 0; i + n <= haystack.length(); i++) {
    int j = 0;
    while (j < n && haystack.charAt(i + j) == needle.charAt(j)) j++;
    if (j == n) return i;
}
return -1;
```
**2. 이 문제의 진짜 주제는 KMP다**
naive는 불일치가 나면 i를 1칸만 밀고 j를 0으로 되돌린다 — 이미 비교해서 알아낸 정보를 통째로 버리는 것. KMP는 needle의 "접두사이면서 동시에 접미사인 최대 길이"(LPS 배열)를 미리 계산해서, 불일치 시 haystack 포인터는 **절대 뒤로 가지 않고** needle 포인터만 되돌린다 → O(n+m).
```python
def strStr(haystack, needle):
    n, m = len(haystack), len(needle)
    lps = [0] * m
    length = 0
    for i in range(1, m):
        while length and needle[i] != needle[length]:
            length = lps[length - 1]
        if needle[i] == needle[length]:
            length += 1
        lps[i] = length

    j = 0
    for i in range(n):
        while j and haystack[i] != needle[j]:
            j = lps[j - 1]
        if haystack[i] == needle[j]:
            j += 1
            if j == m:
                return i - m + 1
    return -1
```
**3. Python ****`find`****는 편하지만 학습은 0**
CPython의 `str.find`는 내부적으로 two-way 알고리즘(Boyer-Moore와 KMP를 섞은 것)을 쓴다. 실무에선 이게 정답이지만, 면접에서 "내장 함수 쓰지 말고"라는 조건이 붙는 순간 남는 게 없다. 최소한 naive 버전은 손으로 쓸 수 있어야 한다.
**4. 연결점**
- "포인터를 뒤로 되돌리지 않는다"는 KMP의 아이디어는 3주차 **Two Pointers / Sliding Window**에서 윈도우 좌측 포인터가 단조증가하는 것과 같은 계열의 발상이다.
- 11주차 **Trie**의 Word Search II도 "문자열 탐색 중 이미 계산한 접두사 정보를 재활용"한다는 점에서 같은 뿌리.
## 다시 볼 때 체크할 것
- [ ] `substring` 없이 `charAt`만으로 naive 매칭을 다시 쓸 수 있는가?
- [ ] LPS 배열을 "aabaaab" 같은 문자열에 대해 손으로 채울 수 있는가?
- [ ] KMP에서 haystack 인덱스 i가 왜 절대 뒤로 가지 않는지 설명할 수 있는가?
