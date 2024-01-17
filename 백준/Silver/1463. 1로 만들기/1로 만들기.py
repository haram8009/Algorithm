n = int(input())

lst = [0] * (n+1)

for i in range(2, n+1):
    ans, ans1, ans2, ans3 = 99999,0,0,0
    if i%3 == 0:
        ans1 = i//3
        ans = lst[ans1]
    if i%2==0:
        ans2 = i//2
        ans = min(ans, lst[ans2])
    ans3 = i-1
    ans = min(ans, lst[ans3])
    
    lst[i] = ans + 1
    
print(lst[n])