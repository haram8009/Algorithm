---
schema_version: 1
platform: "Codetree"
title: "집합의 원소"
problem_url: "https://www.codetree.ai/trails/complete/curated-cards/intro-elements-of-a-set"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c8166896bf5d0d4feb552"
legacy_title: "집합의 원소"
problem_id: ""
difficulty: "Easy"
topics: ["Graph"]
study_group: "선택"
result: "Accepted"
solved_at: "2026-10-01"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O((N + M) log N) / O(N)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail6/%EC%A7%91%ED%95%A9%EC%9D%98%20%EC%9B%90%EC%86%8C/elements-of-a-set.java"
---
## 문제 요약
- 원소 1\~N이 각각 독립된 집합에서 시작, M개의 쿼리를 순서대로 처리
- `0 a b`: a가 속한 집합과 b가 속한 집합을 합친다
- `1 a b`: a와 b가 같은 집합이면 1, 아니면 0을 출력
- 유니온 파인드(Disjoint Set) 기초 개념 문제 (Trail 6 / MST / Disjoint Set, 난이도 쉬움). 쿼리 형식은 코드를 보고 정리했으므로 원문과 대조 필요
## 내 접근
- `uf[i] = i`로 초기화해 각 원소가 자기 자신을 대표로 가지게 함
- `find(x)`: 대표를 찾으면서 만나는 모든 노드의 부모를 대표로 바꾸는 **경로 압축**
- `union(a, b)`: 두 대표를 구해 다르면 `uf[A] = B`로 연결, 같으면 아무것도 하지 않음
- 조회 쿼리는 두 대표가 같은지 비교해 1 또는 0 출력
```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] uf;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        uf = new int[n + 1];
        for (int i = 0; i < uf.length; i++) {
            uf[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int qType = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            // Please write your code here.
            if (qType == 0) {
                union(a, b);
            } else {
                int A = find(a);
                int B = find(b);

                System.out.println(A == B ? 1 : 0);
            }
        }
    }

    public static int find(int x) {
        if (x == uf[x])
            return x;
        return uf[x] = find(uf[x]);
    }

    public static void union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if (A == B) {
            return;
        }

        uf[A] = B;
    }
}
```
## 막힌 지점
## 배운 것 / 패턴
- **패턴**: Union-Find의 두 연산 `find`(대표 찾기)와 `union`(대표끼리 연결). 연결 여부만 묻는 문제에서는 BFS/DFS보다 간단하고, 간선이 하나씩 들어오는 온라인 형태에 특히 강하다
- **복잡도**: 경로 압축만 쓰면 연산 하나당 분할 상환 O(log N). 전체 O((N + M) log N). 랭크(또는 크기) 기준 합치기를 함께 쓰면 거의 상수인 O(α(N))까지 줄어든다
- **재귀 깊이 주의**: `uf[A] = B`로 항상 앞쪽 대표를 뒤쪽에 붙이는 방식이라, `union(1,2), union(2,3), ...` 순서로 들어오면 체인이 N까지 길어질 수 있다. 첫 `find`에서 재귀 깊이가 N이 되므로 N이 크면 반복문 find나 랭크 합치기가 필요
- **입출력**: 조회마다 `System.out.println`을 호출하는데, M이 크면 `StringBuilder`에 모아 한 번에 출력하는 편이 빠르다
- **연결**: 다음 단계인 MST(크루스칼)에서 사이클 판별에 이 구조가 그대로 쓰인다. B형 연습 필수 기본기로도 자주 나온다
## 다시 볼 때 체크할 것
- [ ] `find`에서 `return uf[x] = find(uf[x])` 한 줄이 왜 경로 압축인지, 빼면 최악의 경우 어떻게 되는지 설명할 수 있는가?
- [ ] `union(1,2), union(2,3), union(3,4)...`처럼 주어지는 입력에서 트리 모양이 어떻게 되는지 그려볼 수 있는가?
- [ ] 랭크(또는 크기) 기준 합치기를 추가해 직접 다시 짤 수 있는가? (반복문 find 포함)
