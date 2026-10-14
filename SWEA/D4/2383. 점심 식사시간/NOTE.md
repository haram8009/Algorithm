---
schema_version: 1
platform: "SWEA"
title: "[모의 SW 역량테스트] 점심 식사시간"
problem_url: "https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Pp2uqA8MDFAXB"
problem_id: "2383"
difficulty: "D4"
topics: ["완전탐색", "시뮬레이션", "정렬"]
study_group: ""
result: "통과"
solved_at: "2026-10-14"
retry_needed: false
retry_at: ""
language: ["Java"]
complexity: "O(2^P × P log P), P ≤ 10"
time_minutes: ""
code_url: ""
---

# SWEA 2383. [모의 SW 역량테스트] 점심 식사시간

## 한 줄 인사이트

사람마다 두 계단 중 하나를 고르는 모든 경우를 탐색하고, 각 계단에 배정된 사람은 도착 시간순으로 정렬해 3명 제한을 반영한 완료 시각을 계산한다.

## 문제 핵심 규칙

- 사람은 계단 입구까지 맨해튼 거리 `|r1-r2| + |c1-c2|`만큼 이동한다.
- 계단 앞에 도착한 뒤 1분 대기하고 내려가기 시작한다.
- 길이 `K`인 계단을 내려가는 데 `K`분이 걸린다.
- 계단에는 한 번에 최대 3명만 올라갈 수 있다.
- 사람은 최대 10명, 계단은 2개이므로 배정은 최대 `2^10 = 1024`가지다.

## 풀이 구조

1. `dfs(i)`에서 사람 `i`를 계단 0 또는 1에 배정한다.
2. 배정이 끝나면 `calc()`에서 각 계단에 배정된 사람의 도착 시간을 모아 정렬한다.
3. 앞에서부터 계단 이용 완료 시각을 계산한다.
4. 두 계단의 완료 시각 중 큰 값이 해당 배정의 전체 소요 시간이다.
5. 모든 배정의 전체 소요 시간 중 최솟값을 답으로 선택한다.

## 핵심 점화식

`arrival[i]`는 정렬된 도착 시간, `finish[i]`는 i번째 사람의 계단 이용 완료 시각이다.

```text
start = arrival[i] + 1
if (i >= 3) start = max(start, finish[i - 3])
finish[i] = start + K
```

세 명이 이미 계단을 이용 중이면 세 명 앞 사람의 완료 시각까지 기다린 뒤 시작한다. `finish[i - 3]`은 이미 계산된 완료 시각이고, 현재 `arrival[i]`는 아직 도착 시각이므로 각각의 의미를 섞지 않도록 한다.

## 예시: 3명 제한 확인

계단 길이 `K = 2`, 도착 시간이 모두 1인 사람이 4명이라고 하자.

- 처음 세 명은 도착 후 1분 대기하고 2분 동안 내려가므로 완료 시각은 4다.
- 네 번째 사람은 도착 후 대기가 끝나는 시각이 2지만, 앞사람이 계단에서 나오는 시각 4까지 기다려야 한다.
- 따라서 네 번째 사람의 완료 시각은 `max(2, 4) + 2 = 6`이다.

3명 제한을 반영하지 않으면 네 명 모두 4분에 끝난 것으로 잘못 계산한다.

## 정렬이 필요한 이유

도착 시간 `[5, 1, 1, 1]`, `K = 2`를 생각해 보자.

정렬하지 않으면 네 번째 원소를 계산할 때 `t[0]`을 참조한다. 이때 `t[0]`은 가장 늦게 도착한 사람의 완료 시각이므로, 실제로 먼저 계단을 빠져나가는 사람을 기준으로 대기 시간을 계산하지 못한다.

정렬한 `[1, 1, 1, 5]`에서는 처음 세 사람의 완료 시각이 4이고, 마지막 사람은 `max(5 + 1, 4) + 2 = 8`에 끝난다. 그러므로 계단 이용 순서를 계산하기 전에 반드시 도착 시간순 정렬이 필요하다.

## 실수 체크리스트

- [ ] `static int n`이 있는데 `main` 안에서 `int n`을 다시 선언해 static 필드를 가리지 않았는가?
- [ ] `choice` 배열을 사람 수를 읽고 난 뒤 올바른 크기로 만들었는가?
- [ ] 최솟값을 구하는 답 변수를 `Integer.MAX_VALUE`로 초기화했는가?
- [ ] `dfs(0)`을 호출했는가? 호출하지 않으면 답이 초기값 그대로 출력된다.
- [ ] 한 계단에 배정된 사람이 0명일 때 마지막 원소에 접근하지 않았는가?
- [ ] 계단 앞 1분 대기를 반영했는가?
- [ ] 완료 시각에 계단 길이 `K`를 한 번만 더했는가?
- [ ] 메서드 이름과 배정 배열 이름을 서로 다르게 정했는가?

## 최종 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static int n, ans, pCnt;
	static int[][] people; // [i] = {행, 열}
	static int[][] stair; // [s] = {행, 열, 길이}
	static int[] choice; // choice[i] = 사람 i가 선택한 계단(0 또는 1)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine().trim());
			people = new int[10][2];
			stair = new int[2][3];
			choice = new int[10];
			pCnt = 0;
			int idx = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					int v = Integer.parseInt(st.nextToken());
					if (v == 1)
						people[pCnt++] = new int[] { i, j };
					else if (v > 1)
						stair[idx++] = new int[] { i, j, v };
				}
			}

			dfs(0);
			System.out.println("#" + tc + " " + ans);
		}
	}

	static void dfs(int depth) {
		if (depth == pCnt) {
			calc();
			return;
		}

		choice[depth] = 0;
		dfs(depth + 1);
		choice[depth] = 1;
		dfs(depth + 1);
	}

	static void calc() {
		int total = 0;

		for (int s = 0; s < 2; s++) {
			int k = stair[s][2];
			int sCnt = 0;
			int[] t = new int[10];

			for (int i = 0; i < pCnt; i++) {
				if (choice[i] == s) {
					t[sCnt++] = Math.abs(people[i][0] - stair[s][0])
							+ Math.abs(people[i][1] - stair[s][1]);
				}
			}

			if (sCnt == 0)
				continue;

			Arrays.sort(t, 0, sCnt);
			for (int i = 0; i < sCnt; i++) {
				int start = t[i] + 1; // 도착 후 1분 대기
				if (i >= 3)
					start = Math.max(start, t[i - 3]); // t[i - 3]은 완료 시각
				t[i] = start + k; // 완료 시각
			}

			total = Math.max(total, t[sCnt - 1]);
		}

		ans = Math.min(ans, total);
	}
}
```

## 복잡도

- 시간: `O(2^P × P log P)`, `P ≤ 10`
- 공간: `O(N² + P)`

## 배운 점

- 최적값을 바로 구하기 어렵더라도 입력 크기가 작으면 완전탐색과 시뮬레이션으로 모든 경우를 확인할 수 있다. 이 문제는 최대 1024가지 배정이므로 가지치기 없이도 충분하다.
- 먼저 도착한 사람부터 처리하는 문제에서는 정렬이 계산의 전제다.
- 배열 값을 도착 시각에서 완료 시각으로 덮어쓸 때는 계산 순서가 중요하다. `t[i - 3]`은 앞에서 이미 완료 시각으로 바뀌었고, `t[i]`는 아직 도착 시각인 상태에서 점화식을 적용한다.
- 결과가 `Integer.MAX_VALUE`라면 최솟값 갱신이 한 번도 일어나지 않았는지, 특히 탐색 시작 호출을 확인한다.
