package com.agenda.mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder> {
    
    private List<AgendaItem> itens;
    private List<AgendaItem> lixeira;
    private OnItemActionListener listener;
    
    public interface OnItemActionListener {
        void onMudarCor(AgendaItem item, int position);
        void onSalvar(AgendaItem item, int position);
        void onApagar(AgendaItem item, int position);
        void onTextoAlterado(AgendaItem item, int position);
    }
    
    public AgendaAdapter() {
        this.itens = new ArrayList<>();
        this.lixeira = new ArrayList<>();
    }
    
    public void setOnItemActionListener(OnItemActionListener listener) {
        this.listener = listener;
    }
    
    @NonNull
    @Override
    public AgendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        // Como não temos R.layout.item_agenda compilado, vamos criar o layout programaticamente
        return new AgendaViewHolder(createItemView(parent.getContext()));
    }
    
    private View createItemView(android.content.Context context) {
        // Criar o layout do item programaticamente
        androidx.cardview.widget.CardView cardView = new androidx.cardview.widget.CardView(context);
        cardView.setLayoutParams(new RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT, 
            RecyclerView.LayoutParams.WRAP_CONTENT));
        cardView.setCardElevation(4f);
        cardView.setRadius(12f);
        
        android.widget.LinearLayout mainLayout = new android.widget.LinearLayout(context);
        mainLayout.setOrientation(android.widget.LinearLayout.VERTICAL);
        mainLayout.setPadding(16, 16, 16, 16);
        
        // Layout do texto
        android.widget.LinearLayout textLayout = new android.widget.LinearLayout(context);
        textLayout.setOrientation(android.widget.LinearLayout.VERTICAL);
        textLayout.setPadding(12, 12, 12, 12);
        
        android.widget.EditText editText = new android.widget.EditText(context);
        editText.setId(android.view.View.generateViewId());
        editText.setHint("Digite seu agendamento...");
        editText.setMinLines(2);
        
        android.widget.TextView dateText = new android.widget.TextView(context);
        dateText.setId(android.view.View.generateViewId());
        dateText.setText("Criado em: --/--/---- --:-");
        dateText.setTextSize(12f);
        
        textLayout.addView(editText);
        textLayout.addView(dateText);
        
        // Layout dos botões
        android.widget.LinearLayout buttonLayout = new android.widget.LinearLayout(context);
        buttonLayout.setOrientation(android.widget.LinearLayout.HORIZONTAL);
        buttonLayout.setVisibility(android.view.View.GONE);
        
        String[] buttonTexts = {"Cor", "Editar", "Salvar", "Lixo"};
        for (String text : buttonTexts) {
            android.widget.Button button = new android.widget.Button(context);
            button.setId(android.view.View.generateViewId());
            button.setText(text);
            button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
                0, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            buttonLayout.addView(button);
        }
        
        mainLayout.addView(textLayout);
        mainLayout.addView(buttonLayout);
        cardView.addView(mainLayout);
        
        return cardView;
    }
    
    @Override
    public void onBindViewHolder(@NonNull AgendaViewHolder holder, int position) {
        AgendaItem item = itens.get(position);
        holder.bind(item, position);
    }
    
    @Override
    public int getItemCount() {
        return itens.size();
    }
    
    public void adicionarItem(AgendaItem item) {
        itens.add(item);
        organizarPorCores();
        notifyDataSetChanged();
    }
    
    public void removerItem(int position) {
        if (position >= 0 && position < itens.size()) {
            AgendaItem item = itens.get(position);
            lixeira.add(item);
            itens.remove(position);
            notifyItemRemoved(position);
        }
    }
    
    public void moverItem(int fromPosition, int toPosition) {
        Collections.swap(itens, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }
    
    public void organizarPorCores() {
        // Organizar por dia da semana (cores) e depois por salvos
        itens.sort((item1, item2) -> {
            if (item1.isSalvo() && !item2.isSalvo()) return -1;
            if (!item1.isSalvo() && item2.isSalvo()) return 1;
            return Integer.compare(item1.getDiaSemana(), item2.getDiaSemana());
        });
    }
    
    public List<AgendaItem> getLixeira() {
        return new ArrayList<>(lixeira);
    }
    
    public void limparLixeira() {
        lixeira.clear();
    }
    
    public void alterarCorItem(int position, int novaCor) {
        if (position >= 0 && position < itens.size()) {
            AgendaItem item = itens.get(position);
            item.alterarCor(novaCor);
        }
    }
    
    public AgendaItem getItem(int position) {
        if (position >= 0 && position < itens.size()) {
            return itens.get(position);
        }
        return null;
    }
    
    class AgendaViewHolder extends RecyclerView.ViewHolder {
        
        private EditText editTexto;
        private TextView textDataHora;
        private Button btnMudarCor, btnEditar, btnSalvar, btnApagar;
        private View layoutPrincipal, layoutBotoes;
        private boolean editando = false;
        
        public AgendaViewHolder(@NonNull View itemView) {
            super(itemView);
            
            // Encontrar views programaticamente
            androidx.cardview.widget.CardView cardView = (androidx.cardview.widget.CardView) itemView;
            android.widget.LinearLayout mainLayout = (android.widget.LinearLayout) cardView.getChildAt(0);
            layoutPrincipal = mainLayout;
            
            android.widget.LinearLayout textLayout = (android.widget.LinearLayout) mainLayout.getChildAt(0);
            editTexto = (android.widget.EditText) textLayout.getChildAt(0);
            textDataHora = (android.widget.TextView) textLayout.getChildAt(1);
            
            layoutBotoes = (android.widget.LinearLayout) mainLayout.getChildAt(1);
            btnMudarCor = (android.widget.Button) layoutBotoes.getChildAt(0);
            btnEditar = (android.widget.Button) layoutBotoes.getChildAt(1);
            btnSalvar = (android.widget.Button) layoutBotoes.getChildAt(2);
            btnApagar = (android.widget.Button) layoutBotoes.getChildAt(3);
            
            setupClickListeners();
        }
        
        private void setupClickListeners() {
            // Click longo para mostrar botões de ação
            layoutPrincipal.setOnLongClickListener(v -> {
                toggleBotoes();
                return true;
            });
            
            // Click simples para editar texto
            layoutPrincipal.setOnClickListener(v -> {
                if (!editando) {
                    editando = true;
                    editTexto.setEnabled(true);
                    editTexto.requestFocus();
                }
            });
            
            btnMudarCor.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onMudarCor(itens.get(getAdapterPosition()), getAdapterPosition());
                }
            });
            
            btnEditar.setOnClickListener(v -> {
                editando = true;
                editTexto.setEnabled(true);
                editTexto.requestFocus();
            });
            
            btnSalvar.setOnClickListener(v -> {
                if (listener != null) {
                    AgendaItem item = itens.get(getAdapterPosition());
                    item.setTexto(editTexto.getText().toString());
                    listener.onSalvar(item, getAdapterPosition());
                    editando = false;
                    editTexto.setEnabled(false);
                    layoutBotoes.setVisibility(View.GONE);
                }
            });
            
            btnApagar.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onApagar(itens.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
        
        private void toggleBotoes() {
            if (layoutBotoes.getVisibility() == View.VISIBLE) {
                layoutBotoes.setVisibility(View.GONE);
            } else {
                layoutBotoes.setVisibility(View.VISIBLE);
            }
        }
        
        public void bind(AgendaItem item, int position) {
            editTexto.setText(item.getTexto());
            textDataHora.setText("Criado em: " + item.getDataHoraCriacao());
            editTexto.setEnabled(editando);
            
            // Aplicar cor do item
            GradientDrawable background = (GradientDrawable) layoutPrincipal.getBackground();
            if (background != null) {
                background.setColor(item.getCor());
            }
            
            // Ocultar botões se não estiver editando
            if (!editando) {
                layoutBotoes.setVisibility(View.GONE);
            }
        }
    }
}