package me.welton.myapplication

fun Tangram(): List<Geometria>{

    var listaObjetos = ArrayList<Geometria>()

    //Desenha o tangram inicial
    listaObjetos.add(Triangulo(900, 450)    //Triangulo Grande
            .setXY(295, 900)
            .incAngulo(-90f))

    listaObjetos.add(Triangulo(900, 450)    //Triangulo Grande
            .setXY(525, 1130)
            .incAngulo(180f))

    listaObjetos.add(Triangulo(620, 310)    //Triangulo Medio
            .setXY(865, 560)
            .incAngulo(-135f))

    listaObjetos.add(Triangulo(442, 221)    //Triangulo Pequeno
            .setXY(865, 1125)
            .incAngulo(90f))

    listaObjetos.add(Triangulo(440,220)     //Triangulo Pequeno
            .setXY(525, 780))

    listaObjetos.add(Quadrado(310)
            .setXY(750, 900)
            .incAngulo(45f))

    listaObjetos.add(Paralelograma(420,200)
            .setXY(400,550))

    return listaObjetos
}


fun Peixe(): List<Geometria>{

    var listaObjetos = ArrayList<Geometria>()

    //Desenha o tangram inicial
    listaObjetos.add(Triangulo(450, 225)    //Triangulo Grande
            .setXY(700, 500)
            .incAngulo(-315f))

    listaObjetos.add(Triangulo(450, 225)    //Triangulo Grande
            .setXY(700, 670)
            .incAngulo(135f))

    listaObjetos.add(Triangulo(310, 155)    //Triangulo Medio
            .setXY(370, 500)
            .incAngulo(-90f))

    listaObjetos.add(Triangulo(220, 110)    //Triangulo Pequeno
            .setXY(570, 715)
            .incAngulo(-135f))

    listaObjetos.add(Triangulo(220,110)     //Triangulo Pequeno
            .setXY(570, 455)
            .incAngulo(315f))

    listaObjetos.add(Quadrado(155)
            .setXY(535, 585))

    listaObjetos.add(Paralelograma(160,155)
            .setXY(370,675)
            .incAngulo(-90f))

    return listaObjetos
}