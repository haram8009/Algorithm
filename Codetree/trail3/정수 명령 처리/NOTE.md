---
schema_version: 1
platform: "Codetree"
title: "정수 명령 처리"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-process-numeric-commands"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c817c8e46d02f6c49805d"
legacy_title: "\\[코드트리\\] 정수 명령 처리"
problem_id: ""
difficulty: "Easy"
topics: ["Stack"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-07"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N) / O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail3/%EC%A0%95%EC%88%98%20%EB%AA%85%EB%A0%B9%20%EC%B2%98%EB%A6%AC/process-numeric-commands.java"
---
## 문제 요약
- 명령 N개를 순서대로 처리하는 스택 문제. 명령은 `push X`, `pop`, `size`, `empty`, `top`
- `pop`과 `top`은 꺼낸 값, `size`는 크기, `empty`는 비었으면 1 아니면 0을 출력
- 명령 형식은 코드를 보고 정리했으므로 원문과 대조 필요 (Trail 3 / 스택, 난이도 쉬움)
## 내 접근
- `Stack<Integer>`와 `switch` 문으로 명령을 분기
- 입력은 `BufferedReader`와 `StringTokenizer`, 출력은 `StringBuilder`에 모아 마지막에 한 번에 출력
```java
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Stack<Integer> stack = new Stack<>();
        
        StringBuilder sb = new StringBuilder();

        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
            case "push":
                stack.push(Integer.parseInt(st.nextToken()));
                break;
            case "size":
                sb.append(stack.size()).append("\n");
                break;
            case "empty":
                sb.append(stack.isEmpty()?1:0).append("\n");
                break;
            case "pop":
                sb.append(stack.pop()).append("\n");
                break;
            case "top":
                sb.append(stack.peek()).append("\n");
                break;
            }
        }

        System.out.println(sb);
    }
}
```
## 막힌 지점
## 배운 것 / 패턴
- **연결**: 앞서 푼 정수 명령 처리 8(덱)과 명령 처리 구조가 같다. 자료구조만 바뀐다
- **Stack 클래스**: Java의 `Stack`은 `Vector` 기반이라 동기화 비용이 있다. 성능이 중요하면 `ArrayDeque`를 스택처럼 쓰는 편이 일반적
- **예외**: 빈 스택에서 `pop`, `peek`을 호출하면 `EmptyStackException`이 발생한다. 이 풀이는 입력이 유효하다고 가정한 것
- **출력**: `System.out.println(sb)`는 마지막에 빈 줄이 하나 더 붙는다. 보통은 허용되지만 엄격한 채점에서는 `print`가 안전
## 다시 볼 때 체크할 것
- [ ] `Stack`과 `ArrayDeque`를 스택으로 쓸 때의 차이를 설명할 수 있는가?
- [ ] 빈 스택에서 `pop`, `top`이 나올 때 처리 방식을 말할 수 있는가?
- [ ] 같은 명령을 큐로 바꾸면 무엇이 달라지는지 말할 수 있는가?
