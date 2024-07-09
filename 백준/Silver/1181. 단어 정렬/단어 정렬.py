t = int(input())

words=[]
for _ in range(t):
    words.append(input())
words = list(set(words))
    
# words = sorted(words, key = lambda x : (len(x), x))
words.sort()
words.sort(key=len)
for word in words:
    print(word)