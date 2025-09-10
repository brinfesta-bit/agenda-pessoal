package com.agenda.mobile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AgendaItem {
    private String texto;
    private int cor;
    private String dataHoraCriacao;
    private boolean salvo;
    private int diaSemana; // 0=domingo, 1=segunda, ..., 6=sábado
    
    // Cores para cada dia da semana
    public static final int[] CORES_DIAS = {
        0xFFFF6B6B, // Domingo - Vermelho
        0xFF4ECDC4, // Segunda - Azul claro
        0xFF45B7D1, // Terça - Azul
        0xFF96CEB4, // Quarta - Verde claro
        0xFFFECA57, // Quinta - Amarelo
        0xFFFF9FF3, // Sexta - Rosa
        0xFF6C5CE7  // Sábado - Roxo
    };
    
    public static final String[] NOMES_DIAS = {
        "domingo", "segunda", "terça", "quarta", "quinta", "sexta", "sábado"
    };
    
    public AgendaItem(String texto) {
        this.texto = texto;
        this.salvo = false;
        this.diaSemana = detectarDiaSemana(texto);
        this.cor = CORES_DIAS[this.diaSemana];
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        this.dataHoraCriacao = sdf.format(new Date());
    }
    
    private int detectarDiaSemana(String texto) {
        String textoLower = texto.toLowerCase();
        for (int i = 0; i < NOMES_DIAS.length; i++) {
            if (textoLower.contains(NOMES_DIAS[i])) {
                return i;
            }
        }
        return 1; // Default para segunda-feira
    }
    
    public void alterarCor(int novaCor) {
        this.cor = novaCor;
        // Atualizar dia da semana baseado na cor
        for (int i = 0; i < CORES_DIAS.length; i++) {
            if (CORES_DIAS[i] == novaCor) {
                this.diaSemana = i;
                break;
            }
        }
    }
    
    public void salvar() {
        this.salvo = true;
        this.cor = 0xFF808080; // Cinza
    }
    
    // Getters e Setters
    public String getTexto() { return texto; }
    public void setTexto(String texto) { 
        this.texto = texto;
        if (!salvo) {
            this.diaSemana = detectarDiaSemana(texto);
            this.cor = CORES_DIAS[this.diaSemana];
        }
    }
    
    public int getCor() { return cor; }
    public String getDataHoraCriacao() { return dataHoraCriacao; }
    public boolean isSalvo() { return salvo; }
    public int getDiaSemana() { return diaSemana; }
    
    public String getNomeDiaSemana() {
        return NOMES_DIAS[diaSemana];
    }
}