def solution(n, m, section):
    answer = 0
    
    if m==1:
        return len(section)

    i = 1
    while(i <= n):
        if i in section:
            answer+=1
            section = [s for s in section if not (i <= s <= (i+m-1))]
            i+=m
        else:
            i+=1
    return answer