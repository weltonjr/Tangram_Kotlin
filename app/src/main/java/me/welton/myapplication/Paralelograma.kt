package me.welton.myapplication

/**
 * Created by welton on 18/10/2017.
 */

class Paralelograma(largura: Int, altura: Int) : Geometria() {

    init {
        //CRIA UM VETOR PARA PASSAR AO METODO QUE VAI CRIAR O BUFFER DAS COORDENADAS
        val vetorVertices = floatArrayOf(
                (-altura - largura) / 2f, - altura / 2f,   //inferior esquerdo
                (altura - largura)  / 2f, altura / 2f,     //superior esquerdo
                (largura - altura) / 2f, -altura / 2f,     //inferior direito
                (largura + altura) / 2f, altura / 2f       //superior direito
        )

        setVetorVertices(vetorVertices)
    }
}