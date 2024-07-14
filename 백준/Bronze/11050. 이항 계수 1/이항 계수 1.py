n, k = map(int, input().split())

if k==0 or k==n:
    print(1)
else:    
    memo = [0,1]
    # factorial 계산, 캐싱
    for i in range(2, n+1):
        memo.append(memo[i-1]*i)

    # 이항계수 = n!/(k!(n-k))!
    print(memo[n]//(memo[k]*memo[n-k]))