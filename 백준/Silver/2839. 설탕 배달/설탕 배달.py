n = int(input())

cnt_5 = 0
cnt_3 = 0

while(n):
    if n >= 5:
        cnt_5 += n//5    
        n = n%5
    elif n >= 3:
        cnt_3 += n//3
        n = n%3
    else:
        break

while(n):
    if n >= 3:
        cnt_3 += n//3
        n = n%3
    elif cnt_5 >0:
        cnt_5 -=1
        n += 5
    else:
        n=0
        cnt_5=-1
        cnt_3=0
print(cnt_5+cnt_3)