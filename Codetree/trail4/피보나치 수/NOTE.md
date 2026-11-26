---
schema_version: 1
platform: "Codetree"
title: "피보나치 수"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-fibonacci-number"
problem_id: "intro-fibonacci-number"
difficulty: ""
topics: ["DP", "메모이제이션", "재귀"]
study_group: ""
result: "Passed"
solved_at: "2026-10-26"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "시간 O(n), 공간 O(n)"
time_minutes: ""
code_url: ""
---

## 한 줄 인사이트

한 번 계산한 피보나치 수를 배열에 저장하면 같은 하위 문제를 다시 계산하지 않는다.

## 상태와 점화식

`dp[i]`는 i번째 피보나치 수다. 기저값은 `dp[0] = 0`, `dp[1] = 1`이고 점화식은 `dp[i] = dp[i-1] + dp[i-2]`다.

현재 코드는 `fibo(n)`에서 필요한 상태를 재귀로 요청하는 탑다운 DP다. `n <= 1`이면 기저값을 반환하고, `dp[n] > 0`이면 저장한 값을 반환한다. 처음 계산하는 상태만 두 하위 상태의 합을 저장한다.

예를 들어 `fibo(5)`에서 `fibo(4)`를 구하는 과정에 `fibo(3)`도 저장된다. 나중에 같은 상태를 요청하면 재귀를 다시 펼치지 않는다.

## 복잡도

- 시간: `O(n)` — 각 상태를 한 번 계산한다.
- 공간: `O(n)` — DP 배열과 최대 n 깊이의 재귀 스택을 사용한다.

## 주의점과 개선

- 현재 `main`은 `dp[1]`을 무조건 대입하므로 n=0을 허용하도록 일반화하면 배열 크기를 최소 2로 잡거나 별도 처리해야 한다.
- `dp[n] > 0`은 양수인 피보나치 수의 성질을 이용한 계산 여부 검사다. 결과가 0일 수도 있는 다른 DP에는 별도 방문 배열 등이 필요하다.
- 큰 입력에는 int 오버플로가 생기므로 long 또는 BigInteger가 필요하다.
- 반복문으로 바꾸고 직전 두 값만 유지하면 추가 공간을 `O(1)`로 줄일 수 있다.
