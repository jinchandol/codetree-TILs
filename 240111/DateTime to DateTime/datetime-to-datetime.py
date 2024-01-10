a, b, c = map(int, input().split())

if (b >= 11) and (c>= 11):
    d_a = a - 11
    h_b = b - 11
    m_c = c - 11

    ans = d_a*24*60 + h_b*60 + m_c
    print(ans)
else:
    print(-1)