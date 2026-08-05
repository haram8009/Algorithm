class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        maxP = 0
        thisMaxP = 0
        thisMax = prices[len(prices)-1]
        # 거꾸로 순회 -> 구간별 max profit 갱신
        for i in range(len(prices)-1, -1, -1):
            curr = prices[i]
            # 구간 초기화 
            if curr > thisMax:
                thisMax=curr
            elif curr < thisMax:
                thisMaxP = max(thisMax-curr, thisMaxP)
                maxP = max(thisMaxP, maxP)  
        return maxP

            
