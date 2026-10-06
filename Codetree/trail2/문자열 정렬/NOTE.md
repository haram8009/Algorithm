---
schema_version: 1
platform: "Codetree"
title: "문자열 정렬"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-string-sort"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c819382cbcb49bed7625c"
legacy_title: "\\[코드트리\\] 문자열 정렬"
problem_id: ""
difficulty: "Easy"
topics: ["Array/String"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-13"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(L log L) / O(L)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail2/%EB%AC%B8%EC%9E%90%EC%97%B4%20%EC%A0%95%EB%A0%AC/string-sort.java"
---
## 문제 요약
- 문자열이 주어지면 문자를 사전순(오름차순)으로 정렬한 문자열을 출력
- 입출력 형식은 코드를 보고 정리했으므로 원문과 대조 필요 (Trail 2 / 정렬 / 일반 정렬, 난이도 쉬움)
## 내 접근
- `toCharArray()`로 문자 배열을 만들고 `Arrays.sort`로 정렬한 뒤 한 글자씩 출력
```java
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        // Please write your code here.
        char[] chars = s.toCharArray();

        Arrays.sort(chars);

        for(char c:chars){
            System.out.print(c);
        }
    }
}
```
## 막힌 지점
## 배운 것 / 패턴
- **패턴**: Java의 `String`은 불변이라 직접 정렬할 수 없다. `char[]`로 바꿔 정렬한 뒤 `new String(chars)`로 되돌리는 것이 정석
- **카운팅 정렬**: 문자가 소문자 알파벳처럼 종류가 적으면 `int[26]`로 개수를 세서 O(L)에 정렬할 수 있다. 문자열이 길 때 유리
- **정렬 순서**: 문자 비교는 ASCII 순서다. 대문자가 소문자보다 앞에 오는 것에 주의
## 다시 볼 때 체크할 것
- [ ] `String`을 정렬하려면 왜 배열로 바꿔야 하는지 설명할 수 있는가?
- [ ] 카운팅 정렬로 같은 문제를 다시 풀 수 있는가?
- [ ] 대소문자가 섞인 입력에서 정렬 순서를 예측할 수 있는가?
