---
schema_version: 1
platform: "Codetree"
title: "K개 중에 1개를 N번 뽑기"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-n-permutations-of-k-with-repetition"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c81e08d2cc531934ade01"
legacy_title: "\\[코드트리\\] K개 중에 1개를 N번 뽑기"
problem_id: ""
difficulty: "Easy"
topics: ["Backtracking"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-09"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N * K\\^N) / O(N * K\\^N)  ※ 출력 버퍼 포함, 재귀 스택만 O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/K%EA%B0%9C%20%EC%A4%91%EC%97%90%201%EA%B0%9C%EB%A5%BC%20N%EB%B2%88%20%EB%BD%91%EA%B8%B0/n-permutations-of-k-with-repetition.java"
---
## 문제 요약
- 1부터 K까지의 수 중 하나를 N번 고르는 모든 수열(같은 수 중복 허용)을 사전순으로 출력
- 입력 순서(K, N)와 출력 형식은 코드를 보고 정리했으므로 원문과 대조 필요 (Trail 4 / 백트래킹, 난이도 쉬움)
## 내 접근
- 길이 N 배열을 두고 `depth`번째 자리에 1부터 K까지 차례로 넣으며 재귀
- `depth == N`이 되면 배열을 한 줄로 `StringBuilder`에 기록
```java
import java.util.Scanner;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        // Please write your code here.
        int[] arr = new int[n];

        // 수열 사전순 출력
        permutation(arr, 0, k, n);
        System.out.println(sb);
    }

    static void permutation(int[] arr, int depth, int k, int n) {
        if (depth == n) {
            for (int i : arr) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        for (int i = 1; i <= k; i++) {
            arr[depth]=i;
            permutation(arr, depth+1, k, n);
        }
    }
}
```
## 막힌 지점
## 배운 것 / 패턴
- **패턴**: 백트래킹의 기본 틀. 한 자리를 고르고, 재귀로 다음 자리를 채우고, 돌아와서 다음 후보를 시도한다
- **사전순**: 각 자리에서 작은 값부터 시도하면 출력이 자동으로 사전순이 된다
- **되돌리기 생략**: 같은 자리를 다음 값으로 덮어쓰므로 별도의 undo가 필요 없다. 방문 표시가 필요한 순열 문제와 다른 점
- **출력량**: 결과가 K의 N제곱 줄이라 `println` 반복은 느리다. `StringBuilder`로 모아 한 번에 출력
- **연결**: 아름다운 수, 강력한 폭발 같은 완전 탐색 문제의 뼈대가 되는 형태
## 다시 볼 때 체크할 것
- [ ] 이 재귀의 호출 트리를 K=2, N=3으로 직접 그려볼 수 있는가?
- [ ] 중복을 허용하지 않는 순열이라면 무엇을 추가해야 하는지 말할 수 있는가?
- [ ] 왜 `arr[depth]`를 되돌리지 않아도 되는지 설명할 수 있는가?
