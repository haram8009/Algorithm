testcase = int(input())
cnt = 0

for _ in range(testcase):
    word = input()
    chars=[]
    
    prev = word[0]
    chars.append(prev)
    for i in range(1, len(word)):
        if word[i] != prev:
            if word[i] in chars:
                break
            else:
                chars.append(word[i])
        prev = word[i]
    else:
        cnt += 1    
print(cnt)