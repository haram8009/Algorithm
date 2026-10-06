---
schema_version: 1
platform: "Codetree"
title: "정수 명령 처리 8"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-process-numeric-commands-8"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c811e956fc6436f83ea5d"
legacy_title: "\\[코드트리\\] 정수 명령 처리 8"
problem_id: ""
difficulty: "Easy"
topics: ["Linked List"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-01"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N) / O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail3/%EC%A0%95%EC%88%98%20%EB%AA%85%EB%A0%B9%20%EC%B2%98%EB%A6%AC%208/process-numeric-commands-8.java"
---
## 문제 요약
- 명령 N개를 순서대로 처리하는 덱(Deque) 문제. 명령은 `push_front X`, `push_back X`, `pop_front`, `pop_back`, `size`, `empty`, `front`, `back`
- 값을 돌려주는 명령은 그 결과를 출력하고, `empty`는 비었으면 1, 아니면 0
- 명령 형식은 코드를 보고 정리했으므로 원문과 대조 필요 (Trail 3 / 연결 리스트 / Doubly-LinkedList, 난이도 쉬움)
## 내 접근
- `LinkedList<Integer>`를 덱으로 사용: `addFirst`, `addLast`, `pollFirst`, `pollLast`, `peekFirst`, `peekLast`
- 명령 문자열을 `if-else`로 분기해 처리
```java
import java.util.Scanner;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList<Integer> ll = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String command = sc.next();

            int num;
            // num = sc.nextInt();
            // Please write your code here.
            if (command.equals("push_front")) {
                num = sc.nextInt();
                // System.out.println("add" + num);
                ll.addFirst(num);
            } else if (command.equals("push_back")) {
                num = sc.nextInt();
                // System.out.println("add" + num);
                ll.addLast(num);
            } else if (command.equals("pop_front")) {
                System.out.println(ll.pollFirst());
            } else if (command.equals("pop_back")) {
                System.out.println(ll.pollLast());
            } else if (command.equals("size")) {
                System.out.println(ll.size());
            } else if (command.equals("empty")) {
                if (ll.isEmpty()) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (command.equals("front")) {
                System.out.println(ll.peekFirst());
            } else if (command.equals("back")) {
                System.out.println(ll.peekLast());
            }
        }
    }
}
```
## 막힌 지점
## 배운 것 / 패턴
- **패턴**: 양끝에서 넣고 빼는 자료구조는 덱. Java에서는 `LinkedList`와 `ArrayDeque`가 `Deque`를 구현한다
- **주의**: `pollFirst`, `peekFirst`는 빈 덱에서 null을 반환해 `println`이 "null"을 출력한다. 이 풀이는 빈 덱에 대한 연산이 입력에 없다고 가정한 것이다. 빈 경우 다른 값을 출력해야 하는 변형이면 분기가 필요
- **비교**: `removeFirst`는 빈 덱에서 예외, `pollFirst`는 null. 시험에서는 `ArrayDeque`가 더 빠르지만 null 원소를 넣을 수 없다
- **입출력**: 출력이 많으면 `println` 반복 대신 `StringBuilder`에 모아 한 번에 출력
## 다시 볼 때 체크할 것
- [ ] `removeFirst`와 `pollFirst`의 차이를 설명할 수 있는가?
- [ ] 빈 덱에서 `front`가 들어오면 어떻게 처리해야 하는지 말할 수 있는가?
- [ ] `ArrayDeque`로 바꿔 다시 짤 수 있는가?
