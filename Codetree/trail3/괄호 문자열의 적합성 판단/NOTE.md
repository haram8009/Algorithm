---
schema_version: 1
platform: "Codetree"
title: "괄호 문자열의 적합성 판단"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-parentheses-string"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c81419fb4c849866052b4"
legacy_title: "\\[코드트리\\] 괄호 문자열의 적합성 판단"
problem_id: ""
difficulty: "Medium"
topics: ["Stack"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-07"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N) / O(N)  ※ 괄호 종류가 하나라 카운터로 O(1) 공간 가능"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail3/%EA%B4%84%ED%98%B8%20%EB%AC%B8%EC%9E%90%EC%97%B4%EC%9D%98%20%EC%A0%81%ED%95%A9%EC%84%B1%20%ED%8C%90%EB%8B%A8/parentheses-string.java"
---
## 문제 요약
- 괄호 문자열이 올바른 괄호열이면 Yes, 아니면 No를 출력
- 여는 괄호는 `(`, 닫는 괄호는 `)`만 나온다는 가정은 코드를 보고 정리했으므로 원문과 대조 필요 (Trail 3 / 스택, 난이도 보통)
## 내 접근
- `(`를 만나면 스택에 push, `)`를 만나면 pop
- 스택이 비어 있는데 `)`가 나오면 실패이므로 표식 문자를 push하고 반복을 끝냄
- 마지막에 스택이 비었으면 Yes, 아니면 No
```java
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        // Please write your code here.
        Stack<Character> s = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(') {
                s.push(c);
            } else if (c == ')') {
                // 비어있으면 No
                if (s.isEmpty()) {
//                    System.out.println("No");
                    s.push('n');
                    break;
                }
                // 안비어있으면 pop
                else {
                    s.pop();
                }
            }
        }
        // 안비어있으면 No
        System.out.println(s.isEmpty() ? "Yes" : "No");
    }
}
```
## 막힌 지점
## 배운 것 / 패턴
- **패턴**: 괄호 짝 맞추기는 스택의 대표 문제. 여는 괄호를 쌓고 닫는 괄호가 나올 때 꺼낸다
- **더 간단한 풀이**: 괄호 종류가 하나뿐이면 스택 대신 정수 `depth` 하나로 충분하다. `(`면 증가, `)`면 감소하고, 중간에 음수가 되면 바로 No, 끝에 0이면 Yes. 공간이 O(1)
- **스택이 필요한 경우**: `()`, `[]`, `{}`처럼 종류가 여러 개면 어떤 괄호가 열렸는지 기억해야 하므로 스택이 필요하다
- **코드 정리**: 실패 신호로 스택에 `'n'`을 넣는 방식은 읽기 어렵다. `boolean ok` 플래그를 쓰는 편이 의도가 분명하다
## 다시 볼 때 체크할 것
- [ ] 카운터 하나로 푸는 풀이를 직접 짤 수 있는가?
- [ ] 괄호 종류가 3가지로 늘어나면 풀이를 어떻게 바꿀지 말할 수 있는가?
- [ ] `())(` 같은 입력에서 어느 시점에 실패가 확정되는지 설명할 수 있는가?
