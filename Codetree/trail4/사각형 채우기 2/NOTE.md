---
schema_version: 1
platform: "Codetree"
title: "사각형 채우기 2"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/test-rectangle-fill-2"
problem_id: "test-rectangle-fill-2"
difficulty: "보통"
topics: ["DP", "타일링"]
study_group: ""
result: "Passed"
solved_at: "2026-10-26"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "시간 O(N), 공간 O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EC%82%AC%EA%B0%81%ED%98%95%20%EC%B1%84%EC%9A%B0%EA%B8%B0%202/rectangle-fill-2.java"
---

## 한 줄 인사이트

너비 2를 한 번에 마무리하는 모양이 두 가지이므로 `dp[i - 2]`에 2를 곱한다.

## 상태와 점화식

`dp[i]`는 너비가 `i`인 직사각형을 채우는 경우의 수다.

| 마지막 모양 | 남은 너비 | 경우의 수 |
|---|---|---|
| 세로 타일 하나 | `i - 1` | `dp[i - 1]` |
| 가로 타일 두 개 | `i - 2` | `dp[i - 2]` |
| 정사각형 타일 하나 | `i - 2` | `dp[i - 2]` |

```java
dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10_007;
```

마지막 모양이 달라 각 경우는 겹치지 않는다. 너비 2를 세로 타일 두 개로 채우는 경우는 첫 번째 항에서 이미 세므로 다시 추가하지 않는다.

## 초기값과 계산 순서

`dp[0] = 1`은 빈 직사각형을 채우는 한 가지 방법이고, `dp[1] = 1`이다. `i = 2`부터 순서대로 계산한다. 초기 수열은 `1, 1, 3, 5, 11, ...`이다.

## 복잡도와 주의점

시간 `O(N)`, 공간 `O(N)`이다. 이전 두 상태만 사용하므로 공간을 `O(1)`로 줄일 수도 있다.

매 단계 나머지를 저장하므로 현재 식의 중간값도 `int` 범위 안이다. 현재 코드는 `dp[1]`을 직접 초기화하므로 `n >= 1`을 전제로 한다.
