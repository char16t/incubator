n = 6
k = 3
result = []

def recur(s, n, result):
    if len(s) == k:
        result.append(s)
        return
    for i in range(0, n):
        recur(s + str(i + 1), n, result)

recur("", n, result)
print(result)
