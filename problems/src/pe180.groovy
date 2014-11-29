def f1 = { x, y, z, n -> x ** (n + 1) + y ** (n + 1) - z ** (n + 1) }
def f2 = { x, y, z, n -> (x * y + y * z + z * x) * (x ** (n - 1) + y ** (n - 1) - z ** (n - 1)) }
def f3 = { x, y, z, n -> x * y * z * (x ** (n - 2) + y ** (n - 2) - z ** (n - 2)) }

def fs = { x, y, z, n -> f1(x, y, z, n) + f2(x, y, z, n) - f3(x, y, z, n) }

def s = { x, y, z -> x + y + z }
def t = { u, v -> u / v }
def k = 35
