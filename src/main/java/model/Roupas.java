package model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import java.io.IOException;
import java.nio.file.Path;

public class Roupas {
    private String tamanho;
    private String cor;
    private String marca;
    private String sexo;
    private double preco;
    private String codigoBarras;

    public Roupas (String tamanho, String cor, String marca, String sexo, double preco, String codigoBarras){
        this.tamanho=tamanho;
        this.cor=cor;
        this.marca=marca;
        this.sexo=sexo;
        this.preco=preco;
        this.codigoBarras=codigoBarras;
    }

    public Roupas(){
    }

    @Override
    public String toString (){
        return "Marca: " +marca +" | Tamanho: " +tamanho +" | Cor: " +cor +" | Sexo: " +sexo +" | Preço: R$" +preco +" | Código de Barras: " +codigoBarras
                + "\n" + gerarCodigoBarrasVisual();
    }

    public String gerarCodigoBarrasVisual() {
        StringBuilder barras = new StringBuilder("|");
        for (char c : codigoBarras.toCharArray()) {
            int valor = Character.isDigit(c) ? Character.getNumericValue(c) : (c % 10);
            int largura = (valor % 3) + 1;
            barras.append("█".repeat(largura));
            barras.append(" ");
        }
        barras.append("|");
        return barras.toString();
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void gerarImagemCodigoBarras(String caminhoArquivo) throws WriterException, IOException {
        Code128Writer writer = new Code128Writer();
        BitMatrix bitMatrix = writer.encode(codigoBarras, BarcodeFormat.CODE_128, 300, 100);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", Path.of(caminhoArquivo));
    }
}