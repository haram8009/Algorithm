---
schema_version: 1
platform: "Codetree"
title: "처음 등장하는 위치"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-first-appearing-position"
problem_id: "first-appearing-position"
difficulty: "쉬움"
topics: ["TreeMap", "자료구조"]
study_group: ""
result: "Passed"
solved_at: "2026-11-12"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "시간 O(N log K), 공간 O(N + K)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail5/처음%20등장하는%20위치/first-appearing-position.java"
---

## 한 줄 인사이트

처음 본 숫자만 위치를 저장하고, 숫자 순서대로 출력해야 하므로 `TreeMap<숫자, 최초 위치>`를 사용한다.

## 접근

입력 수열을 왼쪽부터 확인한다. 현재 숫자가 맵에 없으면 `i + 1`을 저장하고, 이미 있으면 건너뛴다. 각 숫자의 위치는 최초 등장 시점에 한 번만 기록된다.

`TreeMap`은 키를 오름차순으로 유지하므로 `entrySet()`을 순회하면 별도 정렬 없이 숫자와 최초 위치를 출력할 수 있다.

## 코드 흐름

1. `numbers` 배열에 입력을 저장한다.
2. `containsKey(num)`으로 이전 등장 여부를 확인한다.
3. 처음 등장했다면 `tm.put(num, i + 1)`을 수행한다.
4. 맵의 엔트리를 순회하며 숫자와 위치를 출력한다.

## 복잡도

서로 다른 숫자 수를 K라 하면 조회와 삽입은 O(log K), 전체 시간은 O(N log K)이다. 출력은 O(K)이다. 현재 코드는 입력 배열 O(N)과 맵 O(K)을 사용한다.

## 주의할 점

- 위치는 1부터 시작하므로 배열 인덱스에 1을 더한다.
- 같은 숫자의 값을 덮어쓰면 마지막 위치가 되므로 기존 키는 건너뛴다.
- `HashMap`은 키 순서를 보장하지 않는다. 사용한다면 키를 따로 정렬해야 한다.
- 입력을 받으면서 바로 맵에 저장하면 배열을 없애고 공간을 O(K)로 줄일 수 있다.
