def solution(board, h, w):
    answer = 0
    
    n = len(board[0])
    
    dh = [0,1,-1,0]
    dw = [1,0,0,-1]
    
    for i in range(4):
        h_check = h+dh[i]
        w_check = w+dw[i]
        if 0 <= h_check and h_check < n and 0<= w_check and w_check <n:
            if board[h][w] == board[h_check][w_check]:
                answer += 1
        
    
    return answer