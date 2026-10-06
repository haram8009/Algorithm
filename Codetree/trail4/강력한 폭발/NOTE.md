---
schema_version: 1
platform: "Codetree"
title: "강력한 폭발"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-strong-explosion"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c8168bfdef641f605472c"
legacy_title: "\\[코드트리\\] 강력한 폭발"
problem_id: ""
difficulty: "Medium"
topics: ["Backtracking","Matrix"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-10"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(5 \\* 3\\^M) / O(N\\^2)  ※ M은 폭탄 수, 가지치기로 실제로는 훨씬 적음"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EA%B0%95%EB%A0%A5%ED%95%9C%20%ED%8F%AD%EB%B0%9C/strong-explosion.java"
---
## 문제 요약
- N x N 격자에서 값이 1인 칸마다 폭탄이 있고, 각 폭탄은 세 가지 모양(세로 5칸, 십자 5칸, X자 5칸) 중 하나로 터진다. 자기 자신을 포함하며 격자 밖은 무시
- 폭발한 서로 다른 칸 수가 가장 많아지게 모양을 골랐을 때의 최댓값을 출력
- 모양과 입력 형식은 코드의 방향 배열을 보고 정리했으므로 원문과 대조 필요 (Trail 4 / 백트래킹, 난이도 보통)
## 내 접근
- 폭탄 좌표를 `bombs`에 모으고, 폭탄마다 3가지 모양을 시도하는 DFS
- `cnt[r][c]`는 그 칸을 덮고 있는 폭탄 수. 설치할 때 0에서 1이 되는 칸만 새로 덮인 칸으로 세고, 해제할 때 1에서 0이 되는 칸을 되돌림
- 가지치기: 남은 폭탄이 전부 새 칸 5개씩 덮어도 현재 최댓값을 넘지 못하면 중단
```java
import java.io.*;
import java.util.*;

public class Main {
    // 자기 자신 포함 5칸 (세로 / 십자 / X자)
    static final int[][] DR = {
        {0, -2, -1,  1,  2},
        {0, -1,  1,  0,  0},
        {0, -1, -1,  1,  1}
    };
    static final int[][] DC = {
        {0,  0,  0,  0,  0},
        {0,  0,  0, -1,  1},
        {0, -1,  1, -1,  1}
    };

    static int n, m, answer;
    static int[][] cnt;    // 각 칸을 덮고 있는 폭탄 수
    static int[][] bombs;  // 폭탄 좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        cnt = new int[n][n];
        bombs = new int[n * n][2];
        m = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    bombs[m][0] = i;
                    bombs[m][1] = j;
                    m++;
                }
            }
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int depth, int covered) {
        if (depth == m) {
            if (covered > answer) answer = covered;
            return;
        }
        // 남은 폭탄이 전부 새 칸 5개씩 덮어도 갱신 불가면 컷
        if (covered + (m - depth) * 5 <= answer) return;

        for (int k = 0; k < 3; k++) {
            int added = apply(k, depth, 1);
            dfs(depth + 1, covered + added);
            apply(k, depth, -1);
        }
    }

    /** sign=1 설치, -1 해제. 새로 덮인(해제된) 칸 수 반환 */
    static int apply(int k, int idx, int sign) {
        int r = bombs[idx][0], c = bombs[idx][1];
        int delta = 0;
        for (int d = 0; d < 5; d++) {
            int nr = r + DR[k][d];
            int nc = c + DC[k][d];
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
            if (sign == 1) { if (cnt[nr][nc]++ == 0) delta++; }
            else           { if (--cnt[nr][nc] == 0) delta--; }
        }
        return delta;
    }
}
```
## 막힌 지점
## 배운 것 / 패턴
- **패턴**: 선택의 개수가 정해진 백트래킹(폭탄마다 3가지). 겹치는 영역의 합집합 크기를 구하는 부분이 핵심
- **카운트 배열**: 매번 visited를 초기화하고 전체를 세면 O(N\^2)가 든다. 칸별 덮은 횟수를 증감하면 폭탄 하나당 5칸만 보면 되고, 해제는 설치의 정확한 역연산이라 undo가 깔끔하다
- **방향 배열**: 모양 3종을 `DR`, `DC` 상수 표로 만들어 분기문이 사라졌다
- **가지치기**: 남은 폭탄이 얻을 수 있는 최대치(전부 5칸씩 새로 덮는 낙관적 상한)를 현재 답과 비교해 잘라낸다. 상한이 느슨해도 효과가 있다
- **주의**: `answer`를 정적 필드 기본값 0에 의존한다. 폭탄이 하나도 없으면 0이 출력된다
## 다시 볼 때 체크할 것
- [ ] `apply`의 설치와 해제가 왜 정확히 서로의 역연산인지 설명할 수 있는가?
- [ ] 가지치기 조건이 정답을 놓치지 않는 이유(낙관적 상한)를 말할 수 있는가?
- [ ] 방향 배열 없이 세 모양을 분기문으로 쓰면 어떻게 달라지는지 비교할 수 있는가?
