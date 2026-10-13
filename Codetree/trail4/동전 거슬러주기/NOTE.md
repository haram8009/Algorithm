---
schema_version: 1
platform: "Codetree"
title: "동전 거슬러주기"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-coin-change"
notion_page_url: ""
legacy_title: ""
problem_id: ""
difficulty: "쉬움"
topics: ["Dynamic Programming","Memoization","Coin Change"]
study_group: ""
result: "Accepted"
solved_at: "2026-10-13"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(NM) / O(M)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EB%8F%99%EC%A0%84%20%EA%B1%B0%EC%8A%AC%EB%9F%AC%EC%A3%BC%EA%B8%B0/coin-change.java"
---

## 문제 요약

코드는 동전 종류 N개, 목표 금액 M, 각 동전의 금액을 입력받는다. 동전을 골라 M을 만드는 데 필요한 최소 개수를 출력하고, 만들 수 없으면 -1을 출력한다. README는 Trail 4의 DP 문제로 분류한다. 사용 개수나 금액 범위의 상세 조건은 README에 없어 코드의 상태와 점화식에 근거해 설명한다.

## 코드의 접근

`dp[x]`를 금액 x를 만드는 최소 동전 수로 둔다. `dp[0]=0`이고, 아직 계산하지 않은 칸은 -1이다. 메모이제이션 함수 `f(x)`는 x 이하인 모든 동전 `coin[i]`에 대해 `f(x-coin[i])+1`의 최솟값을 구해 저장한다. 메인 함수는 `f(M)`을 호출한 뒤 불가능 상태라면 -1, 아니면 `dp[M]`을 출력한다.

불가능 상태는 `NOT_SOLVABLE = Integer.MAX_VALUE - 1`로 표현한다. 하위 금액이 불가능할 때도 그 값에 1을 더하므로, `Integer.MAX_VALUE`를 직접 초기값으로 쓰는 경우의 정수 오버플로를 피한다.

## 핵심 원리

최적해의 마지막 동전을 하나 고정하면 그 앞의 금액 `x-coin[i]`도 최소 개수로 만들어야 한다. 더 많이 쓰는 해가 있었다면 마지막 동전을 그대로 붙여 더 나쁜 답이 되기 때문이다. 따라서 가능한 마지막 동전의 후보를 모두 비교한 점화식이 성립한다. 같은 금액은 저장된 값을 재사용해 중복 재귀를 없앤다.

## 커밋에서 확인한 변경과 주의점

첫 [Passed] 제출에는 `Integer.MAX_VALUE - 1`이 여러 곳에 직접 쓰여 있었다. 다음 [Passed] 커밋은 이 값을 `NOT_SOLVABLE` 상수로 추출하고 출력 조건도 같은 상수를 사용하도록 바꿨다. 점화식은 바뀌지 않았다. 복습 시 -1은 ‘미계산’, `NOT_SOLVABLE`은 ‘계산했지만 불가능’을 뜻한다는 차이를 구분한다. 양의 동전 금액을 전제로 금액이 줄어드는 재귀 구조다.

## 복잡도

N은 동전 종류 수, M은 목표 금액이다. 각 금액 상태를 한 번 계산하며 동전 N개를 살펴보므로 시간은 O(NM)이다. `dp` 배열은 O(M), 재귀 호출 스택은 최악 O(M)이므로 전체 추가 공간은 O(M)이다.

## 한 줄 인사이트

최소 동전 수는 ‘마지막에 쓸 동전’을 모두 시도한 뒤 남은 금액의 최적값을 재사용해 구한다.

## 복습 질문

- [ ] `dp[x]=-1`과 `dp[x]=NOT_SOLVABLE`은 각각 어떤 상태인가?
- [ ] 불가능 상태의 값을 정수 최댓값보다 1 작게 잡은 이유는 무엇인가?
- [ ] `f(x)`의 점화식에서 마지막 동전을 고정하면 왜 남은 금액도 최적으로 풀어야 하는가?
