n, k = map(int, input().split())

arr = [0] + [x for x in range(1, n+1)]

count = [set() for _ in range(n)]

for i in range(n):
    count[i].add(i+1)

command = [0]


for _ in range(k):
    command.append(list(map(int, input().split())))

for _ in range(3):
    for idx in range(1, k+1):
        num1, num2 = command[idx][0], command[idx][1]
        
        temp = arr[num1]
        arr[num1] = arr[num2]
        arr[num2] = temp 

        count[arr[num1]-1].add(num1)
        count[arr[num2]-1].add(num2)

for i in count:
    print(len(i))