---
schema_version: 1
platform: "LeetCode"
title: "Zigzag Conversion"
problem_url: "https://leetcode.com/problems/zigzag-conversion/"
notion_page_url: "https://app.notion.com/p/3bc3d9b37e1c81cb9913ce5c9745485f"
legacy_title: "Zigzag Conversion"
problem_id: 6
difficulty: "Medium"
topics: ["Array/String"]
study_group: "타임어택"
result: "못 품"
solved_at: "2026-08-13"
retry_needed: false
retry_at: "2026-08-14"
language: ["Python"]
complexity: "O(n) / O(n)"
time_minutes: ""
code_url: "https://github.com/haram8009/algorithm-study/blob/이하람/zigzag-conversion/이하람/week02/timeattack/zigzag_conversion.py"
---
## 문제 요약
문자열 `s`를 `numRows`줄짜리 지그재그(아래로 ↓ → 대각선 위로 ↗ 반복)로 쓴 뒤, **행 단위로 왼쪽→오른쪽 읽은 결과**를 반환한다.
## 내 접근
**타임어택 당시 (8/13, 시간 내 미완성)** — 문제 그림 그대로 **실제 2차원 격자**를 만들어 `(r, c)` 좌표에 문자를 배치하려 했다. `dr = [1, -1]`, `dc = [0, 1]` 방향 벡터로 아래/대각선위를 전환.
근데 열 길이 계산을 제대로 못해서 2차원 배열로 못풀었다.
```javascript
class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows==1:
            return s
        r, c = 0, 0
        arr = [""] * numRows

        # print(arr)

        # PAYPALISHIRING
        # ^  >^  >^  >^ 
        # PAYPALISHIRING
        # ^   >>^   >>^ 

        dr = [1, -1]
        dc = [0, 1]
        d = 0  # 0: 아래로, 1: 대각선위로
        for ch in s:
            arr[r][c] = ch

            next_r = r + dr[d]
            if next_r == numRows:  # 바닥 도달 - 방향 대각선위로 바꿔주기
                d = 1
            elif next_r == -1:  # 천장 도달 - 방향 아래로 바꿔주기
                d = 0
            r = r + dr[d]
            c = c + dc[d]

        answer = ""
        for i in range(len(arr)):
            for j in range(len(arr[0])):
                if arr[i][j] != 0:
                    answer += arr[i][j]
            # print()
        return answer

```
**재풀이 (8/14, Accepted 8ms 68.54%)** — 격자를 버리고 **행별 문자열 ****`numRows`****개**만 두고, 현재 행에 계속 append. 0행/마지막 행에 닿으면 방향 부호를 뒤집는다.
## 막힌 지점
## 배운 것 / 패턴
**1. 핵심 — 열(column)은 답에 전혀 필요 없다**
출력은 "각 행을 왼쪽→오른쪽으로 이어붙인 것"이다. 그런데 `s`를 앞에서부터 순서대로 훑으면 **같은 행 안에서는 열 좌표가 자동으로 오름차순**으로 들어온다. 그러면 열 번호를 기억할 이유가 없고, 행별로 그냥 붙여나가면 된다.
타임어택 때 막힌 근본 원인이 정확히 여기다 — **문제가 그림으로 설명되니까 자료구조도 그림을 닮아야 한다고 가정**했고, 그 순간 필요 없는 차원 하나(열)를 짊어졌다. 열을 쓰려면 전체 열 개수를 미리 계산해야 하고(주기 `2*numRows-2` 당 `numRows-1`열), 그 계산이 시간을 먹는다.
**2. 타임어택 코드는 사실 실행 자체가 안 된다 — 버그 3개**
```python
arr = [""] * numRows     # 1차원 "문자열" 리스트
arr[r][c] = ch            # ← str은 불변: TypeError: 'str' object does not support item assignment
```
- **(a)** 2차원을 쓰려면 `[[""] * cols for _ in range(numRows)]`처럼 실제로 할당해야 하고, 그러려면 `cols`를 먼저 구해야 한다.
- **(b)** `if arr[i][j] != 0` — 빈 칸을 `""`로 채우고 `0`과 비교했다. `"" != 0`은 항상 참이라 필터가 안 먹힌다. `!= ""`여야 한다.
- **(c)** `len(arr[0])`로 열 개수를 구하는 것도 (a)가 안 되어 있으면 무의미.
다만 **방향 전환 로직 자체는 맞았다.** `next_r`로 한 칸 앞을 미리 보고 경계면 뒤집는 방식은 정확하다. **실패한 건 알고리즘이 아니라 자료구조 선택이었다** — 이걸 구분해서 기억해두는 게 다음에 도움이 된다.
**3. 재풀이 코드의 미묘한 지점 — ****`d = -1`****로 시작하는 이유**
```python
d = -1
row = 0
for i in range(len(s)):
    if row == 0 or row == numRows-1:   # 경계면 → 부호 반전
        d = -d
    arr[row] += s[i]
    row += d
```
첫 반복에서 `row == 0`이므로 `d`가 `-1 → +1`로 바뀜어 아래로 내려간다. `d = 1`로 시작했다면 첫 칸에서 바로 위로 올라가 버린다. 이 "경계에 **있을 때** 반전"은 타임어택 코드의 "경계에 **닿을 예정이면** 반전"보다 분기가 하나 적다.
**4. ****`numRows == 1`**** 가드가 없으면 정확히 여기서 터진다**
`numRows == 1`이면 `row == 0`과 `row == numRows-1`이 **동시에 참**이라 매 문자마다 `d`가 뒤집히고, `row`가 `0 → 1`로 나가 `IndexError`. 지그재그 문제의 단골 엣지 케이스다.
**5. 연결점**
- "그림을 그대로 자료구조로 옜기지 말고 **답에 필요한 축만 남긴다**"는 4주차 **Matrix**(Spiral Matrix, Rotate Image, Set Matrix Zeroes)에서 계속 나온다. 특히 Set Matrix Zeroes가 "별도 배열 없이 첫 행/열을 플래그로 쓴다"는 같은 결의 문제.
- `dr`/`dc` 방향 벡터 + 경계에서 반전 패턴은 10주차 **Graph BFS/DFS**에서 그대로 쓴다. 이번엔 열이 필요 없어서 과잉이었을 뿐, 발상 자체는 버릴 것이 아니다.
- 같은 2주차 **Reverse Words in a String**과 정반대 교훈이라 같이 보면 좋다 — 거긴 내장 함수가 문제를 너무 쉽게 만들었고, 여긴 자료구조를 너무 어렵게 잡았다.
## 다시 볼 때 체크할 것
- [ ] 열 좌표를 추적할 필요가 없는 이유를 한 문장으로 설명할 수 있는가?
- [ ] `d = -1`로 시작하고 대입 전에 반전하는 순서가 왜 맞는지 설명할 수 있는가?
- [ ] `numRows == 1` 가드를 빼면 정확히 어느 줄에서 터지는가?
