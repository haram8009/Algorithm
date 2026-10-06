---
schema_version: 1
platform: "LeetCode"
title: "Product of Array Except Self"
problem_url: "https://leetcode.com/problems/product-of-array-except-self/"
notion_page_url: "https://app.notion.com/p/3bb3d9b37e1c81b3b791d1aedc9ecd6e"
legacy_title: "Product of Array Except Self"
problem_id: 238
difficulty: "Medium"
topics: ["Array/String"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-12"
retry_needed: true
retry_at: ""
language: ["Python"]
complexity: "O(n) / O(1) (출력 배열 제외)"
time_minutes: ""
code_url: "https://github.com/haram8009/algorithm-study/blob/main/이하람/week02/product_of_array_except_self.py"
---
## 문제 요약
`answer[i]`가 nums에서 i번째를 **뺀** 나머지 전부의 곱이 되도록 배열을 만든다. 제약: **나눗셈 금지**, O(n) 시간.
## 내 접근
전체 곱 `total`을 구해두고 `total // nums[i]`로 각 원소를 계산. 0이 있으면 나눌 수 없으므로 0의 개수로 분기했다.
- 0이 2개 이상 → 전부 0
- 0이 1개 → 그 자리만 `total`(0을 제외한 곱), 나머지는 0
- 0이 없음 → `total // n`
## 막힌 지점
## 배운 것 / 패턴
**1. 분기 3개가 생긴 이유 = 금지된 연산을 썼기 때문**
이 문제가 "나눗셈 금지"를 건 이유가 바로 이 코드에 드러난다. 나눗셈을 쓰는 순간 0으로 나누기가 막히고, 그걸 피하려고 0 개수 분기가 3갈래로 늘어난다. **제약을 우회하면 코드 복잡도로 되돌아온다**는 게 이 문제의 핵심 교훈. (덤으로 정수 오버플로 위험도 있다 — Python은 괜찮지만 Java였다면 `total`이 터진다.)
**2. 정석 — prefix × suffix 2패스**
`answer[i] = (i의 왼쪽 전부의 곱) × (i의 오른쪽 전부의 곱)`. 왼쪽 누적곱을 answer에 먼저 채우고, 오른쪽에서 역방향으로 훑으면서 곱해주면 추가 배열 없이 끝난다:
```python
class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)
        answer = [1] * n

        prefix = 1
        for i in range(n):
            answer[i] = prefix
            prefix *= nums[i]

        suffix = 1
        for i in range(n - 1, -1, -1):
            answer[i] *= suffix
            suffix *= nums[i]

        return answer
```
분기가 하나도 없다. 0이 몇 개든 자동으로 맞는다 — 0을 "특수 케이스"로 볼 필요 자체가 사라지기 때문.
**3. 패턴 이름: 누적합(prefix sum)의 곱셈 버전**
"i번째를 제외한 전체에 대한 집계"를 물으면 **왼쪽 누적 + 오른쪽 누적** 2패스가 정답 패턴이다. 합이면 prefix sum, 곱이면 prefix product, 최댓값이면 prefix max. 문제 표현만 바뀌고 뼈대는 같다.
**4. 연결점**
- 같은 2주차의 **Gas Station**도 "한 방향 누적"으로 O(n)에 푸는 문제 — 누적값의 부호 변화를 관찰하는 게 열쇠다.
- 13주차 **Maximum Subarray(Kadane)** 역시 "왼쪽에서 누적한 상태 하나로 O(n)"이라는 같은 골격.
## 다시 볼 때 체크할 것
- [ ] 나눗셈 없이, 분기 없이 2패스로 다시 쓸 수 있는가?
- [ ] 출력 배열을 공간 계산에서 왜 빼주는지 설명할 수 있는가?
- [ ] "i를 제외한 집계"라는 표현을 보면 바로 prefix/suffix가 떠오르는가?
