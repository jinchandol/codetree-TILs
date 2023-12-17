n, m = map(int, input().split())

def rec(row, col):
    for _ in range(row):
        print('1'*col)

rec(n, m)