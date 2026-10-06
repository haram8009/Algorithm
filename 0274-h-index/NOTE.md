---
schema_version: 1
platform: "LeetCode"
title: "H-Index"
problem_url: "https://leetcode.com/problems/h-index/"
notion_page_url: "https://app.notion.com/p/3b13d9b37e1c8178bd78f540571b76c4"
legacy_title: "H-Index"
problem_id: 274
difficulty: "Medium"
topics: ["Array/String"]
study_group: "타임어택"
result: "Accepted"
solved_at: "2026-08-06"
retry_needed: true
retry_at: ""
language: ["Python"]
complexity: "O(n·maxCitations) / O(1)"
time_minutes: ""
code_url: "https://github.com/haram8009/algorithm-study/blob/main/이하람/week01/timeattack/h_index.py"
---
## 문제 요약
> 논문별 인용 수 배열이 주어질 때, h-index(최소 h편이 각각 h회 이상 인용된 최대 h) 반환.
## 내 접근 (오늘 실전 타임어택)
1. `h`를 1부터 올려가며, 각 라운드마다 아직 살아있는(`> -1`) 값들을 전부 1씩 깎는다
2. 값이 0 아래로 내려가면 `-1`로 고정해서 "이 논문은 더 이상 h를 못 채운다"는 표시로 씀
3. 매 라운드 시작 시, `-1`이 아닌(=아직 h를 채우는) 논문 수가 현재 h보다 적으면 멈추고 `h-1` 반환
## 막힌 지점
- (오늘 타임어택 중 막혔던 지점을 여기에 적어두기)
## 배운 것 / 패턴
- 정답은 맞지만 **매 라운드마다 배열 전체를 한 번씩 훑는 시뮬레이션**이라, 최악의 경우 인용수 최댓값만큼 라운드가 돌아 **O(n · maxCitations)**. 실제로 Runtime이 207ms, 하위 5%로 나온 이유.
- 이 문제의 정석은 **정렬 후 threshold 찾기**:
```python
citations.sort(reverse=True)
h = 0
for i, c in enumerate(citations):
    if c >= i + 1:
        h = i + 1
    else:
        break
return h
```
정렬해두면 "적어도 h편이 h회 이상 인용"이라는 조건이 **인덱스 하나만 비교**하면 되는 문제로 바뀐다. O(n log n).
- 더 빠르게: 인용수는 `n`(논문 수)을 넘는 순간부터는 h에 영향이 없으므로 **`min(citations[i], n)`****으로 캡을 씌워 카운팅 정렬**하면 O(n)까지 가능. "값의 범위가 n으로 제한된다"는 사실을 알아채는 게 핵심.
- 오늘 접근(시뮬레이션)은 로직은 맞지만 **매 단계 전체를 다시 훑는 대신, 한 번의 정렬/카운팅으로 끝낼 수 있는 문제였다** — "값을 깎아나가며 반복 확인" 패턴은 값의 범위가 크면 위험 신호.
## 다시 볼 때 체크할 것
- [ ] 정렬 버전으로 다시 제출해서 Runtime 비교 (207ms → 얼마?)
- [ ] 카운팅 정렬 버전(O(n))까지 작성해보기
- [ ] `[0,0,0,0]`처럼 전부 0인 경우 내 원래 코드와 정렬 버전이 둘 다 0을 반환하는지 확인
