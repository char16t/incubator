n = 6
k = 3

result = []

def recur(s, n, result):
    if len(s) == k:
        result.append(s)
        return
    rangeStart = int(s[-1])-1 if len(s) > 0 else 0
    for i in range(rangeStart, n):
        recur(s + str(i + 1), n, result)

recur("", n, result)
print(result)
print(len(result))


