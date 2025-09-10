# 🌐 Como Acessar sua Agenda Web

## 📋 Opções para Visualizar a Agenda

### Opção 1: Abrir Diretamente no Navegador (Mais Simples)
1. Navegue até a pasta: `c:\Users\clar\Pictures\Nova pasta (3)`
2. Clique duas vezes no arquivo `index.html`
3. A agenda abrirá automaticamente no seu navegador padrão

### Opção 2: Usar Servidor Local (Recomendado)
Se você tiver Python instalado:
```bash
# Abra o terminal na pasta do projeto
cd "c:\Users\clar\Pictures\Nova pasta (3)"

# Inicie o servidor (Python 3)
python -m http.server 8000

# Ou Python 2
python -m SimpleHTTPServer 8000

# Acesse: http://localhost:8000
```

Se você tiver Node.js instalado:
```bash
# Instale o http-server globalmente
npm install -g http-server

# Na pasta do projeto
http-server -p 8000 -o

# Acesse: http://localhost:8000
```

### Opção 3: Usar Extensão do VS Code
1. Instale a extensão "Live Server" no VS Code
2. Abra a pasta do projeto no VS Code
3. Clique com botão direito no `index.html`
4. Selecione "Open with Live Server"

## 🎯 Link de Acesso

**URL Local:** `http://localhost:8000` (quando usar servidor local)

**Arquivo Local:** `file:///c:/Users/clar/Pictures/Nova%20pasta%20(3)/index.html`

## ✨ Funcionalidades da Agenda Web

### 🎨 Sistema de Cores Automático
- **Domingo:** Vermelho (#FF6B6B)
- **Segunda:** Verde-água (#4ECDC4)
- **Terça:** Azul (#45B7D1)
- **Quarta:** Verde (#96CEB4)
- **Quinta:** Amarelo (#FFEAA7)
- **Sexta:** Roxo (#DDA0DD)
- **Sábado:** Verde-claro (#98D8C8)

### 📝 Como Usar

1. **Adicionar Item:**
   - Clique no botão "+" ou no botão flutuante
   - Digite o texto (mencione o dia da semana para cor automática)
   - Clique em "Salvar"

2. **Editar Item:**
   - Clique no ícone de lápis (✏️)
   - Modifique o texto ou escolha nova cor
   - Clique em "Salvar"

3. **Mudar Cor:**
   - Clique no ícone de paleta (🎨)
   - A cor mudará automaticamente

4. **Apagar Item:**
   - Clique no ícone de lixeira (🗑️)
   - Confirme a exclusão

5. **Arrastar e Reorganizar:**
   - Clique e arraste os itens para reordená-los

6. **Filtrar por Dia:**
   - Clique nos botões coloridos dos dias da semana
   - Os itens correspondentes serão destacados

### ⌨️ Atalhos de Teclado
- **Ctrl + N:** Novo item
- **Ctrl + S:** Salvar (quando modal estiver aberto)
- **ESC:** Fechar modal

### 💾 Armazenamento
- Todos os dados são salvos automaticamente no navegador
- Os itens persistem entre sessões
- Funciona offline após o primeiro carregamento

## 🔧 Resolução de Problemas

### Se a agenda não carregar:
1. Verifique se todos os arquivos estão na mesma pasta
2. Tente abrir em modo privado/incógnito
3. Limpe o cache do navegador
4. Use um servidor local em vez de abrir o arquivo diretamente

### Se as funcionalidades não funcionarem:
1. Verifique se o JavaScript está habilitado
2. Abra as ferramentas de desenvolvedor (F12) e veja se há erros
3. Tente em outro navegador

## 📱 Compatibilidade

✅ **Navegadores Suportados:**
- Chrome 60+
- Firefox 55+
- Safari 12+
- Edge 79+

✅ **Dispositivos:**
- Desktop (Windows, Mac, Linux)
- Tablets
- Smartphones

---

**🎉 Sua agenda web está pronta para uso!**

Para acessar, simplesmente abra o arquivo `index.html` no seu navegador ou use um dos métodos de servidor local descritos acima.