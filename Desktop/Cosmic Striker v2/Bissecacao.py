def bisseccao(f, xa,xb,precisao):
    if f(xa)* f(xb) <0:
        print("o intervalo fornecido não é adequado para aplicar esse método")
    while(xa - xb/2)> precisao:
        xm = (xa + xb)/2
        if f(xm) ==0:
            return xm
        if f(xa) * f(xm) <0:
            xb = xm
        else:
            xa=xm
        if xb - xa < precisao:
            break
        if (xm+1)-xm <precisao:
            break
        if(xm<precisao):
            break
        return (xa + xb) / 2
def newton(f, f_c, x0, precisao):
    while True:
        if f(x0) < precisao:
            return x0
        if f_c(x0) ==0:
            print("A derivada é zero")
        x_novo =  x0 - f(x0) / f_c(x0)
        if abs(x_novo - x0) <precisao or  abs(f(x_novo)) < precisao:
            return x_novo
        x0 = x_novo


def funcao_exemplo(x):
    return x**2 - 4

def derivada_funcao_exemplo(x):
    return 2*x

# Chute inicial e precisão
x0 = 1
precisao = 1e-5

# Encontrar a raiz
raiz = newton(funcao_exemplo, derivada_funcao_exemplo, x0, precisao)
print(f"A raiz encontrada é: {raiz:.5f}")