---
schema_version: 1
platform: "JUNGOL"
title: "전깃줄 - 2"
problem_url: "https://jungol.co.kr/problem/1257?cursor=MTAsOCw5"
problem_id: "1257"
difficulty: ""
topics: ["LIS", "이분 탐색", "경로 복원"]
study_group: ""
result: "통과"
solved_at: "2026-10-14"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "시간 O(N log N), 공간 O(N + 500000)"
time_minutes: ""
code_url: "전깃줄 - 2.java"
---

## 한 줄 인사이트

A 전봇대 기준으로 정렬하면 교차하지 않게 남길 전깃줄은 B 번호의 최장 증가 부분 수열(LIS)이며, 제거할 줄을 출력하려면 LIS 길이뿐 아니라 이전 원소 연결 정보도 저장해야 한다.

## 풀이

A 번호 오름차순으로 정렬하면 두 줄이 교차하지 않으려면 B 번호도 증가해야 한다. 따라서 남길 수 있는 최대 줄 수는 B 수열의 LIS 길이이고, 제거 개수는 `N - LIS 길이`다.

N이 최대 100,000이므로 각 원소의 위치를 이분 탐색으로 찾는 O(N log N) LIS를 사용한다. `lis[k]`에는 길이 k인 증가 수열의 가능한 최소 마지막 값이 들어간다. 이 배열은 LIS 길이를 구하는 용도이며 그 자체가 실제 LIS는 아니다.

실제 경로를 복원하기 위해 B 번호별로 직전 값을 `prev[num]`에 기록한다. 삽입할 때 `prev[num] = lis[newIdx - 1]`로 설정하고, 최장 길이의 마지막 값에서 `prev`를 따라가면 하나의 LIS를 얻는다. 그 LIS에 포함되지 않은 줄의 A 번호를 정렬해 출력한다.

## 주의할 점

- `idx`는 지금까지의 LIS 최장 길이이므로 `idx = Math.max(idx, newIdx)`로 갱신한다. 앞쪽 칸을 덮어쓴다고 최장 길이가 줄어들면 안 된다.
- `lis[]`를 뒤에서 훑어 실제 LIS를 복원할 수 없다. 길이별 최소 끝값들이 서로 다른 시점의 원소일 수 있으므로 `prev[]`가 필요하다.
- `lis[0] = -1`을 센티넬로 두면 첫 원소의 이전 값도 `-1`로 처리할 수 있다.
- 값이 중복될 수 있는 일반적인 LIS 문제라면 값 기준 대신 원소 인덱스 기준으로 이전 원소를 저장한다.

## 다시 볼 점

- `idx`가 단조 증가해야 하는 이유를 B 수열 `5, 6, 7, 1`로 설명할 수 있는가?
- `lis[]`가 실제 LIS가 아닌 이유와 `prev[]`가 필요한 이유를 설명할 수 있는가?
