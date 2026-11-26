---
schema_version: 1
platform: "Codetree"
title: "사각형 채우기"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-rectangle-fill"
problem_id: "challenge-rectangle-fill"
difficulty: "쉬움"
topics: ["DP", "타일링"]
study_group: ""
result: "Passed"
solved_at: "2026-10-19"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "시간 O(N), 공간 O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EC%82%AC%EA%B0%81%ED%98%95%20%EC%B1%84%EC%9A%B0%EA%B8%B0/rectangle-fill.java"
---

## 한 줄 인사이트

마지막 모양이 차지하는 너비가 1인지 2인지 나누면 남은 부분의 경우의 수를 그대로 더할 수 있다.

## 상태와 점화식

`dp[i]`는 너비가 `i`인 직사각형을 채우는 경우의 수다.

- 마지막 너비 1을 세로 타일 하나로 채우면 앞부분은 `dp[i - 1]`가지다.
- 마지막 너비 2를 가로 타일 두 개로 채우면 앞부분은 `dp[i - 2]`가지다.

두 경우는 마지막 모양이 달라 겹치지 않는다.

```java
dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
```

## 초기값과 계산 순서

`dp[0] = 1`은 아무것도 놓지 않는 방법 한 가지다. 너비 2를 한 번에 채우는 경우도 이 값으로 계산할 수 있다. `dp[1] = 1`로 두고 `i = 2`부터 차례로 채운다. 초기 수열은 `1, 1, 2, 3, 5, ...`이며 답은 `dp[n]`이다.

## 복잡도와 주의점

시간 `O(N)`, 배열 공간 `O(N)`이다. 이전 두 값만 사용하므로 변수 두 개로 공간을 `O(1)`로 줄일 수 있다.

현재 코드는 `dp[1]`을 직접 초기화하므로 `n >= 1`을 전제로 한다. 매 단계 나머지를 저장하므로 현재 덧셈은 `int` 범위 안에서 안전하다.
