n = int(input())

arr = []

for _ in range(n):
    command = input().split()

    if command[0].startswith("push"):
        arr.append(int(command[1]))
    elif command[0].startswith("pop"):
        arr.pop()
    elif command[0].startswith("size"):
        print(len(arr))
    else:
        print(arr[int(command[1])-1])