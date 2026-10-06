---
schema_version: 1
platform: "Codetree"
title: "회의실 준비 구현"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-implement-scheduling-meeting-room"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c81859e9bdc060b0f0ad5"
legacy_title: "\\[코드트리\\] 회의실 준비 구현"
problem_id: ""
difficulty: "Easy"
topics: ["Greedy"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-09-29"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N log N) / O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail5/%ED%9A%8C%EC%9D%98%EC%8B%A4%20%EC%A4%80%EB%B9%84%20%EA%B5%AC%ED%98%84/implement-scheduling-meeting-room.java"
---
## 문제 요약
- N개의 회의(시작 시간, 끝 시간)가 주어질 때, 서로 겹치지 않게 잡을 수 있는 회의의 최대 개수를 출력
- 입출력 형식은 코드를 보고 정리했으므로 원문과 대조 필요 (Trail 5 / 그리디, 난이도 쉬움)
## 내 접근
- 끝나는 시간 오름차순으로 정렬하고, 끝 시간이 같으면 시작 시간 오름차순
- 마지막으로 선택한 회의의 끝 시간 `lastTime`보다 같거나 늦게 시작하는 회의를 만나면 선택하고 `lastTime`을 갱신
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int [][] arr = new int[n][2];
        int[] s = new int[n];
        int[] e = new int[n];

        for (int i = 0; i < n; i++) {
            s[i] = sc.nextInt();
            e[i] = sc.nextInt();

            arr[i][0] = s[i];
            arr[i][1] = e[i];
        }
        // Please write your code here.

        Arrays.sort(arr, (a,b)->{
            if (a[1] == b[1])
                return a[0] - b[0];

            return a[1] - b[1];
            });

        int lastTime=0;
        int answer=0;
        for(int i=0; i <n; i++){
            // System.out.println(Arrays.toString(arr[i]));
            if(arr[i][0]>=lastTime){
                answer++;
                lastTime = arr[i][1];
            }
        }

        System.out.println(answer);
    }
}
```
## 막힌 지점
## 배운 것 / 패턴
- **패턴**: 활동 선택 문제. 끝나는 시간이 가장 빠른 것을 고르면 남는 시간이 최대가 된다는 그리디
- **다른 기준의 반례**: 시작이 빠른 순으로 고르면 (1,10), (2,3), (4,5)에서 (1,10) 하나만 고르게 되어 틀린다. 짧은 회의 순도 (1,5), (4,6), (5,9)처럼 중간 회의가 양쪽을 막는 경우에 틀린다
- **동률 처리**: 끝 시간이 같으면 시작이 빠른 것을 먼저 둔다. 길이 0인 회의가 섞이면 (1,3), (3,3)에서 (1,3)을 먼저 골라야 둘 다 선택된다
- **정리할 점**: 배열 `s`, `e`는 `arr`와 중복이라 지워도 된다. `lastTime`이 0이므로 시작 시간이 0인 회의도 선택 가능
## 다시 볼 때 체크할 것
- [ ] 끝나는 시간 순이 최적인 이유를 교환 논증으로 설명할 수 있는가?
- [ ] 시작 시간 순과 짧은 회의 순이 틀리는 반례를 직접 만들 수 있는가?
- [ ] 끝 시간이 같을 때 시작 시간 정렬이 필요한 입력을 만들 수 있는가?
<empty-block/>
