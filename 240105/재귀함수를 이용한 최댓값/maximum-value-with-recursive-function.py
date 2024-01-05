n = int(input())

num_arr = list(map(int, input().split()))

MAX_INT = 0

def recur(array, num, n):
    global MAX_INT
    if num == n:
        return
    if MAX_INT < array[num]:
        MAX_INT = array[num]
    
    recur(array, num+1, n)
    
recur(num_arr, 0, n)

print(MAX_INT)