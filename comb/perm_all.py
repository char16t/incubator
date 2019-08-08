n = 6
a = [0] * n
result = []

def recur(s, n, was, result):
    if len(s) == n:
        result.append(s)
        return
    for i in range(0, n):
        if was[i] == 0:
            was[i] = 1
            recur(s + str(i + 1), n, was, result)
            was[i] = 0

recur("", n, a, result)
print(result)
