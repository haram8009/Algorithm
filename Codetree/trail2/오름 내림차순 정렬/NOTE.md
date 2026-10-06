---
schema_version: 1
platform: "Codetree"
title: "오름 내림차순 정렬"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-inc-dec-sorting"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c81a7b226c412a3258501"
legacy_title: "\\[코드트리\\] 오름 내림차순 정렬"
problem_id: ""
difficulty: "Easy"
topics: ["Array/String"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-13"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N log N) / O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail2/%EC%98%A4%EB%A6%84%20%EB%82%B4%EB%A6%BC%EC%B0%A8%EC%88%9C%20%EC%A0%95%EB%A0%AC/inc-dec-sorting.java"
---
## 문제 요약
- N개의 정수를 오름차순으로 한 줄, 내림차순으로 한 줄 출력
- 출력 형식은 코드를 보고 정리했으므로 원문과 대조 필요 (Trail 2 / 정렬 / 일반 정렬, 난이도 쉬움)
## 내 접근
- `Arrays.sort(arr)`로 오름차순 정렬
- 오름차순은 앞에서부터, 내림차순은 같은 배열을 뒤에서부터 출력
```java
import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.

        Arrays.sort(arr);

        for(int i=0; i<n; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        for(int i=n-1; i>=0; i--){
            System.out.print(arr[i]+" ");
        }

    }
}
```
## 막힌 지점
## 배운 것 / 패턴
- **패턴**: 한 번 정렬한 결과를 양방향으로 읽어 오름·내림 두 결과를 얻는다. 정렬을 두 번 할 필요가 없다
- **내림차순 정렬 함정**: 기본형 `int[]`는 `Arrays.sort(arr, Collections.reverseOrder())`를 쓸 수 없다. 이 방식은 `Integer[]`에서만 되므로, 기본형이면 뒤에서 읽는 편이 간단하다
- **복잡도**: 기본형 배열 정렬은 평균 O(N log N). 입력이 크면 `Scanner` 대신 `BufferedReader`가 안전
## 다시 볼 때 체크할 것
- [ ] `int[]`와 `Integer[]`에서 내림차순 정렬 방법이 다른 이유를 말할 수 있는가?
- [ ] 정렬 기준을 직접 지정하는 `Comparator` 사용법을 설명할 수 있는가?
