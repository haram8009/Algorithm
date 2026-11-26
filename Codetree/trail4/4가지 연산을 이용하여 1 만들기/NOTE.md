---
schema_version: 1
platform: "Codetree"
title: "4가지 연산을 이용하여 1 만들기"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-make-one-using-four-operations"
problem_id: "make-one-using-four-operations"
difficulty: "어려움"
topics: ["BFS", "최단 경로", "DP"]
study_group: ""
result: "Passed"
solved_at: "2026-11-26"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "현재 코드 시간 O(MAXN), 공간 O(MAXN)"
time_minutes: ""
code_url: "./make-one-using-four-operations.java"
---

## 한 줄 인사이트

정수 하나를 상태로 보고 연산 한 번을 간선 하나로 보면, 모든 간선 비용이 1인 최단 경로 문제다.

## 문제와 상태

현재 수에 1을 더하거나 빼고, 2 또는 3으로 나누어떨어질 때 나누는 연산으로 1까지 가는 최소 횟수를 구한다. `+1`도 가능하므로 현재 수보다 큰 수를 거치는 경로도 고려해야 한다.

예를 들어 11은 `11 → 12 → 4 → 2 → 1`로 4번에 도달한다. 감소하는 연산만 사용하면 이 경로를 놓친다.

## 제출 코드의 접근

`dp[i]`를 i에서 1까지 가는 연산 횟수로 두고, 1부터 `MAXN = 1_000_000`까지 채운다.

```java
answer = dp[i - 1] + 1;
if (i % 2 == 0) answer = Math.min(answer, dp[i / 2] + 1);
if (i % 3 == 0) answer = Math.min(answer, dp[i / 3] + 1);
if (answer < dp[i - 1]) dp[i - 1] = answer + 1;
dp[i] = answer;
```

앞의 세 후보는 `-1`, `/2`, `/3`을 사용한 경로다. 마지막 갱신은 `(i-1) → i`의 `+1` 연산으로 직전 상태를 개선하려는 처리다.

이 구현은 제출 당시 Passed를 받았지만, 이미 계산한 값을 나중에 수정하고 그 영향을 모든 상태로 다시 전파하지 않는다. 따라서 일반적인 바텀업 DP처럼 작은 상태가 먼저 확정된다는 설명만으로 이 구현의 정당성을 주장해서는 안 된다.

## BFS로 이해하기

큐에는 `Node(num, dist)`를 넣는다. 시작 상태는 `(n, 0)`이고, 네 가지 연산으로 이동할 수 있는 다음 상태를 `(next, dist + 1)`로 넣는다. FIFO 큐는 거리가 작은 상태부터 처리하므로 처음 1에 도달했을 때의 `dist`가 최소 횟수다.

중복 상태를 막기 위해 **큐에 넣는 순간 방문 표시**해야 한다. 현재 코드의 주석 처리된 우선순위 큐 풀이에는 방문 처리가 없어 같은 정수가 여러 경로로 반복 삽입된다. 비용이 모두 1이므로 우선순위 큐 대신 `ArrayDeque`로 충분하다.

배열을 사용하는 BFS라면 탐색 상한이 최적 경로를 제외하지 않는다는 근거가 필요하다. 입력 최댓값을 그대로 상태 상한으로 삼으면 `+1` 경로를 잘라낼 수 있으므로 주의한다. 범위를 S개 상태로 제한할 수 있을 때 BFS의 시간과 공간은 O(S)다.

## 복잡도와 주의점

- 현재 코드는 입력 n과 관계없이 백만 개 상태를 계산하므로 시간과 공간 모두 O(MAXN)이다.
- 바텀업과 탑다운의 속도 차이는 실제로 방문하는 상태 수와 호출 비용에 달려 있다. 탑다운이라는 이유만으로 항상 빠르지는 않다.
- 연산이 서로 순환하므로 메모이제이션만 붙인 재귀는 순환 호출에 주의해야 한다.
