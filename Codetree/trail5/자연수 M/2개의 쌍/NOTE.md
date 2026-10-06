---
schema_version: 1
platform: "Codetree"
title: "자연수 M/2개의 쌍"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-m2-pairs-of-natural-numbers"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c8151abdec9941c954a41"
legacy_title: "\\[코드트리\\] 자연수 M/2개의 쌍"
problem_id: ""
difficulty: "Medium"
topics: ["Greedy","Two Pointers"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-17"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N log N) / O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail5/%EC%9E%90%EC%97%B0%EC%88%98%20M/2%EA%B0%9C%EC%9D%98%20%EC%8C%8D/m2-pairs-of-natural-numbers.java"
---
## 문제 요약
- 값 y가 x개씩 있는 (x, y) 묶음이 N개 주어진다. 전체 M개를 둘씩 M/2개의 쌍으로 묶을 때, 각 쌍의 합 중 최댓값을 가장 작게 만드는 값을 출력하는 문제로 추정
- 이 해석은 코드의 동작(값 오름차순 정렬, 양끝 매칭, 합의 최댓값 갱신)을 보고 정리했으므로 원문과 반드시 대조 필요 (Trail 5 / 그리디, 난이도 보통)
## 내 접근
- 값 y 기준으로 오름차순 정렬
- 투 포인터 `i`(작은 쪽), `j`(큰 쪽)로 양끝을 짝지음. 두 묶음의 합 `c`를 후보로 두고 답을 최댓값으로 갱신
- 개수가 적은 쪽을 모두 소진하고, 많은 쪽에는 차이만큼 남겨 포인터를 이동. 개수가 같으면 둘 다 이동
```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            arr[i] = new int[] { x, y };
        }

        Arrays.sort(arr, (a, b) -> {
            return a[1] - b[1];
        });

        int answer = 0;

        int i = 0, j = n - 1, c;
        while (i <= j && i < n && j >= 0) {
            int[] n1 = arr[i];
            int[] n2 = arr[j];
            c = n1[1] + n2[1];
            int sub = Math.abs(n1[0] - n2[0]);

            if (n1[0] > n2[0]) {
                n1[0] = sub;
                j--;
            } else if (n1[0] < n2[0]) {
                n2[0] = sub;
                i++;
            } else {
                i++;
                j--;
            }

            answer = c > answer ? c : answer;
        }

        System.out.println(answer);
    }
}
```
## 막힌 지점
## 배운 것 / 패턴
- **패턴**: 최댓값을 최소화하는 짝짓기는 정렬 후 가장 작은 것과 가장 큰 것을 짝지는 그리디. 큰 값이 작은 값과 만나야 합의 최댓값이 낮아진다는 교환 논증으로 정당화된다
- **개수 처리**: 원소를 하나씩 짝지으면 M이 클 때 느리다. 두 묶음 중 개수가 적은 쪽만큼을 한 번에 소진하고 차이만 남기면 포인터 이동이 N번 이하라 정렬이 병목(O(N log N))이 된다
- **같은 묶음끼리**: `i`와 `j`가 같은 묶음을 가리키면 개수가 같아 둘 다 이동하며 합은 2y로 계산된다
- **입력**: 제출 기록상 실행 시간이 1.7초로 길다. 입력이 크면 `Scanner` 대신 `BufferedReader`로 바꾸면 줄일 수 있다
## 다시 볼 때 체크할 것
- [ ] 작은 것과 큰 것을 짝짓는 방식이 최적인 이유를 교환 논증으로 설명할 수 있는가?
- [ ] 두 묶음의 개수가 같을 때 포인터를 둘 다 옮기는 이유를 말할 수 있는가?
- [ ] 입력 배열을 직접 수정하는 이 방식이 왜 안전한지 설명할 수 있는가?
