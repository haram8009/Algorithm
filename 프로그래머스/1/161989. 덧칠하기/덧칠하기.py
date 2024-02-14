def solution(n, m, section):
    answer = 0
    section = set(section)

    i = 1
    while(i <= n):
        if i in section:
            answer+=1
            i+=m
        else:
            i+=1
    return answer