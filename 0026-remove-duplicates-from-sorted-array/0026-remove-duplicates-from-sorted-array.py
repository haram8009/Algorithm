class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        # 정렬돼있으니 삭제처리할 때 몇 offset 옮기는지만 저장해놓으면 됨
        k = 0
        idx=0
        while(idx<len(nums) and k<len(nums)):
            print(k, idx)
            nums[k] = nums[idx]

            k+=1
            while(idx+1<len(nums) and nums[idx]==nums[idx+1]):
                idx+=1
            idx+=1
        # k=마지막으로 저장한 인덱스+1
        return k

