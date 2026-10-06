---
schema_version: 1
platform: "Codetree"
title: "최대로 겹치는 구간"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-maximum-overlapped-segments"
notion_page_url: "https://app.notion.com/p/3d03d9b37e1c81358b3cd3023731e65d"
legacy_title: "\\[코드트리\\] 최대로 겹치는 구간"
problem_id: ""
difficulty: "Medium"
topics: ["Array/String","Intervals"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-03"
retry_needed: true
retry_at: ""
language: ["Python"]
complexity: "O(n·C) / O(C), C=좌표 범위 200  ※ 차이 배열이면 O(n+C)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail2/%EC%B5%9C%EB%8C%80%EB%A1%9C%20%EA%B2%B9%EC%B9%98%EB%8A%94%20%EA%B5%AC%EA%B0%84/maximum-overlapped-segments.py"
---
## 문제 요약
수직선 위의 선분 n개가 `(l, r)`로 주어진다(좌표는 음수 포함). 가장 많은 선분이 겹치는 지점의 겹침 수를 출력한다.
## 내 접근
좌표가 음수까지 내려가므로 배열 인덱스로 쓰려면 오프셋이 필요하다. `+100`을 더해 좌표를 0 이상으로 옮기고, 길이 200 배열의 각 칸을 단위 구간 하나로 본다. 선분마다 `range(l+100, r+100)`을 돌며 +1, 마지막에 `max`.
```python
lst = [0]*200
for l, r in segments:
    for i in range(l+100, r+100):
        lst[i] += 1
print(max(lst))
```
## 막힌 지점
## 배운 것 / 패턴
**이 문제의 진짜 함정은 복잡도가 아니라 "무엇을 세는가"다.** 닫힌 구간 \[l, r\]인데도 `range(l+100, r+100)`으로 **끝을 열어서** 순회한 게 정답이었다. 이유:
- 배열의 인덱스 `i`가 뜻하는 것은 *점* i가 아니라 *칸* \[i, i+1)이다.
- 선분 \[1, 3\]은 칸 \[1,2), \[2,3) 두 칸을 덮는다. 칸 \[3,4)는 덮지 않는다.
- 그래서 선분 \[1, 3\]과 \[3, 5\]는 **점 3을 공유하지만 겹치는 구간의 길이는 0**이다. 만약 닫힌 구간으로 `range(l+100, r+100+1)`을 돌았다면 이 둘을 겹친다고 세어 답이 1 커진다.
이 "점 vs 칸" 구분이 \[코드트리\] 블럭쌓는 명령2와의 결정적 차이다. 블럭 문제는 칸 자체가 물리적 객체(블럭 자리)라 \[a, b\]가 진짜 닫힌 구간이고, 이 문제는 연속 좌표축 위의 선분이라 반열린 구간이 맞다. **골격이 같은 두 문제에서 인덱싱 규약만 다른데, 이걸 관성으로 복붙하면 off-by-one이 난다.**
복잡도는 차이 배열로 개선된다:
```python
OFF = 100
diff = [0]*(2*OFF + 2)
for l, r in segments:
    diff[l+OFF] += 1
    diff[r+OFF] -= 1     # 반열린이라 r에서 바로 빼면 된다

cur = best = 0
for v in diff:
    cur += v
    best = max(best, cur)
print(best)
```
반열린 구간이라 `-1`을 `r+OFF`에 그대로 놓는다는 점이 깔끔하다 — 블럭 문제에서 `diff[b] -= 1`(닫힌 구간이라 b+1이 아니라 b, 0-index 보정 때문)과 우연히 형태가 같아 보이지만 **도출 경로가 다르다.** 헷갈리면 항상 "칸 하나가 뭘 뜻하는지"부터 다시 정의할 것.
연결되는 지점: 좌표 범위가 커지면 이 배열 방식이 무너지고 **좌표 압축 + 스위핑(이벤트 정렬)**으로 간다. 시작 이벤트 +1, 끝 이벤트 -1을 좌표순으로 정렬해 훑는 방식이고, 같은 좌표에서 끝 이벤트를 시작 이벤트보다 먼저 처리해야 반열린 규약이 유지된다. 회의실 배정·구간 스케줄링 계열이 전부 이 골격이다.
## 다시 볼 때 체크할 것
- [ ] 선분 \[1,3\]과 \[3,5\]의 답이 1인 이유를 설명할 수 있는가? 닫힌 구간으로 세면 왜 틀리는가?
- [ ] 좌표 범위가 10\^9로 커지면 어떻게 바꾸는가? 좌표 압축/스위핑 코드를 손으로 쓸 수 있는가?
- [ ] 스위핑에서 같은 좌표의 시작/끝 이벤트 처리 순서를 뒤집으면 답이 어떻게 달라지는가?
