---
schema_version: 1
platform: "Codetree"
title: "최대로 겹치는 지점"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-maximum-overlapped-points"
notion_page_url: "https://app.notion.com/p/3d03d9b37e1c816890eafe28f79d3cc0"
legacy_title: "\\[코드트리\\] 최대로 겹치는 지점"
problem_id: ""
difficulty: "Easy"
topics: ["Array/String","Intervals"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-03"
retry_needed: true
retry_at: ""
language: ["Python"]
complexity: "O(n·C) / O(C), C=좌표 범위 101  ※ 차이 배열이면 O(n+C)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail2/%EC%B5%9C%EB%8C%80%EB%A1%9C%20%EA%B2%B9%EC%B9%98%EB%8A%94%20%EC%A7%80%EC%A0%90/maximum-overlapped-points.py"
---
## 문제 요약
수직선 위의 선분 n개가 `(l, r)`로 주어진다(좌표 0 이상). 가장 많은 선분이 겹치는 **지점**의 겹침 수를 출력한다.
## 내 접근
좌표 범위(0\~100)만큼 배열을 잡고 각 칸을 *점* 하나로 본다. 선분마다 `range(l, r+1)`로 양 끝을 포함해 순회하며 +1, 마지막에 `max`.
```python
arr = [0]*101
for l, r in segments:
    for i in range(l, r+1):
        arr[i] += 1
print(max(arr))
```
오프셋이 필요 없고(좌표가 음수가 아니므로), 배열 크기를 100이 아니라 **101**로 잡은 게 포인트 — 좌표 100이 유효한 인덱스여야 한다.
## 막힌 지점
## 배운 것 / 패턴
**구간 칠하기 3종 세트(블럭쌓는 명령2 / 최대로 겹치는 구간 / 최대로 겹치는 지점)의 마지막 조각이고, 세 문제의 차이는 딱 하나 — 배열의 한 칸이 무엇을 뜻하는가다.**
<table header-row="true">
<tr>
<td>문제</td>
<td>한 칸의 의미</td>
<td>순회</td>
<td>크기</td>
</tr>
<tr>
<td>블럭쌓는 명령2</td>
<td>블럭 자리(1-indexed 칸)</td>
<td>`range(a-1, b)`</td>
<td>n</td>
</tr>
<tr>
<td>최대로 겹치는 구간</td>
<td>단위 구간 \[i, i+1)</td>
<td>`range(l+100, r+100)`</td>
<td>200 (음수 오프셋)</td>
</tr>
<tr>
<td>최대로 겹치는 지점</td>
<td>점 i</td>
<td>`range(l, r+1)`</td>
<td>101</td>
</tr>
</table>
결정적 대조: **선분 \[1,3\]과 \[3,5\]는 이 문제에서 답이 2고, '최대로 겹치는 구간'에서는 1이다.** 같은 입력, 같은 코드 골격인데 답이 다르다. 점 3에서는 두 선분이 실제로 만나지만, 겹치는 *구간의 길이*는 0이기 때문. 문제 제목의 "지점 / 구간" 두 글자가 반열린·닫힌 구간 선택을 강제한다 — 관성으로 복붙하면 정확히 여기서 off-by-one이 난다.
차이 배열 버전. 닫힌 구간이므로 `-1`을 `r+1`에 놓는다(구간 문제에서 `r`에 놓았던 것과 대비):
```python
n = int(input())
diff = [0]*103          # 좌표 0~100 + r+1 이 100+1=101까지 → 여유 있게
for _ in range(n):
    l, r = map(int, input().split())
    diff[l]   += 1
    diff[r+1] -= 1      # 닫힌 구간 → 끝점 다음에서 감소

cur = best = 0
for v in diff:
    cur += v
    best = max(best, cur)
print(best)
```
지금 풀이의 복잡도는 O(n·C)지만 C가 상수 101이라 사실상 O(n)이고 통과에는 문제가 없다. 그래도 재도전 대상으로 둔 이유는 **좌표 범위가 문제 조건에 묶인 우연 덕에 살아남은 풀이**라서다. C가 10\^9로 커지는 순간 배열 자체가 불가능해지고, 그때는 차이 배열도 안 되고 좌표 압축 + 이벤트 스위핑으로 간다. 이 문제에서는 닫힌 구간이니 스위핑에서도 **같은 좌표의 시작 이벤트를 끝 이벤트보다 먼저** 처리해야 한다 — 구간 문제와 정확히 반대 순서다.
## 다시 볼 때 체크할 것
- [ ] \[1,3\]과 \[3,5\]의 답이 '지점'에서는 2, '구간'에서는 1인 이유를 그림 없이 설명할 수 있는가?
- [ ] 차이 배열에서 `-1`을 `r`에 놓는지 `r+1`에 놓는지, 그 판단 근거를 매번 다시 도출할 수 있는가?
- [ ] 좌표가 10\^9까지 커진 버전을 스위핑으로 풀 때, 같은 좌표의 시작/끝 이벤트 처리 순서를 어떻게 잡아야 하는가?
