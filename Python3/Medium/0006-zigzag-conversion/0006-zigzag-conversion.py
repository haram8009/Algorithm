class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows == 1:
            return s

        arr = [""]*numRows

        d=-1
        row = 0
        for i in range(len(s)):
            if row==0 or row==numRows-1:
                d = -d
            arr[row] += s[i]
            row += d
        return "".join(arr)