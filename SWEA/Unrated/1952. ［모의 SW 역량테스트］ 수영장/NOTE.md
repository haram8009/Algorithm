---
schema_version: 1
platform: "SWEA"
title: "수영장"
problem_url: "https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq"
notion_page_url: ""
legacy_title: ""
problem_id: "1952"
difficulty: "Unrated"
topics: ["Dynamic Programming","Minimum Cost"]
study_group: ""
result: ""
solved_at: ""
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(T × 12) / O(12)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/SWEA/Unrated/1952.%E2%80%85%EF%BC%BB%EB%AA%A8%EC%9D%98%E2%80%85SW%E2%80%85%EC%97%AD%EB%9F%89%ED%85%8C%EC%8A%A4%ED%8A%B8%EF%BC%BD%E2%80%85%EC%88%98%EC%98%81%EC%9E%A5/%EF%BC%BB%EB%AA%A8%EC%9D%98%E2%80%85SW%E2%80%85%EC%97%AD%EB%9F%89%ED%85%8C%EC%8A%A4%ED%8A%B8%EF%BC%BD%E2%80%85%EC%88%98%EC%98%81%EC%9E%A5.txt"
---

## 문제 요약

코드는 테스트케이스마다 이용권 네 종류의 가격(1일, 1개월, 3개월, 1년)과 1월부터 12월까지의 이용 계획을 입력받아 연간 최소 비용을 출력한다. README에는 문제 링크와 제출 정보가 있으나 상세 제한이나 채점 성공 문구는 없다. 따라서 노트의 결과와 최초 통과 날짜는 확정하지 않는다.

## 코드의 접근

`dp[i]`는 1월부터 i월까지의 계획을 처리하는 최소 비용이다. `dp[0]=0`에서 시작해 매달 두 선택을 비교한다. 해당 달 이용 일수에 1일권 가격을 곱한 값과 1개월권 가격 중 작은 값을 `dp[i-1]`에 더한다. i가 3 이상이면 `dp[i-3]+3개월권 가격`도 후보에 넣는다. 12월까지 계산한 뒤 `dp[12]`와 1년권 가격 중 작은 값을 출력한다.

## 핵심 원리

i월까지의 마지막 구매 결정을 기준으로 경우를 나누면, 이번 달을 일일권 또는 월권으로 해결하는 경우는 앞선 i-1개월의 최적 비용에 이어진다. 마지막 3개월을 3개월권 하나로 처리하는 경우는 앞선 i-3개월의 최적 비용에 이어진다. 각 경우의 이전 기간은 이미 최적으로 계산되어 있으므로 작은 값을 고르면 된다. 1년권은 전체 기간을 한 번에 덮으므로 마지막에 별도로 비교한다.

## 커밋에서 확인한 변경과 주의점

확인된 기간에는 README와 Java 내용의 `.txt` 파일이 한 번 추가됐고, 같은 문제의 후속 코드 변경은 확인되지 않는다. 복습할 때는 `plans[1..12]`와 `dp[0..12]`의 인덱스가 한 달씩 대응하는 점, 3개월권 후보는 i가 3 이상일 때만 비교하는 점, 연간권은 마지막에 비교하는 점을 확인한다. 저장소의 채점 성공 표시가 명확하지 않아 성능 수치를 통과 근거로 해석하지 않았다.

## 복잡도

T는 테스트케이스 수다. 각 테스트케이스에서 12개월을 순서대로 한 번씩 계산하므로 시간 O(T×12), 테스트케이스 하나의 가격·계획·DP 배열은 O(12) 공간이다.

## 한 줄 인사이트

기간별 최소 비용은 마지막 이용권이 덮는 기간을 떼어 내고 그 이전 기간의 최적 비용을 더해 계산한다.

## 복습 질문

- [ ] `dp[i-3]`에 3개월권 가격을 더하면 어떤 달들을 한 번에 덮는가?
- [ ] 이용 계획이 0인 달의 일일권 후보 비용은 얼마인가?
- [ ] 1년권을 월별 점화식 밖에서 마지막에 비교해도 되는 이유는 무엇인가?
