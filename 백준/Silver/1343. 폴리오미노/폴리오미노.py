# 폴리오미노

def changeItem(item):
    l = len(item)
    result = ''
    while (l):
        if l >= 4:
            result += 'AAAA'
            l -= 4
        elif l >= 2:
            result += 'BB'
            l -= 2
    return result


s = input()

lst = s.split('.')

ans = ''

for item in lst:
    l = len(item)

    if l % 2 == 1:
        ans = '-1 '
        break

    ans = ans + changeItem(item) + '.'

print(ans[:-1])
