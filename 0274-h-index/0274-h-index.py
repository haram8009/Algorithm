class Solution:
    def hIndex(self, citations: List[int]) -> int:
        h=0
        n=len(citations)

        while(True):
            if h-1 > n:
                return n
            if -1 in citations:
                if (n-citations.count(-1)) < h:
                    break
            h += 1
            for i in range(n):
                if citations[i] > -1:
                    citations[i] -= 1
        return h - 1 