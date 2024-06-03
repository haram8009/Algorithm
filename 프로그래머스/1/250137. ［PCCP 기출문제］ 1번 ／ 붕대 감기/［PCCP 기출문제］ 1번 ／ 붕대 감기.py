def solution(bandage, health, attacks):
    answer = health # 남은 체력
    t, x, y = bandage
    
    success = 0 # 연속 성공
    prev_attack_time = 0
    for attack_time, attack_damage in attacks:
        if prev_attack_time:
            success = attack_time - prev_attack_time -1
            times = success//t if success else 0
            answer += (success*x + y*times)
            if answer > health:
                answer = health
        answer -= attack_damage
        if answer<=0:
            return -1
        prev_attack_time = attack_time
        
    return answer if answer>0 else -1