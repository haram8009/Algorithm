---
schema_version: 1
platform: "Codetree"
title: "기울어진 직사각형"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-slanted-rectangle"
notion_page_url: "https://app.notion.com/p/3d03d9b37e1c81a09e09dbeaa71eb3e5"
legacy_title: "\\[코드트리\\] 기울어진 직사각형"
problem_id: ""
difficulty: "Hard"
topics: ["Matrix"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-03"
retry_needed: true
retry_at: ""
language: ["Java"]
complexity: "O(n\\^5) / O(n\\^2)  ※ 최적해는 대각선 누적합으로 O(n\\^4)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EA%B8%B0%EC%9A%B8%EC%96%B4%EC%A7%84%20%EC%A7%81%EC%82%AC%EA%B0%81%ED%98%95/slanted-rectangle.java"
---
## 문제 요약
n×n 격자에서 네 변이 모두 대각선인 **45° 기울어진 직사각형**의 테두리를 고른다. 각 변의 길이는 1 이상. 테두리 위 칸들의 합이 최대가 되는 값을 출력한다.
## 내 접근
**아래 꼭짓점 (i, j) + 두 변 길이 (k, kk)** 네 변수로 도형을 매개화했다. 이 4개가 정해지면 나머지 세 꼭짓점이 자동으로 결정된다.
- 아래 → (↗ kk칸) → 오른쪽 `(i-kk, j+kk)`
- → (↖ k칸) → 위 `(i-kk-k, j+kk-k)`
- → (↙ kk칸) → 왼쪽 `(i-k, j-k)`
- → (↘ k칸) → 다시 아래 `(i, j)`
방향 벡터 `dr = {-1,-1,1,1}`, `dc = {1,-1,-1,1}`를 순서대로 돌면서 `d % 2 == 0 ? kk : k`칸씩 이동하고, **이동한 뒤에** 그 칸을 더한다. 마지막 스텝이 시작점으로 돌아오므로 시작점도 정확히 한 번 더해진다 — 테두리 `2(k+kk)`칸이 중복·누락 없이 딱 한 번씩 세어진다.
격자 밖으로 나가지 않을 조건은 네 꼭짓점에서 바로 나온다:
```plain text
i - kk - k >= 0      →  i >= k + kk        (위 꼭짓점 행)
j - k     >= 0       →  j >= k             (왼쪽 꼭짓점 열)
j + kk    <= n-1     →  j <  n - kk        (오른쪽 꼭짓점 열)
```
코드의 `for (i = k+kk; i < n; i++)`, `for (j = k; j < n-kk; j++)`가 정확히 이 세 부등식이다.
## 막힌 지점
## 배운 것 / 패턴
**1. 이 문제의 난이도는 알고리즘이 아니라 매개화에 있다.** 꼭짓점 4개를 각각 좌표로 잡으려 하면 변수 8개에 "네 변이 대각선이고 마주보는 변의 길이가 같다"는 제약을 얹어야 해서 조건이 폭발한다. **한 꼭짓점 + 두 변 길이**로 잡으면 변수가 4개로 줄고, 나머지 세 꼭짓점이 각각 한 방향으로만 밀리기 때문에 경계 조건이 서로 독립적인 부등식 3개로 깔끔하게 분리된다. "기울어진 도형"류에서 좌표계를 회전(`u=r+c, v=r-c`)시키는 트릭도 있지만, 여기선 회전 없이 방향 벡터만으로 충분했다.
**2. "이동한 뒤 더한다"는 관용구.** 시작점을 미리 더하고 순회하면 마지막에 시작점이 두 번 세어져 빼줘야 하고, 꼭짓점 4개를 각각 특별 처리하려 하면 조건문이 늘어난다. 시작점을 더하지 않고 출발해 네 변을 각각 `길이`칸씩 도는 방식은 **모든 꼭짓점이 자기 앞 변의 마지막 칸으로 자동 처리**된다. 격자 순환 순회 전반에 그대로 쓰이는 패턴.
**3. 복잡도는 O(n\^5)로, 최적해보다 한 단계 높다.** `k, kk, i, j` 4중 루프가 O(n\^4)이고 테두리 순회가 매번 O(k+kk) = O(n). 후보 도형 수 자체가 O(n\^4)이니 루프는 줄일 수 없지만, **테두리 합을 O(1)로 만들면 O(n\^4)**가 된다. 네 변이 모두 대각선이므로 **대각선 방향 누적합 2종**만 미리 만들면 된다:
```java
// 1-indexed, 패딩 (n+2)x(n+2)
int[][] SE = new int[n+2][n+2];   // ↘ 방향: SE[r][c] = g[r][c] + SE[r-1][c-1]
int[][] NE = new int[n+2][n+2];   // ↗ 방향: NE[r][c] = g[r][c] + NE[r+1][c-1]
for (int r = 1; r <= n; r++)
    for (int c = 1; c <= n; c++)
        SE[r][c] = g[r][c] + SE[r-1][c-1];
for (int r = n; r >= 1; r--)
    for (int c = 1; c <= n; c++)
        NE[r][c] = g[r][c] + NE[r+1][c-1];

int max = 0;
for (int k = 1; k < n; k++)
  for (int kk = 1; kk < n; kk++)
    for (int i = k + kk + 1; i <= n; i++)
      for (int j = k + 1; j + kk <= n; j++) {
          int sum = (NE[i-kk][j+kk]         - NE[i][j])                  // ↗ kk칸
                  + (SE[i-kk-1][j+kk-1]     - SE[i-kk-k-1][j+kk-k-1])    // ↖ k칸
                  + (NE[i-kk-k+1][j+kk-k-1] - NE[i-k+1][j-k-1])          // ↙ kk칸
                  + (SE[i][j]               - SE[i-k][j-k]);             // ↘ k칸
          max = Math.max(max, sum);
      }
```
각 항은 "대각선 위 한 구간의 합 = 끝 누적 − 시작 직전 누적"이라는 1차원 prefix sum 그대로다. 2D prefix sum이 가로/세로 축에 대해 하는 일을 **두 대각선 축에 대해** 한 것.
**4. 연결되는 지점.** 대각선 누적합은 이후 대각선 방향 탐색이 나오는 문제(비숍 이동, 대각선 DP)에서 계속 재활용된다. 그리고 이 문제의 매개화 감각은 trail4의 격자 완전탐색 → 회전/이동 시뮬레이션으로 넘어갈 때 "도형을 어떤 최소 변수 집합으로 표현할까"라는 같은 질문으로 반복된다.
## 다시 볼 때 체크할 것
- [ ] `i >= k+kk`, `k <= j < n-kk` 세 부등식을 코드를 보지 않고 네 꼭짓점 좌표에서 다시 도출할 수 있는가?
- [ ] 시작점을 미리 더하지 않고 "이동 후 더하기"로 돌면 테두리가 정확히 `2(k+kk)`칸 한 번씩 세어지는 이유를 설명할 수 있는가?
- [ ] 대각선 누적합 2종(↘, ↗)을 직접 정의하고 네 변의 합 공식을 손으로 쓸 수 있는가? O(n\^5)와 O(n\^4)의 차이가 n=100에서 얼마나 되는가?
