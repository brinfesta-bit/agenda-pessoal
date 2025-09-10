// Agenda Web - JavaScript Principal
class AgendaWeb {
    constructor() {
        this.items = [];
        this.currentEditingId = null;
        this.dayColors = {
            'domingo': '#FF6B6B',
            'segunda': '#4ECDC4',
            'terça': '#45B7D1',
            'quarta': '#96CEB4',
            'quinta': '#FFEAA7',
            'sexta': '#DDA0DD',
            'sábado': '#98D8C8'
        };
        this.dayKeywords = {
            'domingo': ['domingo', 'dom'],
            'segunda': ['segunda', 'seg'],
            'terça': ['terça', 'terca', 'ter'],
            'quarta': ['quarta', 'qua'],
            'quinta': ['quinta', 'qui'],
            'sexta': ['sexta', 'sex'],
            'sábado': ['sábado', 'sabado', 'sab']
        };
        
        this.init();
    }

    init() {
        this.loadFromStorage();
        this.setupEventListeners();
        this.setupSortable();
        this.renderItems();
    }

    setupEventListeners() {
        // Botões de adicionar
        document.getElementById('addItemBtn').addEventListener('click', () => this.openAddModal());
        document.getElementById('floatingAddBtn').addEventListener('click', () => this.openAddModal());
        
        // Modal
        document.getElementById('closeModal').addEventListener('click', () => this.closeModal());
        document.getElementById('cancelEdit').addEventListener('click', () => this.closeModal());
        document.getElementById('saveEdit').addEventListener('click', () => this.saveItem());
        
        // Fechar modal clicando fora
        document.getElementById('editModal').addEventListener('click', (e) => {
            if (e.target.id === 'editModal') {
                this.closeModal();
            }
        });
        
        // Seletor de cores no modal
        document.querySelectorAll('.color-option').forEach(btn => {
            btn.addEventListener('click', (e) => {
                document.querySelectorAll('.color-option').forEach(b => b.classList.remove('selected'));
                e.target.classList.add('selected');
            });
        });
        
        // Botões de cores dos dias da semana
        document.querySelectorAll('.color-btn').forEach(btn => {
            btn.addEventListener('click', (e) => {
                const day = btn.dataset.day;
                const color = btn.dataset.color;
                this.filterByDay(day);
            });
        });
        
        // Tecla ESC para fechar modal
        document.addEventListener('keydown', (e) => {
            if (e.key === 'Escape') {
                this.closeModal();
            }
        });
    }

    setupSortable() {
        const agendaList = document.getElementById('agendaList');
        new Sortable(agendaList, {
            animation: 150,
            ghostClass: 'dragging',
            onEnd: (evt) => {
                // Reordenar array de itens
                const item = this.items.splice(evt.oldIndex, 1)[0];
                this.items.splice(evt.newIndex, 0, item);
                this.saveToStorage();
            }
        });
    }

    generateId() {
        return Date.now().toString(36) + Math.random().toString(36).substr(2);
    }

    detectDayFromText(text) {
        const lowerText = text.toLowerCase();
        
        for (const [day, keywords] of Object.entries(this.dayKeywords)) {
            for (const keyword of keywords) {
                if (lowerText.includes(keyword)) {
                    return day;
                }
            }
        }
        
        // Se não detectar nenhum dia, usar cor baseada no dia atual
        const today = new Date();
        const dayNames = ['domingo', 'segunda', 'terça', 'quarta', 'quinta', 'sexta', 'sábado'];
        return dayNames[today.getDay()];
    }

    openAddModal() {
        this.currentEditingId = null;
        document.getElementById('editTextarea').value = '';
        document.querySelectorAll('.color-option').forEach(btn => btn.classList.remove('selected'));
        document.getElementById('editModal').classList.add('active');
        document.getElementById('editTextarea').focus();
    }

    openEditModal(id) {
        const item = this.items.find(i => i.id === id);
        if (!item) return;
        
        this.currentEditingId = id;
        document.getElementById('editTextarea').value = item.text;
        
        // Selecionar cor atual
        document.querySelectorAll('.color-option').forEach(btn => {
            btn.classList.remove('selected');
            if (btn.dataset.color === item.color) {
                btn.classList.add('selected');
            }
        });
        
        document.getElementById('editModal').classList.add('active');
        document.getElementById('editTextarea').focus();
    }

    closeModal() {
        document.getElementById('editModal').classList.remove('active');
        this.currentEditingId = null;
    }

    saveItem() {
        const text = document.getElementById('editTextarea').value.trim();
        if (!text) {
            alert('Por favor, digite um texto para o item.');
            return;
        }
        
        const selectedColorBtn = document.querySelector('.color-option.selected');
        let color;
        
        if (selectedColorBtn) {
            color = selectedColorBtn.dataset.color;
        } else {
            // Auto-detectar cor baseada no texto
            const detectedDay = this.detectDayFromText(text);
            color = this.dayColors[detectedDay];
        }
        
        if (this.currentEditingId) {
            // Editar item existente
            const item = this.items.find(i => i.id === this.currentEditingId);
            if (item) {
                item.text = text;
                item.color = color;
                item.updatedAt = new Date().toISOString();
            }
        } else {
            // Criar novo item
            const newItem = {
                id: this.generateId(),
                text: text,
                color: color,
                createdAt: new Date().toISOString(),
                updatedAt: new Date().toISOString()
            };
            this.items.unshift(newItem); // Adicionar no início
        }
        
        this.saveToStorage();
        this.renderItems();
        this.closeModal();
    }

    deleteItem(id) {
        if (confirm('Tem certeza que deseja apagar este item?')) {
            this.items = this.items.filter(item => item.id !== id);
            this.saveToStorage();
            this.renderItems();
        }
    }

    changeItemColor(id) {
        const item = this.items.find(i => i.id === id);
        if (!item) return;
        
        // Ciclar pelas cores disponíveis
        const colors = Object.values(this.dayColors);
        const currentIndex = colors.indexOf(item.color);
        const nextIndex = (currentIndex + 1) % colors.length;
        
        item.color = colors[nextIndex];
        item.updatedAt = new Date().toISOString();
        
        this.saveToStorage();
        this.renderItems();
    }

    filterByDay(day) {
        const color = this.dayColors[day];
        const items = document.querySelectorAll('.agenda-item');
        
        items.forEach(item => {
            const itemColor = item.style.borderLeftColor;
            const rgbColor = this.hexToRgb(color);
            const expectedColor = `rgb(${rgbColor.r}, ${rgbColor.g}, ${rgbColor.b})`;
            
            if (itemColor === expectedColor) {
                item.style.display = 'block';
                item.style.animation = 'highlight 0.5s ease';
            } else {
                item.style.opacity = '0.3';
            }
        });
        
        // Remover filtro após 3 segundos
        setTimeout(() => {
            items.forEach(item => {
                item.style.display = 'block';
                item.style.opacity = '1';
                item.style.animation = '';
            });
        }, 3000);
    }

    hexToRgb(hex) {
        const result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
        return result ? {
            r: parseInt(result[1], 16),
            g: parseInt(result[2], 16),
            b: parseInt(result[3], 16)
        } : null;
    }

    formatDate(dateString) {
        const date = new Date(dateString);
        return date.toLocaleString('pt-BR', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });
    }

    renderItems() {
        const agendaList = document.getElementById('agendaList');
        
        if (this.items.length === 0) {
            agendaList.innerHTML = `
                <div class="empty-state">
                    <i class="fas fa-calendar-plus"></i>
                    <h3>Nenhum item na agenda</h3>
                    <p>Clique no botão "+" para adicionar seu primeiro item</p>
                </div>
            `;
            return;
        }
        
        agendaList.innerHTML = this.items.map(item => `
            <div class="agenda-item" style="border-left-color: ${item.color}" data-id="${item.id}">
                <div class="drag-handle"></div>
                <div class="item-content">
                    <div class="item-text">${this.escapeHtml(item.text)}</div>
                    <div class="item-actions">
                        <button class="action-btn edit-btn" onclick="agenda.openEditModal('${item.id}')" title="Editar">
                            <i class="fas fa-edit"></i>
                        </button>
                        <button class="action-btn color-change-btn" onclick="agenda.changeItemColor('${item.id}')" title="Mudar Cor">
                            <i class="fas fa-palette"></i>
                        </button>
                        <button class="action-btn delete-btn" onclick="agenda.deleteItem('${item.id}')" title="Apagar">
                            <i class="fas fa-trash"></i>
                        </button>
                    </div>
                </div>
                <div class="item-date">
                    Criado: ${this.formatDate(item.createdAt)}
                    ${item.updatedAt !== item.createdAt ? `<br>Editado: ${this.formatDate(item.updatedAt)}` : ''}
                </div>
            </div>
        `).join('');
    }

    escapeHtml(text) {
        const div = document.createElement('div');
        div.textContent = text;
        return div.innerHTML;
    }

    saveToStorage() {
        try {
            localStorage.setItem('agendaItems', JSON.stringify(this.items));
        } catch (error) {
            console.error('Erro ao salvar no localStorage:', error);
        }
    }

    loadFromStorage() {
        try {
            const stored = localStorage.getItem('agendaItems');
            if (stored) {
                this.items = JSON.parse(stored);
            }
        } catch (error) {
            console.error('Erro ao carregar do localStorage:', error);
            this.items = [];
        }
    }

    // Método para exportar dados
    exportData() {
        const dataStr = JSON.stringify(this.items, null, 2);
        const dataBlob = new Blob([dataStr], {type: 'application/json'});
        const url = URL.createObjectURL(dataBlob);
        const link = document.createElement('a');
        link.href = url;
        link.download = 'agenda-backup.json';
        link.click();
        URL.revokeObjectURL(url);
    }

    // Método para importar dados
    importData(file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            try {
                const importedItems = JSON.parse(e.target.result);
                if (Array.isArray(importedItems)) {
                    this.items = importedItems;
                    this.saveToStorage();
                    this.renderItems();
                    alert('Dados importados com sucesso!');
                } else {
                    alert('Formato de arquivo inválido.');
                }
            } catch (error) {
                alert('Erro ao importar arquivo: ' + error.message);
            }
        };
        reader.readAsText(file);
    }
}

// Adicionar estilos de animação dinamicamente
const style = document.createElement('style');
style.textContent = `
    @keyframes highlight {
        0% { transform: scale(1); }
        50% { transform: scale(1.05); box-shadow: 0 8px 25px rgba(102, 126, 234, 0.3); }
        100% { transform: scale(1); }
    }
`;
document.head.appendChild(style);

// Inicializar aplicação quando DOM estiver carregado
let agenda;
document.addEventListener('DOMContentLoaded', () => {
    agenda = new AgendaWeb();
});

// Adicionar atalhos de teclado
document.addEventListener('keydown', (e) => {
    // Ctrl/Cmd + N para novo item
    if ((e.ctrlKey || e.metaKey) && e.key === 'n') {
        e.preventDefault();
        agenda.openAddModal();
    }
    
    // Ctrl/Cmd + S para salvar (quando modal estiver aberto)
    if ((e.ctrlKey || e.metaKey) && e.key === 's') {
        const modal = document.getElementById('editModal');
        if (modal.classList.contains('active')) {
            e.preventDefault();
            agenda.saveItem();
        }
    }
});