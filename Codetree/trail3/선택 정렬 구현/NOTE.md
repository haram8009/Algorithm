---
schema_version: 1
platform: "Codetree"
title: "선택 정렬 구현"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-implement-selection-sort"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c817ca858c792cae9279f"
legacy_title: "\\[코드트리\\] 선택 정렬 구현"
problem_id: ""
difficulty: "Medium"
topics: ["Array/String"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-06"
retry_needed: false
retry_at: ""
language: ["Python"]
complexity: "O(N\\^2) / O(1)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail3/%EC%84%A0%ED%83%9D%20%EC%A0%95%EB%A0%AC%20%EA%B5%AC%ED%98%84/implement-selection-sort.py"
---
## 문제 요약
- N개의 정수를 선택 정렬로 오름차순 정렬해 공백으로 구분해 출력
- Trail 3 / 정렬 / 선택 정렬 챌린지, 난이도 보통. 입출력 형식은 코드를 보고 정리했으므로 원문과 대조 필요
## 내 접근
- i번째 자리에 올 값을 정하기 위해 `i`부터 끝까지 최솟값의 위치 `min`을 찾고 i번째와 교환
- 이 과정을 i = 0부터 N-1까지 반복
```python
n = int(input())
arr = list(map(int, input().split()))

# Please write your code here.
for i in range(n):
    min = i
    for j in range(i+1,n):
        if arr[j] < arr[min]:
            min = j
    tmp = arr[i]
    arr[i] = arr[min]
    arr[min] = tmp

for a in arr:
    print(a, end=" ")
```
## 막힌 지점
## 배운 것 / 패턴
- **복잡도**: 비교는 입력과 상관없이 항상 N(N-1)/2번이라 O(N\^2). 거품 정렬과 달리 이미 정렬돼 있어도 빨라지지 않는다. 대신 교환은 최대 N번뿐이다
- **불안정**: 멀리 있는 최솟값과 교환하기 때문에 같은 값의 순서가 뒤바뀔 수 있다 (예: 5a, 5b, 2 를 정렬하면 5b가 5a보다 앞에 온다)
- **이름 주의**: 변수 `min`이 파이썬 내장 함수 `min`을 가린다. 같은 스코프에서 `min()`을 쓰면 오류가 나므로 `min_idx`처럼 바꾸는 편이 안전
- **교환**: `arr[i], arr[min] = arr[min], arr[i]`로 한 줄에 쓸 수 있다
## 다시 볼 때 체크할 것
- [ ] 선택 정렬이 불안정한 반례를 직접 만들 수 있는가?
- [ ] 거품 정렬과 선택 정렬의 교환 횟수 차이를 설명할 수 있는가?
- [ ] 변수 이름 `min`이 왜 문제가 될 수 있는지 말할 수 있는가?
