t = int(input())

words=[]
for _ in range(t):
    word = input()
    if word not in words:
        words.append(word)
    
words = sorted(words, key = lambda x : (len(x), x))

for word in words:
    print(word)