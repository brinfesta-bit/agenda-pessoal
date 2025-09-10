# 📅 Agenda Pessoal - Aplicação Web e Android

Uma agenda pessoal completa com sistema de cores automático por dias da semana, disponível em duas versões: **aplicação web** e **app Android nativo**.

## 🌟 Características Principais

- **Sistema de cores automático** - Detecta dias da semana no texto e aplica cores específicas
- **Interface moderna** - Design responsivo com gradientes e animações
- **Funcionalidades completas** - Adicionar, editar, apagar, reorganizar itens
- **Armazenamento local** - Dados persistem entre sessões
- **Multiplataforma** - Versão web e Android

## 🎨 Cores dos Dias da Semana

| Dia | Cor | Código |
|-----|-----|--------|
| Domingo | 🔴 Vermelho | #FF6B6B |
| Segunda | 🟢 Verde-água | #4ECDC4 |
| Terça | 🔵 Azul | #45B7D1 |
| Quarta | 🟢 Verde | #96CEB4 |
| Quinta | 🟡 Amarelo | #FFEAA7 |
| Sexta | 🟣 Roxo | #DDA0DD |
| Sábado | 🟢 Verde-claro | #98D8C8 |

## 🚀 Como Usar

### Versão Web (Recomendada)

1. **Opção Simples:**
   - Baixe os arquivos
   - Abra `index.html` no navegador

2. **Com Servidor Local:**
   ```bash
   # Python
   python -m http.server 8000
   
   # Node.js
   npx http-server -p 8000
   
   # Acesse: http://localhost:8000
   ```

### Versão Android

1. Importe o projeto no Android Studio
2. Conecte um dispositivo ou use emulador
3. Execute o projeto

## 📁 Estrutura do Projeto

```
📦 agenda-pessoal/
├── 🌐 VERSÃO WEB
│   ├── index.html          # Interface principal
│   ├── styles.css          # Estilos e design
│   ├── script.js           # Funcionalidades JavaScript
│   └── COMO_USAR.md        # Instruções detalhadas
│
├── 📱 VERSÃO ANDROID
│   ├── MainActivity.java   # Atividade principal
│   ├── AgendaAdapter.java  # Adaptador do RecyclerView
│   ├── AgendaItem.java     # Modelo de dados
│   ├── activity_main.xml   # Layout principal
│   ├── item_agenda.xml     # Layout dos itens
│   ├── strings.xml         # Recursos de texto
│   ├── styles.xml          # Estilos Android
│   └── *.xml               # Recursos drawable
│
└── 📋 DOCUMENTAÇÃO
    ├── README.md           # Documentação Android
    └── .gitignore          # Arquivos ignorados
```

## ✨ Funcionalidades

### 📝 Gerenciamento de Itens
- ➕ **Adicionar** novos itens
- ✏️ **Editar** texto e cores
- 🗑️ **Apagar** com confirmação
- 🎨 **Mudar cores** rapidamente
- 🔄 **Arrastar** para reorganizar

### 🎯 Recursos Avançados
- 🔍 **Filtros** por dia da semana
- 💾 **Armazenamento** automático
- ⌨️ **Atalhos** de teclado
- 📱 **Design responsivo**
- 🌙 **Animações** suaves

### ⌨️ Atalhos de Teclado (Versão Web)
- `Ctrl + N` - Novo item
- `Ctrl + S` - Salvar
- `ESC` - Fechar modal

## 🛠️ Tecnologias Utilizadas

### Versão Web
- **HTML5** - Estrutura semântica
- **CSS3** - Design moderno com flexbox e grid
- **JavaScript ES6+** - Funcionalidades interativas
- **SortableJS** - Arrastar e soltar
- **LocalStorage** - Persistência de dados
- **Font Awesome** - Ícones

### Versão Android
- **Java** - Linguagem principal
- **Android SDK** - Framework nativo
- **RecyclerView** - Lista de itens
- **CardView** - Design material
- **SharedPreferences** - Armazenamento local

## 📱 Compatibilidade

### Versão Web
- ✅ Chrome 60+
- ✅ Firefox 55+
- ✅ Safari 12+
- ✅ Edge 79+
- ✅ Mobile browsers

### Versão Android
- ✅ Android 5.0+ (API 21+)
- ✅ Tablets e smartphones

## 🎯 Demonstração

### Interface Web
![Agenda Web](https://via.placeholder.com/800x600/667eea/ffffff?text=Agenda+Web+Interface)

### App Android
![Agenda Android](https://via.placeholder.com/400x700/4ECDC4/ffffff?text=Agenda+Android+App)

## 🚀 Instalação e Execução

### Clonar Repositório
```bash
git clone https://github.com/seu-usuario/agenda-pessoal.git
cd agenda-pessoal
```

### Executar Versão Web
```bash
# Método 1: Abrir diretamente
open index.html

# Método 2: Servidor local
python -m http.server 8000
# Acesse: http://localhost:8000
```

### Executar Versão Android
1. Abra o Android Studio
2. Importe o projeto
3. Sincronize as dependências
4. Execute no dispositivo/emulador

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

Criado com ❤️ para organização pessoal e produtividade.

---

⭐ **Se este projeto foi útil para você, considere dar uma estrela!**