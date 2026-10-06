---
schema_version: 1
platform: "LeetCode"
title: "Reverse Words in a String"
problem_url: "https://leetcode.com/problems/reverse-words-in-a-string/"
notion_page_url: "https://app.notion.com/p/3bc3d9b37e1c81cebaeae30434068ce2"
legacy_title: "Reverse Words in a String"
problem_id: 151
difficulty: "Medium"
topics: ["Array/String"]
study_group: "타임어택"
result: "Accepted"
solved_at: "2026-08-13"
retry_needed: true
retry_at: ""
language: ["Python"]
complexity: "O(n) / O(n)  ※ follow-up은 O(1) extra space 요구"
time_minutes: ""
code_url: ""
---
## 문제 요약
문자열 `s`의 단어 순서를 뒤집어 반환한다. 선행·후행 공백은 제거하고, 단어 사이 연속 공백은 하나로 줄인다.
**Follow-up**: 가변 문자열을 쓰는 언어라면 **O(1) 추가 공간**으로 in-place 처리해볼 것.
## 내 접근
`s.split()` → `reverse()` → `" ".join()` 3줄. 내장 함수가 공백 처리를 전부 대신해준다.
## 막힌 지점
O(1)로 풀고싶었는데 도저히 방법을 모르겠어서 그렇게 못풀었다.
## 배운 것 / 패턴
**1. ****`split()`****과 ****`split(" ")`****은 완전히 다른 함수다 — 이 문제의 함정이 정확히 여기다**
```python
s = "  hello   world  "
s.split()      # ['hello', 'world']                     ← 연속/양끝 공백 전부 알아서 처리
s.split(" ")   # ['', '', 'hello', '', '', 'world', '', '']  ← 빈 문자열 폭발
```
이 문제가 Easy가 아니라 Medium인 이유의 절반이 "공백 정규화"인데, 인자 없는 `split()`이 그걸 통째로 지운다. **운 좋게 맞힌 게 아니라 맞는 선택이었지만, 그 덕에 문제의 절반을 안 보고 지나갔다.**
**2. Follow-up 정석 — 3단계 reverse 트릭**
가변 배열(C++ `string`, Java `char[]`)라면 추가 공간 O(1)로 된다:
1. **전체를 뒤집는다** → `"dlrow olleh"`
2. **각 단어를 다시 뒤집는다** → `"world hello"`
3. 앞뒤·중복 공백을 두 포인터로 밀어서 정리한다
```python
# 파이썬은 str이 불변이라 list로 바꿔야 해 진짜 O(1)은 불가능하지만, 로직은 연습 가치가 있다
def reverseWords(self, s: str) -> str:
    a = list(s)

    def rev(i, j):
        while i < j:
            a[i], a[j] = a[j], a[i]
            i += 1
            j -= 1

    # 1) 공백 정규화 (two pointers: read/write)
    w = 0
    i = 0
    n = len(a)
    while i < n:
        if a[i] != ' ':
            if w > 0:
                a[w] = ' '
                w += 1
            start = w
            while i < n and a[i] != ' ':
                a[w] = a[i]
                w += 1
                i += 1
            rev(start, w - 1)          # 2) 단어단위 뒤집기
        else:
            i += 1
    del a[w:]
    rev(0, len(a) - 1)                 # 3) 전체 뒤집기
    return ''.join(a)
```
**3. 파이썬 문자열은 불변 — 이 문제의 follow-up을 파이썬으로는 진짜로 만족할 수 없다**
`list(s)`로 바꾸는 순간 O(n)이다. 면접에서 이 follow-up이 나오면 "파이썬은 str이 immutable이라 문자 배열로 변환해야 하고, 그 시점에 O(n) 공간이 강제됩니다"라고 밝히고 로직을 보여주는 게 정답이다.
**4. 타임어택 관점 — 3줄로 끝난 게 좋은 신호만은 아니다**
실전에선 빨리 통과시키는 게 맞다. 다만 이 문제는 **언어 내장 기능이 문제를 지워버리는** 유형이라, 통과 여부와 무관하게 배운 게 없다. 2주차 0028(Find the Index of the First Occurrence)에서 `haystack.find()`로 끝낸 것과 **정확히 같은 구조**다. 두 번 연속으로 같은 지점에서 내장 함수로 우회했다는 게 이번 주차의 진짜 신호다.
→ **다시 풀 때 조건**: `split` / `reverse` / `join` 전부 금지, 포인터만으로.
**5. 연결점**
- 3단계 reverse의 공백 정리 단계가 정확히 **3주차 Two Pointers**의 read/write 포인터 패턴이다. 1주차 Remove Element / Remove Duplicates에서 쓰던 그 구조 그대로.
- "전체 뒤집기 + 부분 뒤집기"는 **Rotate Array**(1주차 타임어택)의 3번 reverse 트릭과 완전히 동일한 발상이다. 둘을 같은 카드로 묶어두면 좋다.
## 다시 볼 때 체크할 것
- [ ] `split()` 없이, 포인터만으로 다시 풀 수 있는가?
- [ ] 3단계 reverse의 순서와 각 단계가 왜 필요한지 설명할 수 있는가?
- [ ] Rotate Array의 reverse 트릭과 어떻게 같은지 말할 수 있는가?
