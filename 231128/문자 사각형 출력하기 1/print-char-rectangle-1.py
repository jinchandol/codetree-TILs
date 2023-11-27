n = int(input())

grid = [
    ['' for _ in range(n)]
    for _ in range(n)
]
start = 97

for elem1 in range(n-1, -1, -1):
    for elem2 in range(n-1, -1, -1):
        grid[elem2][elem1] = chr((start+123)%123).upper()
        
        if start == 122:
            start = 97
        else:
            start += 1

for i in range(n):
    for j in range(n):
        print(grid[i][j], end = " ")
    print()