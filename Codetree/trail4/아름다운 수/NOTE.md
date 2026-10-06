---
schema_version: 1
platform: "Codetree"
title: "아름다운 수"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-beautiful-number"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c819a9304d0f7bc883ac0"
legacy_title: "\\[코드트리\\] 아름다운 수"
problem_id: ""
difficulty: "Easy"
topics: ["Backtracking"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-09"
retry_needed: true
retry_at: ""
language: ["Java"]
complexity: "O(N \\* 4\\^N) / O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail4/%EC%95%84%EB%A6%84%EB%8B%A4%EC%9A%B4%20%EC%88%98/beautiful-number.java"
---
## 문제 요약
- 1부터 4까지의 숫자로 이루어진 길이 N의 수 중, 숫자 x가 연속으로 정확히 x개씩 묶여 나타나는 수(아름다운 수)의 개수를 출력
- 정의는 코드의 검사 로직(`isBeutiful`)을 보고 정리했으므로 원문과 대조 필요 (Trail 4 / 백트래킹, 난이도 쉬움)
## 내 접근
- 중복 순열로 길이 N의 모든 수열(4\^N개)을 만든 뒤 하나씩 검사
- 검사: 위치 i의 숫자 target이 target개 연속되는지 확인하고, 통과하면 `i += target`으로 다음 묶음으로 이동. 중간에 값이 다르거나 배열이 끝나면 실패
```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        int[] arr = new int[n];
        cnt = 0;

        // 중복순열
        permutation(arr, 0, 4, n);
        System.out.println(cnt);
    }

    static void permutation(int[] arr, int depth, int k, int n) {
        if (depth == n) {
            if (isBeutiful(arr)) {
//                System.out.println(Arrays.toString(arr));

                cnt++;
            }
            return;
        }

        for (int i = 1; i <= k; i++) {
            arr[depth] = i;
            permutation(arr, depth + 1, k, n);
        }
    }

    static boolean isBeutiful(int[] arr) {
//        System.out.println(Arrays.toString(arr));
        int i = 0;
        while (i < arr.length) {
            int target = arr[i];
            int j = i;
            for (int k = 0; k < target; k++, j++) {
                if (j >= arr.length)
                    return false;
                else if (arr[j] != target)
                    return false;
            }
            i += target;
        }
        return true;
    }
}
```
## 막힌 지점
## 배운 것 / 패턴
- **패턴**: 생성 후 검사(generate and test). 구현이 쉽지만 N이 커지면 4\^N 때문에 느려진다
- **더 나은 풀이**: 숫자 하나씩이 아니라 묶음 단위로 재귀한다. 현재 길이를 `len`이라 할 때 x = 1..4 중 `len + x <= N`인 x를 골라 `len + x`로 넘어가면, 처음부터 조건을 만족하는 수만 만들어지므로 검사 함수가 필요 없다
- **이 문제에서는**: N이 작아 완전 탐색이 통과했지만, 같은 유형에서 제한이 커지면 가지치기가 필수
- **이름**: `isBeutiful`은 `isBeautiful`의 오타. 동작에는 영향 없지만 고치는 편이 좋다
## 다시 볼 때 체크할 것
- [ ] 묶음 단위 재귀로 검사 함수 없이 다시 풀 수 있는가?
- [ ] N=4일 때 아름다운 수를 직접 나열해 개수를 확인할 수 있는가?
- [ ] 완전 탐색이 통과하는 N의 한계를 대략 계산할 수 있는가?
