---
schema_version: 1
platform: "Codetree"
title: "지질 연구"
problem_url: "https://www.codetree.ai/ko/trails/complete/curated-cards/challenge-geological-research/description"
notion_page_url: "https://app.notion.com/p/3ec3d9b37e1c81c8bf2ce5b8231a5377"
legacy_title: "지질 연구"
problem_id: ""
difficulty: "Easy"
topics: ["Graph","BFS/DFS","DP"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-10-01"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(N + M) / O(N + M)"
time_minutes: ""
code_url: "https://github.com/haram8009/Algorithm/blob/main/Codetree/trail6/%EC%A7%80%EC%A7%88%20%EC%97%B0%EA%B5%AC/geological-research.java"
---
## 문제 요약
- 노드 N개, 간선 M개의 방향 그래프(사이클 없음). 간선 `u v`는 u가 v에 압력을 준다는 뜻
- 제한: `2 ≤ N ≤ 1,000`, `N-1 ≤ M ≤ 100,000`
- 들어오는 간선이 없는 노드의 압력은 1
- 그 외 노드는 들어오는 압력 중 최댓값이 **2번 이상** 나오면 최댓값+1, **1번만** 나오면 최댓값 그대로
- 전체 노드 중 가장 큰 압력을 출력
## 내 접근
- 간선을 뒤집어 저장: `graph[v]`에 v에게 압력을 주는 노드 u를 모음
- `dfs(node)`: 들어오는 노드가 없으면 1. 있으면 각 `dfs(next)`의 최댓값 `max_w`와 그 등장 횟수 `cnt`를 구해 `cnt > 1 ? max_w + 1 : max_w`
- `dp[node]`에 결과를 저장해 한 번 계산한 노드는 재계산하지 않음. 값이 항상 1 이상이라 `dp[node] != 0`을 방문 표시로 써도 안전
- 모든 노드에서 dfs를 돌려 최댓값을 정답으로 사용
```java
import java.util.*;

public class Main {
	static List<Integer>[] graph;
	static int[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		graph = new List[n + 1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		// [해당 노드에 가해지는 최대 압력]
		dp = new int[n + 1];

		for (int i = 0; i < m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();

			// 해당 노드에 압력을 주는 노드 저장
			graph[v].add(u);
		}

		// Please write your code here.
		int answer = 0;
		for (int i = 1; i < graph.length; i++) {
			int tmp = dfs(i);
			if(tmp>answer)
				answer = tmp;
		}

//		System.out.println(Arrays.toString(dp));
		System.out.println(answer);
	}

	public static int dfs(int node) {
		if (dp[node] != 0) {
			return dp[node];
		}

		if (graph[node].isEmpty()) {
			dp[node] = 1;
			return dp[node];
		}

		int max_w = 0;
		int cnt = 0;
		for (int next : graph[node]) {
			int tmp = dfs(next);
			if (tmp > max_w) {
				cnt = 1;
				max_w = tmp;
			} else if (tmp == max_w) {
				cnt++;
			}
		}

		dp[node] = cnt > 1 ? max_w + 1 : max_w;
		return dp[node];
	}
}
```
## 막힌 지점
## 배운 것 / 패턴
- **패턴**: DAG 위의 메모이제이션 DFS (= 위상 순서 DP). 간선을 뒤집어 "나에게 영향을 주는 노드"를 모으면 점화식이 자연스럽게 나온다. 하천 차수를 구하는 Strahler 수와 같은 구조
- **반례로 확인한 규칙**: 들어오는 값이 (2, 2)면 3, (2, 1)이면 2, (1, 1, 1)이면 **2** (3이 아님). 최댓값이 몇 번 나오든 +1은 한 번만 올라간다
- **복잡도**: 각 노드는 한 번만 계산되고 각 간선은 한 번씩만 훑으므로 O(N + M)
- **제한 조건 읽기**: N이 최대 1,000이라 재귀 깊이가 작아 StackOverflow 걱정은 없다. N이 크면 Kahn 위상 정렬(진입차수 0부터 큐로 처리)로 바꿔야 안전
- **연결**: 시험(B형)에서 DAG 전파 문제가 나오면 재귀 깊이와 입력 속도(Scanner 대신 StreamTokenizer)를 먼저 점검할 것
## 다시 볼 때 체크할 것
- [ ] `cnt`를 "최댓값이 나온 횟수"로 정의하고, 더 큰 값이 나올 때 1로 리셋하는 이유를 설명할 수 있는가?
- [ ] 같은 간선 `u v`가 중복으로 들어오면 어떤 입력에서 답이 틀어지는지 말할 수 있는가?
- [ ] 재귀 DFS를 Kahn 위상 정렬로 바꿔서 직접 다시 짤 수 있는가?
<empty-block/>
