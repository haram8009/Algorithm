def solution(n, m, section):
    answer = 1
    
    prev = section[0]
    
    for sec in section:
        if sec - prev >= m:
            answer+=1
            prev = sec
    
    return answer