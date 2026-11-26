---
schema_version: 1
platform: "Codetree"
title: "동전 프로모션"
problem_url: "https://www.codetree.ai/frequent-problems/hsat/problems/coin-promotion"
problem_id: "coin-promotion"
difficulty: "13"
topics: ["DP", "배낭 문제", "동전 교환"]
study_group: ""
result: "Passed"
solved_at: "2026-10-29"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "시간 O(NM), 공간 O(NM)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/hsat/동전%20프로모션/coin-promotion.java"
---

## 한 줄 인사이트

같은 동전을 다시 쓸 수 있는지는 점화식에서 현재 행을 참조하는지, 이전 행을 참조하는지로 표현한다.

## DP 상태

`dp[i][j]`는 앞에서부터 i번째 동전 종류까지 고려하여 금액 j를 만드는 최소 동전 수이다. 만들 수 없는 상태는 MAX_VALUE = 10001로 둔다.

동전이 하나도 없을 때는 금액 0만 만들 수 있으므로 dp[0][0] = 0, 나머지 dp[0][j]는 무한대로 초기화한다. 각 행의 dp[i][0]은 배열 기본값 0을 사용한다.

## 점화식

현재 동전의 금액을 w라고 하자. 사용하지 않는 경우는 항상 dp[i - 1][j]이다.

### A 타입: 같은 동전을 반복 사용

```java
dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - w] + 1);
```

현재 행 dp[i][j - w]에는 이미 현재 동전을 사용한 경우가 포함될 수 있다. 여기에 한 개를 더하므로 반복 사용이 가능하다. 금액을 작은 값부터 채우므로 이 상태는 먼저 계산되어 있다.

### 그 외 타입: 현재 동전을 한 번만 사용

```java
dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - w] + 1);
```

이전 행에는 현재 동전을 사용한 경우가 없다. 이전 행에서만 가져오므로 현재 동전은 최대 한 번 추가된다. j < w라면 이전 행의 값을 복사한다.

## 정답과 복잡도

모든 동전을 고려한 dp[N][M]이 정답이다. 현재 코드는 각 행 dp[i][M]의 최솟값을 answer에 모으는데, 종류가 늘어나도 기존 경우를 유지할 수 있으므로 마지막 행의 값과 같다. 정답이 MAX_VALUE이면 -1을 출력한다.

동전 종류 N개와 목표 금액 M을 순회하므로 시간 O(NM), 2차원 DP 배열로 공간 O(NM)이다.

## 주의할 점

- A 타입에서 이전 행만 참조하면 반복 사용이 불가능해진다.
- 한 번만 사용할 동전에서 현재 행을 참조하면 여러 번 사용하게 된다.
- 불가능한 상태를 0으로 두면 동전 수가 과소 계산되므로 무한대 초기화가 필요하다.
- 1차원으로 줄일 때 A 타입은 금액 오름차순, 한 번만 쓰는 타입은 내림차순으로 순회한다.
- 무한대 상수는 가능한 최대 동전 수보다 커야 하며, 1을 더해도 정수 범위를 넘지 않아야 한다.
