package com.agenda.mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.graphics.drawable.GradientDrawable;

public class MainActivity extends AppCompatActivity implements AgendaAdapter.OnItemActionListener {
    
    private RecyclerView recyclerView;
    private AgendaAdapter adapter;
    private FloatingActionButton fabAddItem;
    private LinearLayout painelCores;
    private Button[] botoesCores;
    private int itemSelecionadoParaCor = -1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Criar layout programaticamente
        createMainLayout();
        
        initViews();
        setupRecyclerView();
        setupColorPanel();
        setupFab();
    }
    
    private void createMainLayout() {
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(16, 16, 16, 16);
        mainLayout.setBackgroundColor(0xFFF5F5F5);
        
        // Cabeçalho
        LinearLayout headerLayout = new LinearLayout(this);
        headerLayout.setOrientation(LinearLayout.HORIZONTAL);
        headerLayout.setLayoutParams(new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 
            LinearLayout.LayoutParams.WRAP_CONTENT));
        
        android.widget.TextView titleText = new android.widget.TextView(this);
        titleText.setText("Agenda Móvel");
        titleText.setTextSize(24f);
        titleText.setTextColor(0xFF333333);
        titleText.setLayoutParams(new LinearLayout.LayoutParams(
            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        
        fabAddItem = new FloatingActionButton(this);
        fabAddItem.setImageResource(android.R.drawable.ic_input_add);
        
        headerLayout.addView(titleText);
        headerLayout.addView(fabAddItem);
        
        // RecyclerView
        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
        
        // Painel de cores
        painelCores = new LinearLayout(this);
        painelCores.setOrientation(LinearLayout.HORIZONTAL);
        painelCores.setBackgroundColor(0xFFFFFFFF);
        painelCores.setPadding(8, 8, 8, 8);
        painelCores.setVisibility(View.GONE);
        
        // Criar botões de cores
        String[] diasLetras = {"D", "S", "T", "Q", "Q", "S", "S"};
        int[] cores = {0xFFFF6B6B, 0xFF4ECDC4, 0xFF45B7D1, 0xFF96CEB4, 0xFFFECA57, 0xFFFF9FF3, 0xFF6C5CE7};
        
        botoesCores = new Button[7];
        for (int i = 0; i < 7; i++) {
            Button btn = new Button(this);
            btn.setText(diasLetras[i]);
            btn.setBackgroundColor(cores[i]);
            btn.setTextColor(0xFFFFFFFF);
            btn.setLayoutParams(new LinearLayout.LayoutParams(120, 120));
            botoesCores[i] = btn;
            painelCores.addView(btn);
        }
        
        mainLayout.addView(headerLayout);
        mainLayout.addView(recyclerView);
        mainLayout.addView(painelCores);
        
        setContentView(mainLayout);
    }
    
    private void initViews() {
        // Views já foram criados programaticamente em createMainLayout()
        // Não precisamos fazer findViewById
    }
    
    private void setupRecyclerView() {
        adapter = new AgendaAdapter();
        adapter.setOnItemActionListener(this);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        
        // Configurar arrastar e soltar
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
            
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                adapter.moverItem(fromPosition, toPosition);
                return true;
            }
            
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Não implementado - usar botão apagar
            }
        });
        
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    
    private void setupColorPanel() {
        for (int i = 0; i < botoesCores.length; i++) {
            final int diaSemana = i;
            botoesCores[i].setOnClickListener(v -> {
                if (itemSelecionadoParaCor != -1) {
                    // Alterar cor do item selecionado
                    adapter.alterarCorItem(itemSelecionadoParaCor, AgendaItem.CORES_DIAS[diaSemana]);
                    adapter.notifyItemChanged(itemSelecionadoParaCor);
                    
                    // Ocultar painel de cores
                    painelCores.setVisibility(View.GONE);
                    itemSelecionadoParaCor = -1;
                }
            });
        }
    }
    
    private void setupFab() {
        fabAddItem.setOnClickListener(v -> {
            // Criar novo item da agenda
            AgendaItem novoItem = new AgendaItem("Novo agendamento");
            adapter.adicionarItem(novoItem);
        });
    }
    
    @Override
    public void onMudarCor(AgendaItem item, int position) {
        itemSelecionadoParaCor = position;
        painelCores.setVisibility(View.VISIBLE);
    }
    
    @Override
    public void onSalvar(AgendaItem item, int position) {
        item.salvar();
        adapter.organizarPorCores();
        adapter.notifyDataSetChanged();
    }
    
    @Override
    public void onApagar(AgendaItem item, int position) {
        adapter.removerItem(position);
    }
    
    @Override
    public void onTextoAlterado(AgendaItem item, int position) {
        adapter.notifyItemChanged(position);
    }
    
    @Override
    public void onBackPressed() {
        if (painelCores.getVisibility() == View.VISIBLE) {
            painelCores.setVisibility(View.GONE);
            itemSelecionadoParaCor = -1;
        } else {
            super.onBackPressed();
        }
    }
}