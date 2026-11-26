---
schema_version: 1
platform: "Codetree"
title: "최대로 연속한 숫자"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-maximum-consecutive-number"
problem_id: "challenge-maximum-consecutive-number"
difficulty: "보통"
topics: ["TreeSet", "TreeMap", "구간 관리", "멀티셋"]
study_group: ""
result: "Passed"
solved_at: "2026-11-12"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "시간 O(M log M), 공간 O(M)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail5/최대로%20연속한%20숫자/maximum-consecutive-number.java"
---

## 한 줄 인사이트

숫자 하나를 지우면 그 숫자가 속한 연속 구간 하나만 두 구간으로 갈라지므로, 지운 위치와 구간 길이의 개수만 관리한다.

## 문제 요약

0부터 N까지의 정수에서 숫자를 하나씩 제거한다. 제거할 때마다 남아 있는 연속한 숫자 구간의 최대 길이를 출력한다.

## 두 자료구조의 역할

| 자료구조 | 저장하는 값 | 사용하는 연산 |
|---|---|---|
| TreeSet s | 제거한 숫자와 양쪽 경계 | lower(num), higher(num) |
| TreeMap tm | 구간 길이 → 해당 길이의 구간 개수 | 개수 증감, lastKey() |

초기에는 길이 N + 1의 구간 하나가 있다. TreeSet에는 경계인 -1과 N + 1을 넣고, TreeMap에는 N + 1의 개수를 1로 저장한다. 경계를 넣으면 양 끝 숫자를 제거할 때도 같은 공식을 쓸 수 있다.

## 숫자 하나를 제거할 때

제거할 숫자를 num, 그보다 작은 가장 가까운 제거 위치를 l, 큰 가장 가까운 제거 위치를 h라고 하자.

기존 구간은 l + 1부터 h - 1까지이다. num을 제거하면 다음처럼 바뀐다.

| 구간 | 길이 |
|---|---|
| 사라지는 기존 구간 | h - l - 1 |
| 새 왼쪽 구간 | num - l - 1 |
| 새 오른쪽 구간 | h - num - 1 |

코드는 먼저 s.add(num)을 호출한다. lower와 higher는 자기 자신을 제외한 엄격한 이전 값과 다음 값을 반환하므로 이 순서로도 이웃 경계를 찾는다.

1. 기존 길이의 구간 개수를 1 줄인다.
2. 개수가 0이면 키를 삭제한다.
3. 새 왼쪽·오른쪽 길이의 개수를 각각 1 늘린다.
4. tm.lastKey()로 가장 긴 구간 길이를 출력한다.

## 길이의 개수가 필요한 이유

서로 다른 구간의 길이가 같을 수 있다. 길이만 TreeSet에 넣으면 구간 하나가 사라질 때 같은 길이의 다른 구간까지 없어진 것으로 처리된다. TreeMap에 개수를 저장하면 같은 길이를 여러 번 보유하는 멀티셋처럼 사용할 수 있다.

두 새 구간의 길이가 같아도 두 번 증가시켜야 한다. 현재 코드는 왼쪽과 오른쪽을 순서대로 getOrDefault로 갱신하므로 이를 처리한다.

## 복잡도

제거 횟수를 M이라고 하면 각 단계에서 TreeSet과 TreeMap 연산을 상수 번 수행한다.

- 시간: O(M log M).
- 공간: O(M). 제거 위치와 구간 길이 정보가 필요하다. 사용하지 않는 removals 배열도 O(M)이다.

## 주의할 점

- 초기 숫자는 0부터 N까지이므로 초기 구간 길이는 N + 1이다.
- 개수가 0인 키를 삭제해야 lastKey()가 실제 구간의 최대 길이를 반환한다.
- 길이 0인 구간도 저장한다. 양옆에 숫자가 남지 않았음을 뜻하며 최대 길이 계산에는 문제없다.
- 같은 숫자가 중복 제거되지 않는 입력을 전제로 한다. 중복 입력을 허용한다면 이미 제거된 숫자인지 먼저 확인해야 한다.
