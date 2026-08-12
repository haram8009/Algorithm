class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        if len(haystack)<len(needle):
            return -1
        n = len(haystack)
        haystack += needle

        i = haystack.index(needle)

        return -1 if i==n else i 