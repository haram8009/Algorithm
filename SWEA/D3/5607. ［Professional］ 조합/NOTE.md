---
schema_version: 1
platform: "SWEA"
title: "[Professional] 조합"
problem_url: "https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGKdbqczEDFAUo"
problem_id: "5607"
difficulty: ""
topics: ["조합", "모듈러 역원", "페르마의 소정리", "분할 정복 거듭제곱"]
study_group: ""
result: "Passed"
solved_at: "2026-10-20"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "시간 O(M+T log P), 공간 O(M+log P), M=1000000"
time_minutes: ""
code_url: ""
---

## 한 줄 인사이트

나머지 연산에서는 분모로 직접 나누지 않고, 소수 모듈러에서 분모의 역원을 구해 곱한다.

## 조합 공식과 역원

`nCr = n! / (r! × (n-r)!)`이지만 팩토리얼의 나머지를 정수 나눗셈하면 올바른 답을 얻지 못한다.

소수 `P = 1,234,567,891`에 대해 페르마의 소정리는 P의 배수가 아닌 a에 대해 `a^(P-1) ≡ 1 (mod P)`임을 보장한다. 따라서 a의 모듈러 역원은 `a^(P-2) mod P`다.

## 현재 코드의 계산 순서

1. 모든 테스트 케이스에서 재사용할 `fact[i] = i! mod P`를 1,000,000까지 전처리한다.
2. `denom = fact[n-r] × fact[r] mod P`를 구한다.
3. `pow(denom, P-2)`로 분모의 역원을 구한다.
4. `fact[n] × 역원 mod P`를 출력한다.

입력 상한이 P보다 작으므로 팩토리얼에 P가 인수로 들어가지 않는다. 분모가 0이 아니므로 역원이 존재한다.

## 빠른 거듭제곱

`pow(base, exp)`는 지수를 절반으로 나눈 값을 한 번 구해 제곱한다. 지수가 홀수면 base를 한 번 더 곱하고, 지수가 0이면 1을 반환한다. 각 곱셈 뒤 `% P`를 적용하며 연산 횟수와 재귀 깊이는 `O(log P)`다.

## 복잡도

M은 전처리 상한, T는 테스트 케이스 수다.

- 시간: `O(M + T log P)`.
- 공간: 팩토리얼 배열 `O(M)`과 재귀 스택 `O(log P)`.

## 주의점

- `fact[0] = 1`이어야 r=0이나 r=n도 같은 공식으로 처리한다.
- 곱셈은 long으로 수행해야 한다. 현재 long 배열과 long 매개변수를 사용해 int 곱셈 오버플로를 피한다.
- 나눗셈을 나머지 연산과 자유롭게 섞을 수 없다. 역원과의 곱셈으로 바꾸는 것이 핵심이다.
- 저장된 소스의 확장자는 txt지만 내용은 Java 코드다.
