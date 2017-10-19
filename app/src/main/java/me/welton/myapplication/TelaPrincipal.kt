package me.welton.myapplication

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.SystemClock
import android.provider.Settings
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

//Metodo de extensão
fun Geometria.corAleatoria(): Geometria{
    //Define valores de cor aleatorios e retorna o objeto
    return this.setCor(
            Math.random().toFloat(),
            Math.random().toFloat(),
            Math.random().toFloat())
}

//define a classe que vai controlar o comportamento do desenho (o que sera desenhado)
internal class Renderizador : GLSurfaceView.Renderer {

    private var listaObjetos: List<Geometria> = ArrayList()

    override //chamado quando a superficie de desenho for criada (1vez)
    fun onSurfaceCreated(vrOpengl: GL10, eglConfig: EGLConfig) {
        //CONFIGURANDO A LIMPEZA DE FUNDO (RGBT)
        vrOpengl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f)

        //Recupera a lista de objetos a ser desenhada, arquivo: Desenhos.kt
        listaObjetos = Peixe() //Tangram()

        //Define uma cor aleatoria para cada objeto na lista, usando "arrow function" e "metodo de extensão"
        listaObjetos.forEach{x->x.corAleatoria()}
    }

    override fun onSurfaceChanged(vrOpengl: GL10, width: Int, heigth: Int) {
        //chamado quando as configurações da superficie desenho forem alteradas
        //ex: rotação do aparelho
        Log.i("INFO", "SUPERFICIE ALTERADA$width $heigth")

        //INICIAR A PREPARAÇÃO DO OPENGL PARA DESENHO
        //CONFIGURAR O VIEWPORT - AREA DE DESENHO UTILIZADA DA TELA
        vrOpengl.glViewport(0, 0, width, heigth)
        //FAZ A OPENGL APONTAR PARA A MATRIZ DE PROJEÇÃO
        vrOpengl.glMatrixMode(GL10.GL_PROJECTION)
        //CARREGA A MATRIZ DE IDENTIDADE NA PROJEÇÃO
        vrOpengl.glLoadIdentity()

        //CHAMA O COMANDO PARA CONFIGURAR A AREA DE DESENHO (VOLUME DE REDENRIZAÇÃO)
        vrOpengl.glOrthof(0f, width.toFloat(), 0f, heigth.toFloat(), -1f, 1f)

        //SOLICITA AO OPENGL APONTAR PARA A MATRIZ DE MODELOS DE CAMERA
        vrOpengl.glMatrixMode(GL10.GL_MODELVIEW)
        vrOpengl.glLoadIdentity()

        //FloatBuffer vertices = criaFloatBuffer(vetorJava);
        //SOLICITA  A BIBLIOTECA QUE HABILITE  O RECURSO DE VETOR DE VERTICES
        vrOpengl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
    }

    override fun onDrawFrame(vrOpengl: GL10) {
        //SOLICITA A OPENGL QUE LIMPE A MATRIZ DE PIXEL DE CORES
        vrOpengl.glClear(GL10.GL_COLOR_BUFFER_BIT)

        //Usa um foreach para chamar o metodo desenha() de cada objeto da lista de objetos
        for(objeto in listaObjetos){
            objeto.desenha(vrOpengl)
        }
    }
}

class TelaPrincipal : Activity() {
    //cria a referencia para o objeto responsavel pela exibição do desenho
    internal var vrSuperficieDesenho: GLSurfaceView? = null
    internal var vrRenderizador: Renderizador? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //SOLICITA A RETIRADA DA BARRA DE TITULO
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        //validar variavel de referencia para a superficie do desenho
        vrSuperficieDesenho = GLSurfaceView(this)
        vrRenderizador = Renderizador()
        vrSuperficieDesenho!!.setRenderer(vrRenderizador)

        //CONFIGURA MODO FULLSCREEN NA LARGURA E ALTURA
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //SOLICITA QUE O APLICATIVO FIQU7E ATIVO NA TELA MESMO POR INATIVIDADE DO USUARIO
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContentView(vrSuperficieDesenho)
    }
}
