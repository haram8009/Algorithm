---
schema_version: 1
platform: "Codetree"
title: "황금비율 토스트"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-golden-toast"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c81fa9275d14d96db89b4"
legacy_title: "\\[코드트리\\] 황금비율 토스트"
problem_id: ""
difficulty: "Easy"
topics: ["Linked List"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-02"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N + M) / O(N + M)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail3/%ED%99%A9%EA%B8%88%EB%B9%84%EC%9C%A8%20%ED%86%A0%EC%8A%A4%ED%8A%B8/golden-toast.java"
---
## 문제 요약
- 문자열 s와 명령 M개가 주어진다. 커서는 처음에 문자열 맨 뒤에 있다
- `L` 커서를 왼쪽으로, `R` 커서를 오른쪽으로, `D` 커서 오른쪽 문자 삭제, `P x` 커서 위치에 문자 x 삽입
- 모든 명령 후의 문자열을 출력. 명령 형식은 코드를 보고 정리했으므로 원문과 대조 필요 (Trail 3 / Iterator, 난이도 쉬움)
## 내 접근
- 문자들을 `LinkedList<Character>`에 넣고 `listIterator(l.size())`로 맨 뒤에서 시작
- `L`은 `previous()`, `R`은 `next()`, `P`는 `it.add(c)`, `D`는 `next()` 후 `remove()`
- 입력은 `BufferedReader`, 결과는 `StringBuilder`로 출력
```java
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String s = br.readLine();

        LinkedList<Character> l = new LinkedList<>();
        for (char c : s.toCharArray()) {
            l.add(c);
        }
        ListIterator<Character> it = l.listIterator(l.size());

        for (int i = 0; i < m; i++) {
            String command = br.readLine();
            switch (command.charAt(0)) {
            case 'L':
                if (it.hasPrevious())
                    it.previous();
                break;
            case 'P':
                it.add(command.charAt(2));
                break;
            case 'R':
                if (it.hasNext())
                    it.next();
                break;
            case 'D':
                if (it.hasNext()) {
                    it.next();
                    it.remove();
                }
                break;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (char c : l) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}
```
## 막힌 지점
## 배운 것 / 패턴
- **패턴**: 중간 삽입·삭제가 잦고 위치가 커서로 정해지면 연결 리스트와 `ListIterator`. 배열이면 삽입·삭제가 O(N)이라 시간 초과가 날 수 있다
- **ListIterator 규칙**: `add`는 커서 앞에 넣고 커서는 그 뒤에 남는다. `remove`는 직전에 `next`나 `previous`로 지나친 원소를 지우므로, `D`는 `next()`와 `remove()`를 한 쌍으로 써야 한다
- **경계**: `hasPrevious`, `hasNext`로 커서가 끝을 벗어나지 않게 막았다
- **연결**: 이중 연결 리스트 개념 문제들에서 배운 삽입·삭제를 Java 표준 라이브러리로 쓰는 형태
## 다시 볼 때 체크할 것
- [ ] `it.add` 직후 커서가 어디에 있는지 설명할 수 있는가?
- [ ] `remove()`를 `next()` 없이 부르면 어떻게 되는지 말할 수 있는가?
- [ ] `LinkedList`와 `ArrayList`로 풀었을 때 시간복잡도 차이를 비교할 수 있는가?
