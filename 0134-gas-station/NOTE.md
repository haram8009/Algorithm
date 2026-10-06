---
schema_version: 1
platform: "LeetCode"
title: "Gas Station"
problem_url: "https://leetcode.com/problems/gas-station/"
notion_page_url: "https://app.notion.com/p/3bb3d9b37e1c8177a36fdf7c52ac33f2"
legacy_title: "Gas Station"
problem_id: 134
difficulty: "Medium"
topics: ["Array/String","Greedy"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-13"
retry_needed: true
retry_at: ""
language: ["Python"]
complexity: "O(n) / O(1)"
time_minutes: ""
code_url: "https://github.com/haram8009/algorithm-study/blob/main/이하람/week02/gas_station.py"
---
## 문제 요약
원형으로 배치된 n개 주유소에서 `gas[i]`를 얻고 다음 주유소까지 `cost[i]`를 쓴다. 빈 탱크로 출발해 한 바퀴 돌 수 있는 출발 지점을 반환(없으면 -1). 답은 유일하다고 보장된다.
## 내 접근
`diff = gas[i] - cost[i]`를 한 번만 순회하면서 두 가지를 동시에 누적했다.
- `total`: 전체 합 → 음수면 애초에 답이 없음
- `tank`: 현재 후보 구간의 누적 → 음수가 되는 순간 `start = i+1`로 리셋
## 막힌 지점
n\^2으로 풀어서 시간초과남
```python
class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        n = len(gas)
        for i in range(n):
            if gas[i] < cost[i]:
                continue
            tank = 0
            j = i
            success = True
            for _ in range(n):
                tank += gas[j] - cost[j]
                if tank < 0:
                    success = False
                    break
                j = (j + 1) % n
            if success:
                return i
        return -1
```
## 배운 것 / 패턴
**1. 이 문제가 O(n)이 되는 진짜 이유 — 명제 두 개를 분리했기 때문**
`total`과 `tank`를 따로 둔 게 우연이 아니라, 서로 독립적인 두 명제에 정확히 대응된다:
- **존재성**: `sum(gas) >= sum(cost)` 이면 답이 반드시 존재한다 → `total`이 담당
- **위치**: 그 답이 어디인가 → `tank` / `start`가 담당
두 질문을 한 번의 순회에 겹쳐 답한 게 이 풀이의 핵심이다. 분리해서 보지 못하면 "각 시작점마다 한 바퀴 돌려보기" O(n²)에서 벗어나지 못한다.
**2. ****`start = i+1`****로 건너뛰어도 되는 증명 (이걸 말로 설명할 수 있어야 한다)**
`start`부터 누적했는데 `i`에서 처음 음수가 됐다고 하자. 그럼 `start < j <= i` 인 **어떤 j로 시작해도 반드시 실패**한다:
- `start ~ i` 구간에서 `i` 전까지의 모든 부분합은 ≥ 0 이었다 (아니었다면 더 일찍 리셋됐을 것)
- 즉 `sum(start..j-1) >= 0` 이므로 `sum(j..i) <= sum(start..i) < 0`
- 따라서 j에서 출발해도 i에서 똑같이 터진다
중간 후보를 통째로 버릴 수 있으니 포인터가 뒤로 가지 않고, 그래서 한 번의 순회로 충분하다.
**3. 패턴 이름: "누적하다 음수면 리셋" — Kadane과 같은 골격**
```python
# Gas Station
if tank < 0:
    start = i + 1
    tank = 0

# Kadane (Maximum Subarray)
if cur < 0:
    cur = 0
```
둘 다 "음수로 끌고 가는 접두사는 미래에 도움이 안 된다"는 같은 관찰에서 나온다. Kadane은 값을, 이 문제는 위치를 추적할 뿐.
**4. 연결점**
- 같은 2주차 **Product of Array Except Self**와 같은 "한 방향 누적으로 O(n)" 계열. 거긴 누적곱, 여긴 누적합 + 리셋 조건이 붙은 것.
- 13주차 **Maximum Subarray / Maximum Sum Circular Subarray**에서 거의 그대로 재등장한다. 원형 배열이라는 설정까지 같으니 그때 이 문제를 다시 끓어보면 좋다.
**5. 사소한 개선 여지**
기능적으로는 완성된 풀이다. 굳이 꼽자면 `diff` 계산을 `zip(gas, cost)`로 돌려 인덱스 접근을 줄일 수 있지만, `start = i+1`에서 인덱스가 필요하므로 `enumerate`를 써야 한다 — 지금 형태도 충분히 깔끔하다.
## 다시 볼 때 체크할 것
- [ ] `start = i+1`로 건너뛰어도 답을 놓치지 않는 이유를 증명처럼 설명할 수 있는가?
- [ ] `total`과 `tank`가 각각 어떤 질문에 답하는지 단어로 말할 수 있는가?
- [ ] Kadane의 `if cur < 0: cur = 0`과 이 문제의 리셋이 왜 같은 논리인지 설명할 수 있는가?
<empty-block/>
