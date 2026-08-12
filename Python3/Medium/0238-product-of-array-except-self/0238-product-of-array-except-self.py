class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        answer = []

        total = 1
        zeros = []
        for i in range(len(nums)):
            if nums[i]==0:
                zeros.append(i)
                continue
            total *= nums[i] 


        if len(zeros)>1:
            answer = [0]*len(nums)
        elif len(zeros)==1:
            for i in range(len(nums)):
                if nums[i]==0:
                    answer.append(total)
                else:    
                    answer.append(0)
        else:
            for n in nums:
                answer.append(total//n)
        return answer