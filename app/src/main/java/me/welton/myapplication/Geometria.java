//Pacote da classe
package me.welton.myapplication;

//Classes utilizadas
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

//jamais havera objeto desta classe no sistema
public abstract class Geometria
{
    //Atributo da classe
    private FloatBuffer vetorVertices = null;
    float[] cor = null;

    private int posX = 0, posY = 0;

    private float angulo = 0;

    private float zoom = 1;

    public Geometria setZoom(float novoZoom)
    {
       this.zoom = novoZoom;
        return this;
    }

    public float getZoom()
    {
        return zoom;
    }

    public Geometria setAngulo(float novoAngulo)
    {
        this.angulo = novoAngulo;
        return this;
    }

    public Geometria incAngulo(float graus)
    {
        angulo += graus;
        return this;
    }

    public float getAngulo()
    {
        return angulo;
    }

    public Geometria(){cor = new float[3];}

    public Geometria setXY(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
        return this;
    }

    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }

    //metodo publico, de acesso direto, para definir a cor do objeto
    public Geometria setCor(float r, float g, float b)
    {
        cor[0]=r;
        cor[1]=g;
        cor[2]=b;
        return this;
    }

    public Geometria mover(int x, int y)
    {
        posX += x;
        posY += y;
        return this;
    }

    //metodo de acesso ao vetor de buffer
    void setVetorVertices(float[] vetorJava)
    {
        vetorVertices = VertexBuffer.criaFloatBuffer(vetorJava);
    };

    //metodo de acesso ao vetor de buffer
    FloatBuffer getVetorVertices(){
        return vetorVertices;
    };

    //Metodo da Classe
    public void desenha(GL10 vrOpengl)
    {
        vrOpengl.glLoadIdentity();
        vrOpengl.glTranslatef(getPosX(), getPosY(), 0);
        vrOpengl.glRotatef(getAngulo(), 0,0,1);
        vrOpengl.glScalef(getZoom(), getZoom(), 1);

        vrOpengl.glVertexPointer(2, GL10.GL_FLOAT,0, getVetorVertices());//vertices de 2 em 2 pontos, GL10.GL_FLOAT, nao pula nenhuma informação, array de pontos
        vrOpengl.glColor4f(cor[0], cor[1], cor[2],1);
        vrOpengl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, getVetorVertices().limit()/2);
    }
}
