def solution(data, ext, val_ext, sort_by):
    answer = []
    index_ = {"code":0, "date":1, "maximum":2, "remain":3}
    
    for row in data:
        if row[index_[ext]] < val_ext:
            answer.append(row)
            
    answer.sort(key = lambda x: x[index_[sort_by]])
    return answer