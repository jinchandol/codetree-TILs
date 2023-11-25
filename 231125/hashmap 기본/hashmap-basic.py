n = int(input())

s = dict()

for _ in range(n):
    command = list(input().split())

    if command[0].startswith('add'):
        s[command[1]] = command[2]
    
    elif command[0].startswith('find'):
        if command[1] not in s:
            print('None')
        else:
            print(s[command[1]])
    
    elif command[0].startswith('remove'):
        s.pop(command[1])