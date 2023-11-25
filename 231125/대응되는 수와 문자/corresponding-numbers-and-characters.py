n, m = map(int, input().split())
freq = dict()

for i in range(1, n+1):
    string = input()
    freq[string] = str(i)
    freq[str(i)] = string

for _ in range(m):
    question = input()
    print(freq[question])