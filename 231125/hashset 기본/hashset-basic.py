n = int(input())

s = set()

def do_it(p, q):
    global s
    if p == 'find':
        if q in s:
            print("true")
        else:
            print("false")

    elif p == 'add':
        s.add(q)
    
    elif p == 'remove':
        s.remove(q)

for _ in range(n):
    action, num = input().split()
    do_it(action, num)