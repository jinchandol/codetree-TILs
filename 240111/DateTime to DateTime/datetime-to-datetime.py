a, b, c = map(int, input().split())

if (a == 11) and (b < 11) and (c < 11):
    print(-1)
else:
    d_a = a - 11
    h_b = b - 11
    m_c = c - 11

    ans = d_a*24*60 + h_b*60 + m_c
    print(ans)