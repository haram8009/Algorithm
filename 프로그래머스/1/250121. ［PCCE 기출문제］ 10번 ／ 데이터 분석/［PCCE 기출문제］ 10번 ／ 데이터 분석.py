def solution(data, ext, val_ext, sort_by):
    answer = []
    index_ = {"code":0, "date":1, "maximum":2, "remain":3}
    
    answer = list(filter(lambda x : x[index_[ext]] < val_ext , data))
            
    answer.sort(key = lambda x: x[index_[sort_by]])
    return answer