---
schema_version: 1
platform: "Codetree"
title: "사각형 채우기 3"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-rectangle-fill-3"
problem_id: "challenge-rectangle-fill-3"
difficulty: "보통"
topics: ["DP", "타일링"]
study_group: ""
result: "Passed"
solved_at: "2026-10-19"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "제출 코드: 시간 O(N^2), 공간 O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EC%82%AC%EA%B0%81%ED%98%95%20%EC%B1%84%EC%9A%B0%EA%B8%B0%203/rectangle-fill-3.java"
---

## 한 줄 인사이트

마지막 부분의 너비가 3 이상인 경우까지 더해야 하며, 반복해서 등장하는 이전 상태들의 합은 누적합으로 관리할 수 있다.

## 상태와 제출 코드의 점화식

`dp[i]`는 너비가 `i`인 직사각형을 채우는 경우의 수다. 제출 코드는 마지막으로 붙이는 부분의 너비에 따라 계산한다.

- 너비 1로 마무리: `2 * dp[i - 1]`.
- 너비 2로 마무리: `3 * dp[i - 2]`.
- 너비 3 이상으로 마무리: 남은 너비 `j = 0 .. i - 3`마다 `2 * dp[j]`.

```text
dp[i] = 2 * dp[i - 1] + 3 * dp[i - 2]
        + 2 * (dp[0] + ... + dp[i - 3])  (mod M)
M = 1,000,000,007
```

너비 3 이상의 항을 빠뜨리면 필요한 경우를 놓친다.

## 초기값과 계산 흐름

`dp[0] = 1`, `dp[1] = 2`다. 빈 직사각형을 한 가지로 세기 때문에 앞부분 없이 전체를 마지막 부분으로 채우는 경우도 계산할 수 있다.

```java
dp[i] = (dp[i - 1] * 2 + dp[i - 2] * 3) % MOD;
for (int j = i - 3; j >= 0; j--) {
    dp[i] = (dp[i] + dp[j] * 2) % MOD;
}
```

처음 값은 `1, 2, 7, 22`다. `i = 2`일 때 내부 반복문은 실행되지 않는다.

## 복잡도와 개선 방향

제출 코드는 각 너비마다 앞 상태들의 합을 다시 구하므로 시간 `O(N^2)`, 공간 `O(N)`이다.

`prefix[k] = dp[0] + ... + dp[k]`를 나머지로 저장하면 다음과 같이 시간 `O(N)`에 계산할 수 있다.

```text
dp[i] = (2 * dp[i - 1] + 3 * dp[i - 2]
         + (i >= 3 ? 2 * prefix[i - 3] : 0)) % M
prefix[i] = (prefix[i - 1] + dp[i]) % M
```

이는 개선 아이디어이며 현재 제출 코드는 이중 반복문이다.

## 주의점

나머지를 취하기 전 곱셈과 덧셈이 `int` 범위를 넘을 수 있으므로 제출 코드처럼 `long[]`과 `long MOD`를 사용해야 한다. 계산이 끝난 뒤 `long`으로 바꾸면 이미 발생한 오버플로는 복구되지 않는다.

현재 코드는 `dp[1]`을 직접 초기화하므로 `n >= 1`을 전제로 한다.
