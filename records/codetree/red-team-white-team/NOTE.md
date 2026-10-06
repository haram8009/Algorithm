---
schema_version: 1
platform: "Codetree"
title: "레드팀 화이트팀"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/challenge-red-team-and-white-team"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c812084b5e37a97992d87"
legacy_title: "\\[코드트리\\] 레드팀 화이트팀"
problem_id: ""
difficulty: "Medium"
topics: ["BFS/DFS","Graph","UnionFind"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-10-01"
retry_needed: true
retry_at: ""
language: ["Java"]
complexity: "DFS: O(N+M) / O(N+M)  ※ Union-Find: O(M·α(N)) / O(N)"
time_minutes: ""
code_url: ""
---
## 문제 요약
N개 노드, M개 간선. 간선으로 이어진 두 노드는 서로 다른 팀이어야 하고, 두 팀으로 나눌 수 있으면 1, 없으면 0을 출력한다. 즉 **이분 그래프 판별**이다.
## 접근 1. DFS로 색 전파
- 미방문(0) 노드를 1로 칠하고 시작, 이웃은 반대 색(-1)으로 재귀 칠하기
- 이웃이 이미 칠해져 있는데 나와 **같은 색**이면 모순이므로 답 0
- 연결 요소가 여러 개일 수 있어서 모든 노드를 돌며 미방문 노드마다 시작
- 마킹과 모순 확인을 한 번의 DFS에서 같이 처리 (`else if (parents[next] == flag)`)
## 접근 2. Union-Find (힌트 보고 풀었음)
- `againsts[루트]` = 그 그룹과 반대 팀인 그룹의 노드
- 간선 (x, y)마다 아래 순서로 처리
	1. `find(x) == find(y)`이면 같은 팀으로 묶여 있다는 뜻이므로 모순, 답 0
	2. x 그룹의 기존 적을 y 그룹에 union (적의 적은 내 편)
	3. y 그룹의 기존 적을 x 그룹에 union
	4. 서로를 적으로 기록
- 함정: 간선의 양 끝을 그대로 union하면 안 된다. union은 "같은 팀"이라는 뜻인데 간선은 "다른 팀"이라는 뜻이라 의미가 정반대가 된다.
- 대안: 노드를 2N개로 늘려 `union(a, b+n)`, `union(b, a+n)` 하고 `find(x) == find(x+n)`이면 모순
## 오답 노트 (처음 코드에서 막혔던 지점)
- `flag`를 0이면 1로 "찍기만" 하고 `parents[i]`에 저장하지 않았다
- 안쪽 for문이 `graph.keySet()` 전체를 돌아서 i와 무관한 노드까지 반대 색으로 칠했다. 이웃만 도려면 `graph.get(i)`
- 노드를 한 번씩 훑는 방식으로는 색이 이웃의 이웃까지 **전파**되지 않는다. 그래서 BFS/DFS가 필요하다
- 마킹과 확인을 분리한 버전에서 `check`가 `visited`를 모순 검사보다 먼저 봐서, 이미 방문한 노드로 가는 간선이 검사되지 않았다. 삼각형 `1-2, 2-3, 3-1`이 반례. 색 비교를 먼저 하거나, 간선마다 양 끝 색만 비교하면 된다
- 제출 코드에 남은 디버그용 `System.out.println`은 오답 처리의 원인이 된다
## 개선 포인트
- Union-Find 코드의 `System.out.println(parents[1] = answer);`는 부작용이 있는 식이라 `System.out.println(answer);`로 정리
- union by size/rank가 없어서 체인이 길어지면 재귀 `find`의 깊이가 커질 수 있다. 제한이 크면 반복문 find나 rank 추가
- DFS도 N이 크면 재귀 스택이 위험해서 BFS로 바꾸는 것을 고려
## 자기 점검 질문
- 삼각형 `1-2, 2-3, 3-1`에서 마킹만 하고 간선 비교를 안 하면 왜 모순을 놓칠까?
- Union-Find에서 `find(x) == find(y)`가 왜 곧바로 모순일까?
- 홀수 길이 사이클이 있으면 왜 두 팀으로 나눌 수 없을까?
- 연결 요소가 여러 개일 때 DFS 시작점은 어떻게 잡아야 할까?
## 코드 1. DFS 색 전파
```java
import java.util.*;

public class Main {
    static Map<Integer, ArrayList<Integer>> graph;
    static int[] parents;
    static int answer;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        parents = new int[n + 1];
        graph = new HashMap<>();

        int a, b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();

            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        answer = 1;

        // 1 vs -1
        // 미방문 노드이면 1로 시작
        for (int node : graph.keySet()) {
            if (parents[node] == 0) {
                mark(node, 1);
            }
        }
        System.out.println(answer);
    }

    static void mark(int node, int flag) {
        // 입력, 인접 노드 방문
        parents[node] = flag;
        int opp = flag * (-1);
        for (int next : graph.get(node)) {
            if (parents[next] == 0)
                mark(next, opp);
            else if (parents[next] == flag) {
                answer = 0;
                return;
            }
        }
    }
}
```
## 코드 2. Union-Find (againsts 배열)
```java
import java.util.*;

public class Main {
    static int[] parents;
    static int[] againsts;
    static int answer; // 모순이면 0

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        parents = new int[n + 1];
        againsts = new int[n + 1];
        answer = 1;

        for (int i = 0; i < n + 1; i++) {
            parents[i] = i;
        }

        int a, b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();

            beta(a, b);
        }

        System.out.println(answer);
    }

    static int find(int x) {
        if (parents[x] == x)
            return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b) {
        int A = find(a);
        int B = find(b);
        parents[A] = B;
    }

    static void beta(int x, int y) {
        int X = find(x);
        int Y = find(y);

        if (X == Y) {
            answer = 0;
            return;
        }

        if (againsts[X] != 0) {
            union(againsts[X], Y);
        }

        if (againsts[Y] != 0) {
            union(againsts[Y], X);
        }

        int XX = find(X);
        int YY = find(Y);

        againsts[XX] = Y;
        againsts[YY] = X;
    }
}
```
