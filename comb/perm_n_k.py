n = 6
k = 3
keys = [i for i in range(int(n))]
values = [0] * n
a = dict(zip(keys, values))
result = []

def recur(s, n, was, result):
    if len(s) == k:
        result.append(s)
        return
    for i in range(0, n):
        if was[i] == 0:
            was[i] = 1
            recur(s + str(i + 1), n, was, result)
            was[i] = 0

recur("", n, a, result)
print(result)
