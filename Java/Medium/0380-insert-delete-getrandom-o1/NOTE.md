---
schema_version: 1
platform: "LeetCode"
title: "Insert Delete GetRandom O(1)"
problem_url: "https://leetcode.com/problems/insert-delete-getrandom-o1/"
notion_page_url: "https://app.notion.com/p/3bb3d9b37e1c81edab4bc66d47c532ca"
legacy_title: "Insert Delete GetRandom O(1)"
problem_id: 380
difficulty: "Medium"
topics: ["Array/String","Hashmap"]
study_group: "사전학습"
result: "Accepted"
solved_at: "2026-08-12"
retry_needed: true
retry_at: ""
language: ["Java"]
complexity: "O(n) / O(n)  ※ 요구사항은 평균 O(1)"
time_minutes: ""
code_url: "https://github.com/haram8009/algorithm-study/blob/main/이하람/week02/InsertDeleteGetRandomO1.java"
---
## 문제 요약
insert / remove / getRandom 세 연산을 **모두 평균 O(1)** 에 지원하는 자료구조를 설계한다.
## 내 접근
`ArrayList` 하나만 두고, `indexOf`로 중복 여부를 검사한 뒤 `add` / `remove(Integer.valueOf(val))`로 처리. `getRandom`은 `Random.nextInt(size)`로 인덱스를 뽑았다.
## 막힌 지점
## 배운 것 / 패턴
**1. Accepted이지만 요구 복잡도는 못 맞췄다 (Runtime 5%가 그 증거)**
- `rset.indexOf(val)` → **O(n)** 선형 탐색
- `rset.remove(Integer.valueOf(val))` → 찾는 데 O(n) + 뒤 원소 당기는 데 O(n)
즉 insert/remove가 O(n)이다. 이 문제는 "풀리느냐"가 아니라 "**세 연산 전부 O(1)이 되느냐**"가 전부인 설계 문제라, 이 풀이는 사실상 문제를 안 푼 것에 가깝다. 205ms(5.06%)라는 숫자가 정확히 그 얘기를 하고 있다.
**2. 핵심 통찰: 왜 HashSet 하나로는 안 되는가**
- insert/remove만 보면 `HashSet`이 O(1)로 완벽하다. **그런데 ****`getRandom`****이 안 된다** — 해시셋은 인덱스로 접근할 수 없어서, 균등 랜덤을 뽑으려면 전체를 순회해야 한다.
- 거꾸로 배열만 쓰면 `getRandom`은 O(1)인데 탐색/삭제가 O(n).
→ **두 자료구조를 겹쳐서 서로의 약점을 덮는다**: `ArrayList`(랜덤 접근용) + `HashMap<값, 인덱스>`(위치 조회용).
**3. O(1) 삭제의 트릭 — 마지막 원소와 swap**
배열 중간을 지우면 뒤를 당겨야 해서 O(n)이다. 하지만 이 문제는 **순서를 보장할 필요가 없다.** 그래서 지울 자리에 마지막 원소를 덮어쓰고 맨 뒤를 잘라내면 O(1):
```java
class RandomizedSet {
    private final List<Integer> list = new ArrayList<>();
    private final Map<Integer, Integer> idx = new HashMap<>();
    private final Random rd = new Random();

    public boolean insert(int val) {
        if (idx.containsKey(val)) return false;
        idx.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        Integer i = idx.remove(val);
        if (i == null) return false;
        int last = list.get(list.size() - 1);
        list.set(i, last);              // 마지막 값을 지울 자리로
        if (last != val) idx.put(last, i);  // 옮긴 값의 인덱스 갱신
        list.remove(list.size() - 1);   // 맨 뒤 제거 → O(1)
        return true;
    }

    public int getRandom() {
        return list.get(rd.nextInt(list.size()));
    }
}
```
**4. 자잘하지만 중요한 것들**
- `remove(int)`는 **인덱스** 삭제, `remove(Object)`는 **값** 삭제다. `Integer.valueOf(val)`을 쓴 건 맞는 판단이었다 — 이 오버로딩 함정은 Java 코테 단골.
- 별도 `size` 필드는 `list.size()`와 어긋날 위험만 만든다. 상태는 하나로.
**5. 연결점**
"해시맵 + 다른 자료구조를 결합해 모든 연산을 O(1)로"는 그대로 **7주차 LRU Cache**(해시맵 + 이중연결리스트)로 이어진다. 이 문제를 제대로 풀어두면 LRU가 응용문제로 보인다.
## 다시 볼 때 체크할 것
- [ ] getRandom 때문에 HashSet 단독이 왜 불가능한지 설명할 수 있는가?
- [ ] remove에서 `last == val`일 때 인덱스 갱신을 건너뛰어야 하는 이유는?
- [ ] `list.remove(i)` vs `list.remove(Integer.valueOf(i))` 차이를 바로 말할 수 있는가?
