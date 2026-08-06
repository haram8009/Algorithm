class Solution:
    def hIndex(self, citations: List[int]) -> int:
        h=0
        n=len(citations)

        while(True):
            if -1 in citations:
                if (n-citations.count(-1)) < h:
                    break
            # print("b", h, citations)
            h += 1
            for i in range(n):
                if citations[i] > -1:
                    citations[i] -= 1
            # print("d", h, citations)
        return h - 1 if h-1<=n else n
    