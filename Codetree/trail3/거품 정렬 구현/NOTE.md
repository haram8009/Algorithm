---
schema_version: 1
platform: "Codetree"
title: "거품 정렬 구현"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-implement-bubble-sort"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c81a6af89eae2d382c5b8"
legacy_title: "\\[코드트리\\] 거품 정렬 구현"
problem_id: ""
difficulty: "Medium"
topics: ["Array/String"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-05"
retry_needed: false
retry_at: ""
language: ["Python"]
complexity: "O(N\\^2) / O(1)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail3/%EA%B1%B0%ED%92%88%20%EC%A0%95%EB%A0%AC%20%EA%B5%AC%ED%98%84/implement-bubble-sort.py"
---
## 문제 요약
- N개의 정수를 거품 정렬로 오름차순 정렬해 공백으로 구분해 출력
- Trail 3 / 정렬 / 거품 정렬 챌린지, 난이도 보통. 입출력 형식은 코드를 보고 정리했으므로 원문과 대조 필요
## 내 접근
- 이웃한 두 원소를 비교해 앞이 크면 교환하는 패스를 반복
- 패스를 한 번 돌 때마다 가장 큰 값이 맨 뒤에 자리를 잡으므로 `end`를 하나씩 줄임
- 한 패스에서 교환이 없으면(`bubbled`가 False) 이미 정렬된 것이므로 종료
```python
n = int(input())
arr = list(map(int, input().split()))

# Please write your code here.
bubbled=True 
end=n-1
while(bubbled):
    bubbled=False
    for i in range(end):
        if arr[i]>arr[i+1]:
            tmp=arr[i]
            arr[i]=arr[i+1]
            arr[i+1] = tmp
            bubbled=True
    end-=1 

for a in arr:
    print(a, end=" ")
```
## 막힌 지점
## 배운 것 / 패턴
- **복잡도**: 최악·평균 O(N\^2). 조기 종료 덕분에 이미 정렬된 입력은 O(N)
- **안정 정렬**: 같은 값은 `>`로만 교환하므로 순서가 유지된다. 부등호를 `>=`로 바꾸면 안정성이 깨진다
- **범위 축소**: 패스 k가 끝나면 뒤쪽 k개가 확정되므로 `end`를 줄이는 것만으로 비교 횟수가 절반 가까이 준다
- **파이썬 팁**: 교환은 `arr[i], arr[i+1] = arr[i+1], arr[i]` 한 줄로 쓸 수 있다
## 다시 볼 때 체크할 것
- [ ] 첫 패스가 끝났을 때 배열의 어떤 위치가 확정되는지 말할 수 있는가?
- [ ] 조기 종료 플래그가 없을 때와 있을 때 시간복잡도를 비교할 수 있는가?
- [ ] 거품 정렬이 안정 정렬인 이유를 설명할 수 있는가?
